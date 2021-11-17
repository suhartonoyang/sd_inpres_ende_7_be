package co.id.sdinpresende7be.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.sdinpresende7be.model.Classroom;
import co.id.sdinpresende7be.model.User;
import co.id.sdinpresende7be.repo.ClassroomRepo;
import co.id.sdinpresende7be.repo.UserRepo;

@Service
public class ClassroomService {

	@Autowired
	private ClassroomRepo classroomRepo;
	
	@Autowired
	private UserRepo userRepo;

	public List<Classroom> getAllClassrooms() {
		return classroomRepo.findAll();
	}

	public Classroom getClassroomById(Integer id) {
		return classroomRepo.findById(id).orElse(null);
	}

	public void deleteClassroomById(Integer id) {
		classroomRepo.deleteById(id);
	}

	public Classroom saveClassroom(Classroom classroom) throws Exception {

		Classroom newClassroom = new Classroom();

		Classroom existingClassroom = getClassroomById(classroom.getId());
		if (existingClassroom == null) {
			newClassroom.setCreatedBy(classroom.getCreatedBy());
			newClassroom.setCreatedDate(new Date());
		} else {
			newClassroom.setId(classroom.getId());
			newClassroom.setCreatedBy(classroom.getCreatedBy());
			newClassroom.setCreatedDate(classroom.getCreatedDate());
			newClassroom.setModifiedBy(classroom.getCreatedBy());
			newClassroom.setModifiedDate(new Date());
		}
		
		User teacher = userRepo.findById(classroom.getTeacher().getId()).orElse(null);
		if (teacher==null) {
			throw new Exception("Teacher not found");
		}else {
			if(!teacher.getRole().equalsIgnoreCase("teacher"))
				throw new Exception("User is not a teacher");
		}
		
		newClassroom.setName(classroom.getName());
		newClassroom.setTeacher(teacher);
		return classroomRepo.save(newClassroom);
	}
}
