package com.globallabs.springwebmvc.rest;

import com.globallabs.springwebmvc.exception.JediNotFoundException;
import com.globallabs.springwebmvc.model.Jedi;
import com.globallabs.springwebmvc.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController // recurso, dados a serem acessados, controles rest são chamados de resurces, criar, deletar, atualziar, tudo via rest
public class JediResource {

    @Autowired
    private JediRepository repository;

    @GetMapping("/api/jedai") //forma de acesso a rest
    public List<Jedi> getAllJedi(){
        return repository.findAll();
    }

    @GetMapping("/api/jedai/{id}") //pegar da variavel do caminho da requisição {id}
    public Jedi getJedi(@PathVariable("id") Long id){ // esse id vai ser pego da var acima e transformar no id daqui
        final Optional<Jedi> jedi = repository.findById(id);
        if(jedi.isPresent()){
            return jedi.get();
        }else{
            throw new JediNotFoundException();
        }

    }


}
