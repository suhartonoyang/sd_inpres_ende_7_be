package co.id.sdinpresende7be.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.id.sdinpresende7be.bean.Response;
import co.id.sdinpresende7be.model.Profile;
import co.id.sdinpresende7be.service.ProfileService;

@RequestMapping(value = "/api/profiles")
@RestController
@CrossOrigin
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	@GetMapping
	public ResponseEntity<Response> getAllProfiles() {
		List<Profile> profiles = profileService.getAllProfiles();
		Response response = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (profiles.isEmpty()) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "No Data Found";
		}

		response.setCode(code);
		response.setMessage(message);
		response.setData(profiles);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{profileType}")
	public ResponseEntity<Response> getProfileByProfileType(@PathVariable String profileType) {
		Map<String, Object> map = profileService.getProfileByProfileType(profileType);
		Response response = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (map == null) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "No Data Found";
		}

		response.setCode(code);
		response.setMessage(message);
		response.setData(Arrays.asList(map));

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response> saveProfile(@RequestBody Profile profile) {
		Profile newProfile = profileService.saveProfile(profile);
		Response resp = new Response();

		if (newProfile != null) {
			resp.setCode(String.valueOf(HttpStatus.CREATED.value()));
			resp.setMessage("Sucessfully Save " + profile.getProfileType());
			resp.setData(Arrays.asList(newProfile));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}
}
