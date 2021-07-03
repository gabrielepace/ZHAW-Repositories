package ch.zhaw.project.easycts.services;

import ch.zhaw.project.easycts.model.Enrollment;
import ch.zhaw.project.easycts.repositories.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Override
    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id).get();
    }

    @Override
    public List<Enrollment> getAllEnrollmentsFromUser(long id) {
        return enrollmentRepository.findByUserId(id);
    }

    @Override
    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public void deleteEnrollment(long id) {
        enrollmentRepository.deleteById(id);
    }
}
