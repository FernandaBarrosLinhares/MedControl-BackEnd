package br.senai.labmedicine.services;

import br.senai.labmedicine.dtos.Medicamento.MedicamentoAtualizacaoDTO;
import br.senai.labmedicine.dtos.Medicamento.MedicamentoCadastroDTO;
import br.senai.labmedicine.dtos.Medicamento.MedicamentoResponseDTO;
import br.senai.labmedicine.models.Medicamento;
import br.senai.labmedicine.repositories.MedicamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // public List<MedicamentoResponseDTO> buscarMedicamentoPorPaciente(String nomePaciente){
    //     List<Medicamento> medicamentos;
    //     List<MedicamentoResponseDTO> medicamentos = new ArrayList<>();
    //     if(nomePaciente == null || nomePaciente.isEmpty()){
    //         medicamentos = repository.findAll();
    //     }else {
    //         medicamentos = this.repository.BuscaPorNomePaciente(nomePaciente);
    //     }
    //     for(Medicamento medicamento : medicamentos){
    //         PacienteResponseDTO pacienteDTO = new PacienteResponseDTO();
    //         MedicamentoResponseDTO medicamentoDTO = new MedicamentoResponseDTO();
    //         BeanUtils.copyProperties(medicamento,medicamentoDTO);
    //         BeanUtils.copyProperties(medicamento.getPaciente(),pacienteDTO);
    //         medicamentoDTO.setPaciente(pacienteDTO);
    //         medicamentosDTO.add(medicamentoDTO);
    //     }
    //     return medicamentossDTO;
    // }

    public MedicamentoResponseDTO ListaMedicamentoId(Long id) {
        Medicamento listaMedicamentoId = medicamentoRepository.getReferenceById(id);
        MedicamentoResponseDTO response = new MedicamentoResponseDTO();
        BeanUtils.copyProperties(listaMedicamentoId, response);
        return response;
    }

    public void excluirMedicamentoId(Long id) {
        medicamentoRepository.deleteById((long) Math.toIntExact(id));
    }

}