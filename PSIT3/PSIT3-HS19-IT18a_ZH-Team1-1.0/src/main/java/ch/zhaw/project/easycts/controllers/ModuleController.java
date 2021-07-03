package ch.zhaw.project.easycts.controllers;

import ch.zhaw.project.easycts.DTO.CreateModule;
import ch.zhaw.project.easycts.DTO.EnrollmentDTO;
import ch.zhaw.project.easycts.DTO.ModuleList;
import ch.zhaw.project.easycts.DTO.SemesterDTO;
import ch.zhaw.project.easycts.model.*;
import ch.zhaw.project.easycts.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

/**
 * The {@link ModuleController} is the REST interface for all module related requests from external applications
 */
@RestController
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private ModuleGroupService moduleGroupService;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    /**
     * Returns a {@link ModuleList} with all {@link ModuleGroup} and {@link Enrollment} of the given {@link User}
     * The token is used to determine which user is logged in.
     */
    @CrossOrigin
    @PostMapping("/modules")
    public ModuleList getAllModules(@RequestBody String token) {
        User u = tokenService.getUserByToken(token);

        Set<String> moduleGroupDTOList = new HashSet<>();
        List<SemesterDTO> semesterDTOList = new ArrayList<>();
        Map<Integer, SemesterDTO> hashMap = new HashMap<>();

        List<Enrollment> enrollments = enrollmentService.getAllEnrollmentsFromUser(u.getId());
        SemesterDTO semester;

        // create Semester structure with enrollments
        for (Enrollment enrollment : enrollments) {
            if (hashMap.containsKey(enrollment.getSemester())) {
                semester = hashMap.get(enrollment.getSemester());
            } else {
                semester = new SemesterDTO(enrollment.getSemester());
                semester.setSemesterId(enrollment.getSemester());
            }
            EnrollmentDTO enrollmentDTO = new EnrollmentDTO(enrollment.getModule().getName(),
                    enrollment.getMarks(),
                    enrollment.getModule().getEcts(),
                    enrollment.getModule().getModuleGroup().getName(),
                    enrollment.getEnrollmentId(),
                    enrollment.isDispensed());
            semester.addEnrollment(enrollmentDTO);
            hashMap.put(semester.getSemesterId(), semester);

            // create the modulegroups
            String mgName = enrollment.getModule().getModuleGroup().getName();
            moduleGroupDTOList.add(mgName);
        }

        // fill all semesters into the list for transfer
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry entry = (HashMap.Entry) it.next();
            semesterDTOList.add((SemesterDTO) entry.getValue());
            it.remove();
        }

        ModuleList moduleList = new ModuleList(semesterDTOList, moduleGroupDTOList);
        System.out.println(moduleList);
        return moduleList;
    }

    /**
     * This method deletes the {@link Enrollment} with the given ID, NOT THE {@link Module}!!!
     * The naming comes from a users perspective where he deletes the "module", which is an enrollment in backend
     *
     * @param id  id of enrollment
     * @return string message
     */
    @CrossOrigin
    @PostMapping("/module/delete/{id}")
    public String deleteModule(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
        return "Enrollment deleted";
    }

    /**
     * Creates an {@link Enrollment}, {@link Module}, {@link Mark}, {@link ModuleGroup} from the given
     * {@link CreateModule} if the classes are not already existing. In the other case they merge the data.
     *
     * @param createModule    information about the enrollment
     * @return string message
     */
    @CrossOrigin
    @PostMapping("/module")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveModule(@RequestBody CreateModule createModule) {
        Enrollment enrollment = new Enrollment();
        Module module = new Module();
        ModuleGroup moduleGroup = new ModuleGroup();
        User user;

        if (createModule.getEnrollmentId() != null) {
            enrollment = enrollmentService.getEnrollmentById(createModule.getEnrollmentId());
            module = enrollment.getModule();
            user = enrollment.getCreator();
        } else {
            user = tokenService.getUserByToken(createModule.getToken());
            user = userService.getUser(user.getUsername());
        }

        ModuleGroup existingModuleGroup = moduleGroupService.getModuleGroupByName(createModule.getModulegroup());
        if (existingModuleGroup != null) {
            moduleGroup = existingModuleGroup;
        } else {
            moduleGroup.setName(createModule.getModulegroup());
        }

        module.setName(createModule.getName());
        module.setEcts(Integer.valueOf(createModule.getEcts()));
        moduleGroup = moduleGroupService.saveModuleGroup(moduleGroup);
        module.setModuleGroup(moduleGroup);

        enrollment.setSemester(createModule.getSemester());
        enrollment.setCreator(user);
        enrollment.setDispensed(createModule.isDispensed());

        module = moduleService.saveModule(module);
        enrollment.setModule(module);
        enrollment.setCreator(user);
        enrollment.setMarks(createModule.getMarks());
        // Springboot JPA distinguish between update and save with the merge operation.
        // This means, as soon as there is an ID(PK) already existing, it will update
        enrollmentService.saveEnrollment(enrollment);

        return "Module created";
    }
}
