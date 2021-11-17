package co.id.sdinpresende7be.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

import co.id.sdinpresende7be.bean.Response;
import co.id.sdinpresende7be.model.Graduation;
import co.id.sdinpresende7be.service.GraduationService;
import co.id.sdinpresende7be.service.UploadFileService;

@RequestMapping(value = "/api/graduations")
@RestController
@CrossOrigin
public class GraduationController {

	@Autowired
	private GraduationService graduationService;

	@Autowired
	private UploadFileService uploadFileService;

	@GetMapping
	public ResponseEntity<Response> getAllGraduations() {
		List<Graduation> graduations = graduationService.getAllGraduations();
		Response response = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (graduations.isEmpty()) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "Data not found";
		}

		response.setCode(code);
		response.setMessage(message);
		response.setData(graduations);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> getGraduationById(@PathVariable Integer id) {
		Graduation graduation = graduationService.getGraduationById(id);
		Response response = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (graduation == null) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "No Data Found";
		}

		response.setCode(code);
		response.setMessage(message);
		response.setData(Arrays.asList(graduation));

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response> saveGraduation(@RequestBody Graduation graduation) {
		Graduation newGraduation = graduationService.saveGraduation(graduation);
		Response resp = new Response();

		if (newGraduation != null) {
			resp.setCode(String.valueOf(HttpStatus.CREATED.value()));
			resp.setMessage("Sucessfully Save ");
			resp.setData(Arrays.asList(newGraduation));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	@PostMapping("/upload")
	public ResponseEntity<Response> uploadFile(@RequestParam String username, @RequestParam MultipartFile file) throws Exception {
		List<?> alumnis = uploadFileService.uploadFile("graduation", username, file);
		Response resp = new Response();

		if (!alumnis.isEmpty()) {
			resp.setCode(String.valueOf(HttpStatus.CREATED.value()));
			resp.setMessage("Sucessfully upload");
			resp.setData(Arrays.asList(alumnis));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteGraduationById(@PathVariable Integer id) {
		graduationService.deleteAlumniByid(id);
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage("Sucessfully Delete");

		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
}
