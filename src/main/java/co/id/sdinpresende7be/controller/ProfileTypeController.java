package co.id.sdinpresende7be.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.id.sdinpresende7be.bean.Response;
import co.id.sdinpresende7be.model.ProfileType;
import co.id.sdinpresende7be.service.ProfileTypeService;

@RequestMapping(value = "/api/profileTypes")
@RestController
@CrossOrigin
public class ProfileTypeController {

	@Autowired
	private ProfileTypeService profileTypeService;

	@GetMapping
	public ResponseEntity<Response> getAllProfileTypes() {
		List<ProfileType> profileTypes = profileTypeService.getAllProfileTypes();
		Response response = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (profileTypes.isEmpty()) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "No Data Found";
		}

		response.setCode(code);
		response.setMessage(message);
		response.setData(profileTypes);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{name}")
	public ResponseEntity<Response> getProfileTypeByName(@PathVariable String name) {
		ProfileType profileType = profileTypeService.getProfileTypeByName(name);
		Response response = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (profileType == null) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "No Data Found";
		}

		response.setCode(code);
		response.setMessage(message);
		response.setData(Arrays.asList(profileType));

		return ResponseEntity.ok(response);
	}
}
