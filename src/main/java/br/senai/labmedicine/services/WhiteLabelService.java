package br.senai.labmedicine.services;

import br.senai.labmedicine.dtos.whiteLabel.WhiteLabelDTO;
import br.senai.labmedicine.models.WhiteLabel;
import br.senai.labmedicine.repositories.WhiteLabelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WhiteLabelService {

    @Autowired
    private WhiteLabelRepository whiteLabelRepository;

    public WhiteLabelDTO atualizarConfig(Long id,WhiteLabelDTO config) {
        WhiteLabel configBd = this.whiteLabelRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Configuração não encontrada"));
        if(config.getId() != null){
            throw new EntityNotFoundException("Você não pode alterar o id");
        }
        BeanUtils.copyProperties(config,configBd);
        configBd.setId(id);
        return new WhiteLabelDTO(this.whiteLabelRepository.save(configBd));
    }

    public WhiteLabelDTO buscarConfig(Long id) {
        return new WhiteLabelDTO(this.whiteLabelRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Configuração não encontrada")));
    }
}
