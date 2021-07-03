package ch.zhaw.project.easycts.repositories;

import ch.zhaw.project.easycts.model.ModuleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleGroupRepository extends JpaRepository<ModuleGroup, Long> {
    ModuleGroup findByName(String name);
}