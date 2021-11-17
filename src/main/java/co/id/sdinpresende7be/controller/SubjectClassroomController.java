package co.id.sdinpresende7be.controller;

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
import org.springframework.web.bind.annotation.RestController;

import co.id.sdinpresende7be.bean.Response;
import co.id.sdinpresende7be.model.SubjectClassroom;
import co.id.sdinpresende7be.service.SubjectClassroomService;
import io.swagger.annotations.ApiOperation;

@RequestMapping(value = "/api/subjects")
@RestController
@CrossOrigin
public class SubjectClassroomController {

	@Autowired
	private SubjectClassroomService subjectClassroomService;

	@GetMapping
	public ResponseEntity<Response> getAllSubjectClassrooms() {
		List<SubjectClassroom> subjectClassrooms = subjectClassroomService.getAllSubjectClassrooms();
		Response response = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (subjectClassrooms.isEmpty()) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "Data not found";
		}

		response.setCode(code);
		response.setMessage(message);
		response.setData(subjectClassrooms);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> getSubjectClassroomById(@PathVariable Integer id) {
		SubjectClassroom subjectClassroom = subjectClassroomService.getSubjectClassroomById(id);
		Response response = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (subjectClassroom == null) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "No Data Found";
		}

		response.setCode(code);
		response.setMessage(message);
		response.setData(Arrays.asList(subjectClassroom));

		return ResponseEntity.ok(response);
	}

	@ApiOperation(notes = "just fill attributes of createdBy, name, classroom(just id), and user (just id) ", value = "for save subjects")
	@PostMapping
	public ResponseEntity<Response> saveSubjectClassroom(@RequestBody SubjectClassroom subjectClassroom) throws Exception {
		SubjectClassroom newSubjectClassroom = subjectClassroomService.saveSubjectClassroom(subjectClassroom);
		Response resp = new Response();

		if (newSubjectClassroom != null) {
			resp.setCode(String.valueOf(HttpStatus.CREATED.value()));
			resp.setMessage("Sucessfully Save");
			resp.setData(Arrays.asList(newSubjectClassroom));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteSubjectClassroomById(@PathVariable Integer id) {
		subjectClassroomService.deleteSubjectClassroomByid(id);
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage("Sucessfully Delete");

		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
}
