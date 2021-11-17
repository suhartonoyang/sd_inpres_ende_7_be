package co.id.sdinpresende7be.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.sdinpresende7be.model.Alumni;
import co.id.sdinpresende7be.repo.AlumniRepo;

@Service
public class AlumniService {

	@Autowired
	private AlumniRepo alumniRepo;

	public List<Alumni> getAllAlumnis() {
		return alumniRepo.findAll();
	}

	public Alumni getAlumniById(Integer id) {
		return alumniRepo.findById(id).orElse(null);
	}

	public Alumni saveAlumni(Alumni alumni) {

		Alumni newAlumni = new Alumni();

		Alumni existingAlumni = getAlumniById(alumni.getId());

		if (existingAlumni == null) {
			newAlumni.setCreatedBy(alumni.getCreatedBy());
			newAlumni.setCreatedDate(new Date());
		} else {
			newAlumni.setId(alumni.getId());
			newAlumni.setCreatedBy(existingAlumni.getCreatedBy());
			newAlumni.setCreatedDate(existingAlumni.getCreatedDate());
			newAlumni.setModifiedBy(alumni.getCreatedBy());
			newAlumni.setModifiedDate(new Date());
		}

		newAlumni.setNis(alumni.getNis());
		newAlumni.setName(alumni.getName());
		newAlumni.setGender(alumni.getGender());
		newAlumni.setGraduationYear(alumni.getGraduationYear());
		newAlumni.setLastEducation(alumni.getLastEducation());

		return alumniRepo.save(newAlumni);
	}
	
	public void deleteAlumniByid(Integer id) {
		alumniRepo.deleteById(id);
	}

}
