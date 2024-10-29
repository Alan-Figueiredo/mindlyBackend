package com.project.mindly.controller;


import com.project.mindly.model.profissional.Profissional;
import com.project.mindly.dtos.profissional.ProfissionalDto;
import com.project.mindly.dtos.profissional.ProfissionalDtoPatch;
import com.project.mindly.repository.ProfissionalRepository;
import com.project.mindly.service.ProfissionalService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissional")
public class ProfissionalController {


    private final ProfissionalService profissionalService;
    private static final Logger logger = LoggerFactory.getLogger(ProfissionalController.class);

    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }


    @GetMapping
    public List<Profissional> getProfissionalAll() {
        List<Profissional> profissional = profissionalService.getAllProfissional();
        logger.info("Total Profissionals : " + profissional.size());
        return profissional;
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Profissional> getByCpfProfissional(@PathVariable @Valid String cpf) {
        return profissionalService.getProfissionalById(cpf)
                .map(result -> ResponseEntity.status(HttpStatus.OK).body(result))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/create")
    public ResponseEntity<Profissional> createProfissional (@RequestBody @Valid ProfissionalDto data) {
        try {
            Profissional profissional = profissionalService.saveProfissional(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(profissional);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            logger.error("Ocorreu um erro inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    //@PatchMapping("/{cpf}")
    //public ResponseEntity<Profissional> updateProfissional(@RequestBody @Valid ProfissionalDtoPatch data,
                                                          // @PathVariable @Valid String cpf) {

    //}
    //@DeleteMapping("/{cpf}")
    //public ResponseEntity<Void> deleteProfissional(@PathVariable @Valid String cpf) {
       // return profissionalRepository.findById(cpf)
     //           .map(result -> {
     //               profissionalRepository.delete(result);
    //                return ResponseEntity.status(HttpStatus.NO_CONTENT).<Void>build();
    //            })
       //         .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
   // }

}
