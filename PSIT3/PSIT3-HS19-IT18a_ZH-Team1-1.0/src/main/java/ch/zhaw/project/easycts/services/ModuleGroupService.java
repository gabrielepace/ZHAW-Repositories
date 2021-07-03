package ch.zhaw.project.easycts.services;

import ch.zhaw.project.easycts.model.ModuleGroup;

public interface ModuleGroupService {

    ModuleGroup getModuleGroupByName(String name);

    ModuleGroup saveModuleGroup(ModuleGroup moduleGroup);
}
