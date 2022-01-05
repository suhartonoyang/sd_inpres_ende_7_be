package co.id.sdinpresende7be.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.net.HttpHeaders;

import co.id.sdinpresende7be.bean.Response;
import co.id.sdinpresende7be.model.Profile;
import co.id.sdinpresende7be.service.ProfileService;
import io.swagger.annotations.ApiOperation;

@RequestMapping(value = "/api/profiles")
@RestController
@CrossOrigin
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	@ApiOperation(value = "for get all profiles")
	@GetMapping
	public ResponseEntity<Response> getAllProfiles() {
		List<Profile> profiles = profileService.getAllProfiles();
		Response response = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (profiles.isEmpty()) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "Data not found";
		}

		response.setCode(code);
		response.setMessage(message);
		response.setData(profiles);

		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "for getting profile information by id")
	@GetMapping("/{id}")
	public ResponseEntity<Response> getProfileById(@PathVariable Integer id) {
		Profile profile = profileService.getProfileById(id);
		Response response = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (profile == null) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "No Data Found";
		}

		response.setCode(code);
		response.setMessage(message);
		response.setData(Arrays.asList(profile));

		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "for getting profile information by profile type beside fasilitas and prestasi")
	@GetMapping("/profileType/{profileType}")
	public ResponseEntity<Response> getProfileByProfileType(@PathVariable String profileType) {
		List<Map<String, Object>> map = profileService.getProfilesByProfileType(profileType);
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
	
	@ApiOperation(value = "for get image of profile type (Tentang Kami)")
	@GetMapping("/{profileType}/image")
	public ResponseEntity<?> getImage(@PathVariable String profileType) {
		Profile profile = profileService.getProfileByProfileTypeNonMap(profileType);
		if (profile == null) {
			Response response = new Response();
			response.setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
			response.setMessage("No Data Found");
			return ResponseEntity.ok(response);
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; " + "filename=\"" + profile.getFile().getName() + "\"")
				.body(profile.getFile().getData());
	}

	@ApiOperation(value = "for save the profile", notes = "for parameter, just fill attributes based on mapping profile types. remember to empty the file attribute.")
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

	@ApiOperation(value = "for upload the image of profile type (Tentang Kami)", notes = "after call save, call this to upload the image.")
	@PostMapping("/{profileType}/upload")
	public ResponseEntity<Response> uploadImage(@PathVariable String profileType, @RequestParam MultipartFile file) throws Exception {
		Map<String, Object> map = profileService.uploadImage(profileType, file);
		Response resp = new Response();

		if (map != null) {
			resp.setCode(String.valueOf(HttpStatus.CREATED.value()));
			resp.setMessage("Sucessfully Save");
			resp.setData(Arrays.asList(map));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteProfileById(@PathVariable Integer id) {
		profileService.deleteProfileById(id);
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage("Sucessfully Delete");

		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
}
