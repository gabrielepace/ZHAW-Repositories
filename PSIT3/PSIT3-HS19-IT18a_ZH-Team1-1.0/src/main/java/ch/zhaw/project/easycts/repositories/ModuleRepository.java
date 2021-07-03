package ch.zhaw.project.easycts.repositories;

import ch.zhaw.project.easycts.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

}