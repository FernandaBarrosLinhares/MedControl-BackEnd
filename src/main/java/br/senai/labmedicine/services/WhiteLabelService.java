package br.senai.labmedicine.services;

import br.senai.labmedicine.dtos.log.LogCadastroDTO;
import br.senai.labmedicine.dtos.usuario.UsuarioResponseDTO;
import br.senai.labmedicine.dtos.whiteLabel.WhiteLabelDTO;
import br.senai.labmedicine.models.WhiteLabel;
import br.senai.labmedicine.repositories.WhiteLabelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class WhiteLabelService {

    @Autowired
    private WhiteLabelRepository whiteLabelRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LogService logService;

    public WhiteLabelDTO atualizarConfig(Long idUsuarioLogado,Long id,WhiteLabelDTO config) throws AccessDeniedException {
        UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
        if(!usuarioLogado.getTipoUsuario().getDescricao().equals("Administrador")){
            throw new AccessDeniedException("Usuário sem acesso!");
        }
        WhiteLabel configBd = this.whiteLabelRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Configuração não encontrada"));
        if(config.getId() != null){
            throw new EntityNotFoundException("Você não pode alterar o id");
        }
        BeanUtils.copyProperties(config,configBd);
        configBd.setId(id);
        String mensagem = "O Administrador: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" Atualizou a configuração do sistema";
        logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
        return new WhiteLabelDTO(this.whiteLabelRepository.save(configBd));
    }

    public WhiteLabelDTO buscarConfig(Long id) {
        return new WhiteLabelDTO(this.whiteLabelRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Configuração não encontrada")));
    }
}
