package co.id.sdinpresende7be.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.sdinpresende7be.model.Profile;
import co.id.sdinpresende7be.repo.ProfileRepo;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepo profileRepo;
	
	public List<Profile> getAllProfiles(){
		return profileRepo.findAll();
	}
}
