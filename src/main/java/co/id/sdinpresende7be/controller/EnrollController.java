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
import co.id.sdinpresende7be.model.Enroll;
import co.id.sdinpresende7be.service.AlumniService;
import co.id.sdinpresende7be.service.EnrollService;
import co.id.sdinpresende7be.service.UploadFileService;

@RequestMapping(value = "/api/enrolls")
@RestController
@CrossOrigin
public class EnrollController {

	@Autowired
	private EnrollService enrollService;

	@Autowired
	private UploadFileService uploadFileService;

	@GetMapping
	public ResponseEntity<Response> getAllEnrolls() {
		List<Enroll> enrolls = enrollService.getAllEnrolls();
		Response response = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (enrolls.isEmpty()) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "Data not found";
		}

		response.setCode(code);
		response.setMessage(message);
		response.setData(enrolls);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/upload")
	public ResponseEntity<Response> uploadFile(@RequestParam int subjectClassroomId, @RequestParam String username,
			@RequestParam MultipartFile file) throws Exception {
		List<?> enrolls = uploadFileService.uploadFileNilai(subjectClassroomId, username, file);
		Response resp = new Response();

		if (!enrolls.isEmpty()) {
			resp.setCode(String.valueOf(HttpStatus.CREATED.value()));
			resp.setMessage("Sucessfully upload");
			resp.setData(enrolls);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

}
