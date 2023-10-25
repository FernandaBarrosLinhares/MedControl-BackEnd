package br.senai.labmedicine.services;

import br.senai.labmedicine.dtos.estatistica.EstattisticaDTO;
import br.senai.labmedicine.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstatisticasService {
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private ExameRepository exameRepository;
    @Autowired
    private ExercicioRepository exercicioRepository;
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicamentoRepository medicamentoRepository;
    @Autowired
    private DietaRepository dietaRepository;

    public EstattisticaDTO listarEstatisticas() {
        EstattisticaDTO estatistica = new EstattisticaDTO();
        estatistica.setNumPacientes(this.pacienteRepository.count());
        estatistica.setNumExames(this.exameRepository.count());
        estatistica.setNumExercicios(this.exercicioRepository.count());
        estatistica.setNumConsultas(this.consultaRepository.count());
        estatistica.setNumMedicamentos(this.medicamentoRepository.count());
        estatistica.setNumDietas(this.dietaRepository.count());
        return estatistica;
    }
}
