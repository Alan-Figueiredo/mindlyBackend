package com.project.mindly.controller;

import com.project.mindly.model.sessao.Sessao;
import com.project.mindly.model.sessao.SessaoDto;
import com.project.mindly.repository.SessaoRepository;
import com.project.mindly.service.SessaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessao")
public class SessaoController {

    private final SessaoService sessaoService;


    public SessaoController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @GetMapping
    public List<Sessao> getAllSessao(){
        return sessaoService.findAllSessao();
    }

    @GetMapping("/id")
    public ResponseEntity<Sessao> getByIdSessao(@PathVariable @Valid int id) {
        return sessaoService.findSessaoById(id)
                .map(result -> ResponseEntity.status(HttpStatus.OK).body(result))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/create")
    public ResponseEntity<Sessao> updateSessao(@RequestBody @Valid SessaoDto data) {
        try {
            Sessao sessao = sessaoService.saveSessao(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(sessao);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
