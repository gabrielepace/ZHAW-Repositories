package ch.zhaw.project.easycts.repositories;

import ch.zhaw.project.easycts.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findBySemester(int id);

    List<Enrollment> findByUserId(long id);
}