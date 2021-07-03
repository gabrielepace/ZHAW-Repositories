package ch.zhaw.ase1.service;

import ch.zhaw.ase1.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.zhaw.ase1.model.PersonEntity;

@Service
@Transactional(propagation = Propagation.NESTED)
public class PersonService {
    @Autowired
    private PersonRepository repository;

    public PersonService() {
        super();
    }

     public PersonEntity save(final PersonEntity entity) {
        return repository.save(entity);
    }

    public PersonEntity findOne(final Integer id) {
        return repository.findById(id).orElse(null);
    }

    public PersonEntity findOne(final Long id) {
        return repository.findById((int)(long)id).orElse(null);
    }

    public Iterable<PersonEntity> findAll() {
        return repository.findAll();
    }
}
