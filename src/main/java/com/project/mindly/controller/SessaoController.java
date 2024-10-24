package com.project.mindly.controller;

import com.project.mindly.model.sessao.Sessao;
import com.project.mindly.repository.SessaoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("sessao")
public class SessaoController {

    private final SessaoRepository sessaoRepository;


    public SessaoController(SessaoRepository sessaoRepository) {
        this.sessaoRepository = sessaoRepository;
    }

    @GetMapping
    public List<Sessao> getAllSessao(){
        return sessaoRepository.findAll();
    }

    @GetMapping("/id")
    public ResponseEntity<Sessao> getByIdSessao(@PathVariable @Valid int id) {
        return sessaoRepository.findById(id)
                .map(result -> ResponseEntity.status(HttpStatus.OK).body(result))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
