package co.id.sdinpresende7be.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import co.id.sdinpresende7be.model.File;
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
		Profile existingProfile = null;
		if (profile.getProfileType().equalsIgnoreCase("fasilitas") || profile.getProfileType().equalsIgnoreCase("prestasi")) {
			existingProfile = getProfileById(profile.getId());
		} else {
			existingProfile = getProfileByProfileTypeNonMap(profile.getProfileType());
		}

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
		newProfile.setDescription(profile.getDescription());
		newProfile.setVision(profile.getVision());
		newProfile.setMission(profile.getMission());
		newProfile.setCategory(profile.getCategory());

		return profileRepo.save(newProfile);
	}

	public Map<String, Object> uploadImage(String profileType, MultipartFile file) throws Exception {
		if (!profileType.equalsIgnoreCase("tentang kami")) {
			throw new Exception("This profile type does not need image");
		}

		Profile profile = getProfileByProfileTypeNonMap(profileType);
		if (profile == null) {
			throw new Exception("Data not found");
		}

		File newFile = new File();
		newFile.setName(StringUtils.cleanPath(file.getOriginalFilename()));
		newFile.setType(file.getContentType());
		newFile.setData(file.getBytes());
		newFile.setProfile(Arrays.asList(profile));

		profile.setFile(newFile);
		profileRepo.save(profile);

		Map<String, Object> profileMap = new HashMap<String, Object>();

		profileMap.put("Title", profile.getTitle());
		profileMap.put("Description", profile.getDescription());
		profileMap.put("ImageUrl", profile.getImageUrl());
		if (profile.getFile() != null) {
			profileMap.put("ImageName", profile.getFile().getName());
		}

		return profileMap;
	}

	public Profile getProfileByProfileTypeNonMap(String profileType) {
		return profileRepo.findByProfileType(profileType).stream().findFirst().orElse(null);
	}

	public Profile getProfileById(Integer id) {
		return profileRepo.findById(id).orElse(null);
	}

	public List<Map<String, Object>> getProfilesByProfileType(String profileType) {
		List<Profile> profiles = profileRepo.findByProfileType(profileType);
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		profiles.stream().forEach(profile -> {
			System.out.println("masuk sini");
			System.out.println(profile);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", profile.getId());
			switch (profileType) {
			case "Tentang Kami":
				map.put("Title", profile.getTitle());
				map.put("Description", profile.getDescription());
				map.put("ImageUrl", profile.getImageUrl());
				if (profile.getFile() != null) {
					map.put("ImageName", profile.getFile().getName());
				}
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
				break;
			}
			
			maps.add(map);
		});

		return maps;

	}

//	public Map<String, Object> getProfileByProfileType(String profileType) {
//		Profile profile = getProfileByProfileTypeNonMap(profileType);
//
//		Map<String, Object> map = new HashMap<String, Object>();
//		switch (profileType) {
//		case "Tentang Kami":
//			map.put("Title", profile.getTitle());
//			map.put("Description", profile.getDescription());
//			map.put("ImageUrl", profile.getImageUrl());
//			if (profile.getFile() != null) {
//				map.put("ImageName", profile.getFile().getName());
//			}
//			break;
//		case "Visi & Misi":
//			map.put("Visi", profile.getVision());
//			map.put("Misi", profile.getMission());
//			break;
//		case "Fasilitas":
//			map.put("Title", profile.getTitle());
//			Date inputDate = profile.getCreatedDate();
//			if (profile.getModifiedDate() != null) {
//				inputDate = profile.getCreatedDate();
//			}
//			map.put("Input Date", inputDate);
//			break;
//		case "Tata Tertib":
//			map.put("Title", profile.getTitle());
//			map.put("Description", profile.getDescription());
//			break;
//		case "Prestasi":
//			map.put("Title", profile.getTitle());
//			map.put("Category", profile.getCategory());
//			Date inputDate1 = profile.getCreatedDate();
//			if (profile.getModifiedDate() != null) {
//				inputDate1 = profile.getCreatedDate();
//			}
//			map.put("Input Date", inputDate1);
//			break;
//		default:
//			return null;
//		}
//
//		return map;
//	}

	public void deleteProfileById(Integer id) {
		profileRepo.deleteById(id);
	}
}
