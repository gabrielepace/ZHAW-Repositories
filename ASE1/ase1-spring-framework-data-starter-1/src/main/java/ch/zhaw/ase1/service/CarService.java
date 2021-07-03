package ch.zhaw.ase1.service;

import java.util.List;

import ch.zhaw.ase1.model.CarEntity;
import ch.zhaw.ase1.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.NESTED)
public class CarService {

    @Autowired
    private CarRepository repository;

    public CarService() {
        super();
    }


    public CarEntity save(final CarEntity entity) {
        return repository.save(entity);
    }

    public CarEntity findOne(final Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Iterable<CarEntity> findAll() {
        return repository.findAll();
    }

    public List<CarEntity> findByModel(final String model) {
        return repository.findByModel(model);
    }

    public List<CarEntity> findByOwnerName(final String owner) {
        return repository.findByOwnerName(owner);
    }

}
