package com.globallabs.springwebmvc.rest;


import com.globallabs.springwebmvc.model.Jedi;
import com.globallabs.springwebmvc.repository.JediRepository;
import com.globallabs.springwebmvc.service.JediService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController // recurso, dados a serem acessados, controles rest são chamados de resurces, criar, deletar, atualziar, tudo via rest
public class JediResource {

    @Autowired
    private JediService service;


    @GetMapping("/api/jedi") //forma de acesso a rest
    public List<Jedi> getAllJedi() {
        return service.findAll();
    }

    @GetMapping("/api/jedi/{id}") //pegar da variavel do caminho da requisição {id}
    public ResponseEntity<Jedi> getJedi(@PathVariable("id") Long id, HttpResponse response) { // esse id vai ser pego da var acima e transformar no id daqui
        final Jedi jedi = service.findById(id);

        return ResponseEntity.ok(jedi);
    }

    @PostMapping("/api/jedi")
    @ResponseStatus(HttpStatus.CREATED)
    public Jedi createJedi(@Valid @RequestBody Jedi jedi) { // @Valid -> especificação java implementada pelo Hibernate Validator
        //  @RequestBody -> faz com os atributos vão virar o modelo Jedi
        return service.save(jedi);
    }

    @PutMapping("/api/jedi/{id}")
    public ResponseEntity updateJedi(@PathVariable("id") Long id, @Valid @RequestBody Jedi dto) { // @PathVariable serve para pegar um trecho da url que geralmente é dinâmico

        final Jedi jedi = service.update(id, dto);

        return ResponseEntity.ok(jedi);
    }

    @DeleteMapping("/api/jedi/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJedi(@PathVariable("id") Long id) {

        service.delete(id);
    }

}
