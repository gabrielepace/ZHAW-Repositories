package ch.zhaw.project.easycts.services;

import ch.zhaw.project.easycts.model.Module;
import ch.zhaw.project.easycts.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public Module saveModule(Module module) {
        return moduleRepository.save(module);
    }

}
