package com.globallabs.springwebmvc.service;

import com.globallabs.springwebmvc.exception.JediNotFoundException;
import com.globallabs.springwebmvc.model.Jedi;
import com.globallabs.springwebmvc.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JediService {

    @Autowired
    private JediRepository repository;

    public List<Jedi> findAll() {
        return repository.findAll();
    }

    public Jedi findById(Long id) {
        final Optional<Jedi> jedi = repository.findById(id);

        if (jedi.isPresent()) {
            return jedi.get();
        } else {
            throw new JediNotFoundException();
        }


    }

    public Jedi save(final Jedi jedi) {
        return repository.save(jedi);
    }

    public Jedi update(Long id, Jedi dto) {
        final Optional<Jedi> jediEntity = repository.findById(id);
        final Jedi jedi;
        if (jediEntity.isPresent()) {
            jedi = jediEntity.get();
        } else {
            throw new JediNotFoundException();
        }

        jedi.setName(dto.getName());
        jedi.setLastName(dto.getLastName());

        return repository.save(jedi);
    }

    public void delete(final Long id) {
        final Jedi jedi = findById(id);

        repository.delete(jedi);

    }
}
