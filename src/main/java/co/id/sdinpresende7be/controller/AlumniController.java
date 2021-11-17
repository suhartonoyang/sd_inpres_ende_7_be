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
import co.id.sdinpresende7be.model.Alumni;
import co.id.sdinpresende7be.service.AlumniService;
import co.id.sdinpresende7be.service.UploadFileService;

@RequestMapping(value = "/api/alumnis")
@RestController
@CrossOrigin
public class AlumniController {

	@Autowired
	private AlumniService alumniService;

	@Autowired
	private UploadFileService uploadFileService;

	@GetMapping
	public ResponseEntity<Response> getAllAlumnis() {
		List<Alumni> alumnis = alumniService.getAllAlumnis();
		Response response = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (alumnis.isEmpty()) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "Data not found";
		}

		response.setCode(code);
		response.setMessage(message);
		response.setData(alumnis);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> getAlumniById(@PathVariable Integer id) {
		Alumni alumni = alumniService.getAlumniById(id);
		Response response = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (alumni == null) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "No Data Found";
		}

		response.setCode(code);
		response.setMessage(message);
		response.setData(Arrays.asList(alumni));

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response> saveAlumni(@RequestBody Alumni alumni) {
		Alumni newAlumni = alumniService.saveAlumni(alumni);
		Response resp = new Response();

		if (newAlumni != null) {
			resp.setCode(String.valueOf(HttpStatus.CREATED.value()));
			resp.setMessage("Sucessfully Save ");
			resp.setData(Arrays.asList(newAlumni));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	@PostMapping("/upload")
	public ResponseEntity<Response> uploadFile(@RequestParam String username, @RequestParam MultipartFile file) throws Exception {
		List<?> alumnis = uploadFileService.uploadFile("alumni", username, file);
		Response resp = new Response();

		if (!alumnis.isEmpty()) {
			resp.setCode(String.valueOf(HttpStatus.CREATED.value()));
			resp.setMessage("Sucessfully upload");
			resp.setData(Arrays.asList(alumnis));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteAlumniById(@PathVariable Integer id) {
		alumniService.deleteAlumniByid(id);
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage("Sucessfully Delete");

		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
}
