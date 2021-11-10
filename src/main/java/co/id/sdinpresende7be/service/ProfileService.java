package co.id.sdinpresende7be.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.sdinpresende7be.model.Profile;
import co.id.sdinpresende7be.repo.ProfileRepo;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepo profileRepo;

	public List<Profile> getAllProfiles() {
		return profileRepo.findAll();
	}

	public Profile saveProfile(Profile profile) {

		Profile newProfile = new Profile();

		// check profile sudah ada belum
		Profile existingProfile = profileRepo.findByProfileType(profile.getProfileType());
		if (existingProfile == null) {
			newProfile.setCreatedBy(profile.getCreatedBy());
			newProfile.setCreatedDate(new Date());
		} else {
			newProfile.setId(existingProfile.getId());
			newProfile.setCreatedBy(existingProfile.getCreatedBy());
			newProfile.setCreatedDate(existingProfile.getCreatedDate());
			newProfile.setModifiedBy(profile.getCreatedBy());
			newProfile.setModifiedDate(new Date());
		}

		newProfile.setProfileType(profile.getProfileType());
		newProfile.setTitle(profile.getTitle());
		newProfile.setImage(profile.getImage());
		newProfile.setDescription(profile.getDescription());
		newProfile.setVision(profile.getVision());
		newProfile.setMission(profile.getMission());
		newProfile.setCategory(profile.getCategory());

		return profileRepo.save(newProfile);
	}

	public Map<String, Object> getProfileByProfileType(String profileType) {
		Profile profile = profileRepo.findByProfileType(profileType);

		Map<String, Object> map = new HashMap<String, Object>();
		switch (profileType) {
		case "Tentang Kami":
			map.put("Title", profile.getTitle());
			map.put("Image", profile.getImage());
			map.put("Description", profile.getDescription());
			break;
		case "Visi & Misi":
			map.put("Visi", profile.getVision());
			map.put("Misi", profile.getMission());
			break;
		case "Fasilitas":
			map.put("Title", profile.getTitle());
			Date inputDate = profile.getCreatedDate();
			if (profile.getModifiedDate() != null) {
				inputDate = profile.getCreatedDate();
			}
			map.put("Input Date", inputDate);
			break;
		case "Tata Tertib":
			map.put("Title", profile.getTitle());
			map.put("Description", profile.getDescription());
			break;
		case "Prestasi":
			map.put("Title", profile.getTitle());
			map.put("Category", profile.getCategory());
			Date inputDate1 = profile.getCreatedDate();
			if (profile.getModifiedDate() != null) {
				inputDate1 = profile.getCreatedDate();
			}
			map.put("Input Date", inputDate1);
			break;
		default:
			return null;
		}

		return map;
	}
}
