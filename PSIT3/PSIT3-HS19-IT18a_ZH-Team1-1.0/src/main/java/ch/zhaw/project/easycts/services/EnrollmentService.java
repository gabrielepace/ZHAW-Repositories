package ch.zhaw.project.easycts.services;

import ch.zhaw.project.easycts.model.Enrollment;

import java.util.List;

public interface EnrollmentService {

    Enrollment getEnrollmentById(Long id);

    List<Enrollment> getAllEnrollmentsFromUser(long id);

    Enrollment saveEnrollment(Enrollment enrollment);

    void deleteEnrollment(long id);

}
