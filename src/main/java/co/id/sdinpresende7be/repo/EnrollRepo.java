package co.id.sdinpresende7be.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.id.sdinpresende7be.model.Enroll;
import co.id.sdinpresende7be.model.SubjectClassroom;

public interface EnrollRepo extends JpaRepository<Enroll, Integer> {

	Integer deleteBysubjectClassroom(SubjectClassroom subjectClassroom);
}
