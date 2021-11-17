package co.id.sdinpresende7be.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.sdinpresende7be.model.Classroom;
import co.id.sdinpresende7be.model.SubjectClassroom;
import co.id.sdinpresende7be.model.User;
import co.id.sdinpresende7be.repo.ClassroomRepo;
import co.id.sdinpresende7be.repo.SubjectClassroomRepo;
import co.id.sdinpresende7be.repo.UserRepo;

@Service
public class SubjectClassroomService {

	@Autowired
	private SubjectClassroomRepo subjectClassroomRepo;

	@Autowired
	private ClassroomRepo classroomRepo;

	@Autowired
	private UserRepo userRepo;

	public List<SubjectClassroom> getAllSubjectClassrooms() {
		return subjectClassroomRepo.findAll();
	}

	public SubjectClassroom getSubjectClassroomById(Integer id) {
		return subjectClassroomRepo.findById(id).orElse(null);
	}

	public SubjectClassroom saveSubjectClassroom(SubjectClassroom subjectClassroom) throws Exception {

		SubjectClassroom newSubjectClassroom = new SubjectClassroom();

		newSubjectClassroom.setCreatedBy(subjectClassroom.getCreatedBy());
		newSubjectClassroom.setCreatedDate(new Date());

		Classroom classroom = classroomRepo.findById(subjectClassroom.getClassroom().getId()).orElse(null);
		if (classroom == null) {
			throw new Exception("Classroom not found");
		}

		User teacher = userRepo.findById(subjectClassroom.getTeacher().getId()).orElse(null);
		if (teacher == null) {
			throw new Exception("Teacher not found");
		}

		newSubjectClassroom.setName(subjectClassroom.getName());
		newSubjectClassroom.setClassroom(classroom);
		newSubjectClassroom.setTeacher(teacher);

		return subjectClassroomRepo.save(newSubjectClassroom);
	}

	public void deleteSubjectClassroomByid(Integer id) {
		subjectClassroomRepo.deleteById(id);
	}

}
