package br.senai.labmedicine.exceptions;

import br.senai.labmedicine.dtos.validacoes.ErroValidacaoDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.security.sasl.AuthenticationException;
import java.io.UnsupportedEncodingException;
import java.nio.file.AccessDeniedException;
import java.security.NoSuchAlgorithmException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class TratadorDeExcecoes {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> tratarEntityNotFound(EntityNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> tratarExcecaoEnum(IllegalArgumentException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroValidacaoDTO>> tratarParametroInvalido(MethodArgumentNotValidException e){
        List<FieldError> errors = e.getFieldErrors();
        List<ErroValidacaoDTO> errosDTO = new ArrayList<>();
        for(FieldError erro : errors){
            errosDTO.add(new ErroValidacaoDTO(erro));
        }
        return new ResponseEntity<>(errosDTO,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> tratarConflitoCpf(DataIntegrityViolationException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<String> tratarErroDataHora(DateTimeParseException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> tratarErroAcesso(AccessDeniedException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> tratarErroLogin(AuthenticationException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnsupportedEncodingException.class)
    public ResponseEntity<String> tratarErroCriptografia(UnsupportedEncodingException e){
        return new ResponseEntity<>("Erro ao criptografar a senha.",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchAlgorithmException.class)
    public ResponseEntity<String> tratarErroCriptografia(NoSuchAlgorithmException e){
        return new ResponseEntity<>("Algorítimo de criptografia indisponível.",HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InternalError.class)
    public ResponseEntity<String> tratarErroInternalError(InternalError e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> tratarExcecaoNumero(NumberFormatException e){
        return new ResponseEntity<>("O valor informado é inválido.",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<String> trartarExcecaoHeader(MissingRequestHeaderException e){
        return new ResponseEntity<>("Usuário logado deve ser informado no header.",HttpStatus.BAD_REQUEST);
    }

}
