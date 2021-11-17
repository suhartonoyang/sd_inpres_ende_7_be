package co.id.sdinpresende7be.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.sdinpresende7be.model.Student;
import co.id.sdinpresende7be.model.User;
import co.id.sdinpresende7be.repo.StudentRepo;
import co.id.sdinpresende7be.repo.UserRepo;

@Service
public class StudentService {

	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	private UserRepo userRepo;

	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}

	public Student getStudentById(Integer id) {
		return studentRepo.findById(id).orElse(null);
	}

	public Student saveStudent(Student student) throws Exception {

		Student newStudent = new Student();

		Student existingStudent = getStudentById(student.getId());

		if (existingStudent == null) {
			newStudent.setCreatedBy(student.getCreatedBy());
			newStudent.setCreatedDate(new Date());
		} else {
			newStudent.setId(student.getId());
			newStudent.setCreatedBy(existingStudent.getCreatedBy());
			newStudent.setCreatedDate(existingStudent.getCreatedDate());
			newStudent.setModifiedBy(student.getCreatedBy());
			newStudent.setModifiedDate(new Date());
		}

		newStudent.setNis(student.getNis());
		newStudent.setName(student.getName());
		newStudent.setGender(student.getGender());
		newStudent.setGuardian(student.getGuardian());

		User teacher = userRepo.findById(student.getTeacher().getId()).orElse(null);
		if (teacher==null) {
			throw new Exception("Teacher not found");
		}
		
		newStudent.setTeacher(teacher);

		return studentRepo.save(newStudent);
	}

	public void deleteStudentById(Integer id) {
		studentRepo.deleteById(id);
	}

}
