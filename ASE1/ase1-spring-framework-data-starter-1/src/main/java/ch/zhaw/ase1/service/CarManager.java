package ch.zhaw.ase1.service;



import ch.zhaw.ase1.model.CarEntity;
import ch.zhaw.ase1.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CarManager {

    @Autowired
    CarRepository repository;

    public void manage()
    {
        System.out.println("Finding Car by Model Name: R8");
        for (CarEntity carEntity : repository.findByModel("R8")) {
            System.out.println(carEntity);
        }

        System.out.println("Finding Cars of Owner: Felix");
        for (CarEntity carEntity : repository.findByOwnerName("Felix")) {
            System.out.println(carEntity);
        }
    }

}
