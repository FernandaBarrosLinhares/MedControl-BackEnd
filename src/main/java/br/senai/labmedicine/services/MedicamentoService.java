package br.senai.labmedicine.services;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.senai.labmedicine.dtos.Medicamento.MedicamentoAtualizacaoDTO;
import br.senai.labmedicine.dtos.Medicamento.MedicamentoCadastroDTO;
import br.senai.labmedicine.dtos.Medicamento.MedicamentoResponseDTO;
import br.senai.labmedicine.models.Medicamento;
import br.senai.labmedicine.repositories.MedicamentoRepository;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    public MedicamentoResponseDTO cadastraMedicamento(MedicamentoCadastroDTO medicamentoDTO) {
        Medicamento novoMedicamento = new Medicamento();

        MedicamentoResponseDTO response = new MedicamentoResponseDTO();

        BeanUtils.copyProperties(medicamentoDTO, novoMedicamento);

        novoMedicamento = medicamentoRepository.save(novoMedicamento);

        BeanUtils.copyProperties(novoMedicamento, response);

        return response;
    }

    public MedicamentoResponseDTO atualizaMedicamentoId(Long id, MedicamentoAtualizacaoDTO medicamentoDTO) {
        Medicamento atualizaMedicamento = medicamentoRepository.getReferenceById(medicamentoDTO.getId());

        BeanUtils.copyProperties(medicamentoDTO, atualizaMedicamento);

        atualizaMedicamento = medicamentoRepository.save(atualizaMedicamento);

        MedicamentoResponseDTO response = new MedicamentoResponseDTO();

        BeanUtils.copyProperties(atualizaMedicamento, response);

        return response;
    }

    public MedicamentoResponseDTO buscarMedicamentoPorId(Long id) {
        Medicamento listaMedicamentoId = medicamentoRepository.getReferenceById(id);
        MedicamentoResponseDTO response = new MedicamentoResponseDTO();
        BeanUtils.copyProperties(listaMedicamentoId, response);
        return response;
    }

    public void excluirMedicamentoId(Long id) {
        Medicamento medicamentoBd = this.medicamentoRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Medicamento não cadastrado."));
        try {
            medicamentoRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Medicamento em uso não pode ser deletado.");
        }

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