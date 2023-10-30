package br.senai.labmedicine.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import br.senai.labmedicine.dtos.log.LogCadastroDTO;
import br.senai.labmedicine.dtos.usuario.UsuarioResponseDTO;
import br.senai.labmedicine.models.Consulta;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.senai.labmedicine.dtos.medicamento.MedicamentoAtualizacaoDTO;
import br.senai.labmedicine.dtos.medicamento.MedicamentoCadastroDTO;
import br.senai.labmedicine.dtos.medicamento.MedicamentoResponseDTO;
import br.senai.labmedicine.models.Medicamento;
import br.senai.labmedicine.repositories.MedicamentoRepository;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private LogService logService;

    public MedicamentoResponseDTO cadastraMedicamento(Long idUsuarioLogado,MedicamentoCadastroDTO medicamentoDTO) {
        Medicamento novoMedicamento = new Medicamento();

        MedicamentoResponseDTO response = new MedicamentoResponseDTO();

        BeanUtils.copyProperties(medicamentoDTO, novoMedicamento);
        novoMedicamento.setStatus(true);
        novoMedicamento = medicamentoRepository.save(novoMedicamento);

        BeanUtils.copyProperties(novoMedicamento, response);
        UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
        String mensagem = "O usuário: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" Cadastrou o medicamento (id:"+ novoMedicamento.getId()+")";
        logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
        return response;
    }

    public MedicamentoResponseDTO atualizaMedicamentoId(Long idUsuarioLogado,Long id, MedicamentoAtualizacaoDTO medicamentoDTO) {
        Medicamento atualizaMedicamento = medicamentoRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Medicamento não encontrado"));

        BeanUtils.copyProperties(medicamentoDTO, atualizaMedicamento);

        atualizaMedicamento = medicamentoRepository.save(atualizaMedicamento);

        MedicamentoResponseDTO response = new MedicamentoResponseDTO();

        BeanUtils.copyProperties(atualizaMedicamento, response);
        UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
        String mensagem = "O usuário: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" Atualizou o medicamento (id:"+ atualizaMedicamento.getId()+")";
        logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
        return response;
    }

    public MedicamentoResponseDTO buscarMedicamentoPorId(Long id) {
        Medicamento listaMedicamentoId = medicamentoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Medicamento não existe"));
        MedicamentoResponseDTO response = new MedicamentoResponseDTO();
        BeanUtils.copyProperties(listaMedicamentoId, response);
        return response;
    }

    public void excluirMedicamentoId(Long idUsuarioLogado,Long id) {
        Medicamento medicamentoBd = this.medicamentoRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Medicamento não cadastrado."));
        UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
        List<Medicamento> medicamentos = this.medicamentoRepository.existsByMedicamentoId(id);
        if(!medicamentos.isEmpty()){
            throw new DataIntegrityViolationException("Medicamento em uso não pode ser deletado.");
        }
        this.medicamentoRepository.deleteById(id);
        String mensagem = "O usuário: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" Deletou o medicamento (id:"+ medicamentoBd.getId()+")";
        logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));

    }

    public List<MedicamentoResponseDTO> buscar(String nomePaciente) {
        List<MedicamentoResponseDTO> medicamentosDTO = new ArrayList<>();
        List<Medicamento> medicamentos;

        if (nomePaciente != null && !nomePaciente.trim().isEmpty()) {
            medicamentos = this.medicamentoRepository.buscarPorNomePaciente(nomePaciente);
        } else {
            medicamentos = this.medicamentoRepository.findAll();
        }

        for (Medicamento medicamento : medicamentos) {
            MedicamentoResponseDTO medicamentoDTO = new MedicamentoResponseDTO();
            BeanUtils.copyProperties(medicamento, medicamentoDTO);

            medicamentosDTO.add(medicamentoDTO);
        }
        return medicamentosDTO;
    }

}