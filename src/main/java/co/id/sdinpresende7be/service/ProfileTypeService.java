package co.id.sdinpresende7be.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.sdinpresende7be.model.ProfileType;
import co.id.sdinpresende7be.repo.ProfileTypeRepo;

@Service
public class ProfileTypeService {

	@Autowired
	private ProfileTypeRepo profileTypeRepo;
	
	public List<ProfileType> getAllProfileTypes(){
		return profileTypeRepo.findAll();
	}
	
	public ProfileType getProfileTypeByName(String name) {
		return profileTypeRepo.findByName(name);
	}
}
