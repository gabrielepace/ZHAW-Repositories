package ch.zhaw.project.easycts.bootstrap;

import ch.zhaw.project.easycts.model.*;
import ch.zhaw.project.easycts.repositories.EnrollmentRepository;
import ch.zhaw.project.easycts.repositories.ModuleGroupRepository;
import ch.zhaw.project.easycts.repositories.ModuleRepository;
import ch.zhaw.project.easycts.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class is only used at startup to set some dummy values, and checks if the basic functions are working.
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final ModuleRepository moduleRepository;
    private final ModuleGroupRepository moduleGroupRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;


    public BootStrapData(ModuleRepository moduleRepository, EnrollmentRepository enrollmentRepository, UserRepository userRepository, ModuleGroupRepository moduleGroupRepository) {
        this.moduleRepository = moduleRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.moduleGroupRepository = moduleGroupRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        User u = new User();
        u.setPassword("$2a$10$gMS6PeEHYHydqhw3Qze2q.XmA29f.hC0LdHtcVyYpptnmctIn9Rt.");
        u.setUsername("test@easycts.ch");

        Mark m1 = new Mark();
        m1.setMark(3);
        m1.setWeighting(10);
        Mark m2 = new Mark();
        m2.setMark(4);
        m2.setWeighting(30);
        Mark m3 = new Mark();
        m3.setMark(6);
        m3.setWeighting(60);
        List<Mark> marks = new ArrayList<>();
        marks.add(m1);
        marks.add(m2);
        marks.add(m3);

        Module module1 = new Module();
        module1.setName("PSIT3");
        module1.setEcts(4);
        ModuleGroup mg1 = new ModuleGroup();
        mg1.setName("MG1");
        mg1 = moduleGroupRepository.save(mg1);
        module1.setModuleGroup(mg1);
        module1 = moduleRepository.save(module1);

        Enrollment e = new Enrollment();
        u = userRepository.save(u);
        e.setCreator(u);
        e.setSemester(1);
        e.setDispensed(false);
        e.setMarks(marks);
        e.setModule(module1);
        e.setDispensed(true);

        enrollmentRepository.save(e);


        Module module2 = new Module();
        module2.setName("WEB3");
        module2.setEcts(2);
        ModuleGroup mg3 = new ModuleGroup();
        mg3.setName("MG3");
        mg3 = moduleGroupRepository.save(mg3);
        module2.setModuleGroup(mg3);
        module2 = moduleRepository.save(module2);

        m1 = new Mark();
        m1.setMark(4);
        m1.setWeighting(20);
        m2 = new Mark();
        m2.setMark(5);
        m2.setWeighting(20);
        m3 = new Mark();
        m3.setMark(6);
        m3.setWeighting(60);
        marks = new ArrayList<>();
        marks.add(m1);
        marks.add(m2);
        marks.add(m3);


        e = new Enrollment();
        e.setSemester(1);
        e.setDispensed(false);
        e.setMarks(marks);
        e.setModule(module2);
        enrollmentRepository.save(e);


        Module module3 = new Module();
        module3.setName("NUM1");
        module3.setEcts(4);
        ModuleGroup mg2 = new ModuleGroup();
        mg2.setName("MG2");
        mg2 = moduleGroupRepository.save(mg2);
        module3.setModuleGroup(mg2);
        module3 = moduleRepository.save(module3);
        m1 = new Mark();
        m1.setMark(5);
        m1.setWeighting(40);
        m2 = new Mark();
        m2.setMark(5);
        m2.setWeighting(20);
        m3 = new Mark();
        m3.setMark(6);
        m3.setWeighting(40);
        marks = new ArrayList<>();
        marks.add(m1);
        marks.add(m2);
        marks.add(m3);

        e = new Enrollment();
        e.setSemester(1);
        e.setDispensed(false);
        e.setMarks(marks);
        e.setModule(module3);
        enrollmentRepository.save(e);

        System.out.println("Data initialized, " + "number of modules: " + moduleRepository.count());
        System.out.println("Data initialized, " + "number of enrollments: " + enrollmentRepository.count());
    }
}
