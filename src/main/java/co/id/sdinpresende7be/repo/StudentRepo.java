package co.id.sdinpresende7be.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import co.id.sdinpresende7be.model.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {
	
	public Student findByNis(int nis);
}
