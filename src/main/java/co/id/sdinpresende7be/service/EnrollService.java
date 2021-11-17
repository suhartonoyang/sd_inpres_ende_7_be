package co.id.sdinpresende7be.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.sdinpresende7be.model.Enroll;
import co.id.sdinpresende7be.repo.EnrollRepo;

@Service
public class EnrollService {

	@Autowired
	private EnrollRepo enrollRepo;
	
	public List<Enroll> getAllEnrolls(){
		return enrollRepo.findAll();
	}
}
