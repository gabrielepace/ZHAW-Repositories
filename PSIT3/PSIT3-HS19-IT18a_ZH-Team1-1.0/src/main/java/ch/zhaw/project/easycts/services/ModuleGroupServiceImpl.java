package ch.zhaw.project.easycts.services;

import ch.zhaw.project.easycts.model.ModuleGroup;
import ch.zhaw.project.easycts.repositories.ModuleGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleGroupServiceImpl implements ModuleGroupService {

    @Autowired
    private ModuleGroupRepository moduleGroupRepository;

    @Override
    public ModuleGroup getModuleGroupByName(String name) {
        return moduleGroupRepository.findByName(name);
    }

    @Override
    public ModuleGroup saveModuleGroup(ModuleGroup moduleGroup) {
        return moduleGroupRepository.save(moduleGroup);
    }

}
