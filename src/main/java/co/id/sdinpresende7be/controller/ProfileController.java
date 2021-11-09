package co.id.sdinpresende7be.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
}
