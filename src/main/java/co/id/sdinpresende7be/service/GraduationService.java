package co.id.sdinpresende7be.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.sdinpresende7be.model.Graduation;
import co.id.sdinpresende7be.repo.GraduationRepo;

@Service
public class GraduationService {

	@Autowired
	private GraduationRepo graduationRepo;

	public List<Graduation> getAllGraduations() {
		return graduationRepo.findAll();
	}

	public Graduation getGraduationById(Integer id) {
		return graduationRepo.findById(id).orElse(null);
	}

	public Graduation saveGraduation(Graduation graduation) {

		Graduation newGraduation = new Graduation();

		Graduation existingGraduation = getGraduationById(graduation.getId());

		if (existingGraduation == null) {
			newGraduation.setCreatedBy(graduation.getCreatedBy());
			newGraduation.setCreatedDate(new Date());
		} else {
			newGraduation.setId(graduation.getId());
			newGraduation.setCreatedBy(existingGraduation.getCreatedBy());
			newGraduation.setCreatedDate(existingGraduation.getCreatedDate());
			newGraduation.setModifiedBy(graduation.getCreatedBy());
			newGraduation.setModifiedDate(new Date());
		}

		newGraduation.setNis(graduation.getNis());
		newGraduation.setName(graduation.getName());
		newGraduation.setGender(graduation.getGender());
		newGraduation.setYear(graduation.getYear());
		newGraduation.setNextEducation(graduation.getNextEducation());

		return graduationRepo.save(newGraduation);
	}

	public void deleteAlumniByid(Integer id) {
		graduationRepo.deleteById(id);
	}

}
