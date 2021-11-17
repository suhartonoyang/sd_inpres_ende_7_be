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
import co.id.sdinpresende7be.model.Classroom;
import co.id.sdinpresende7be.service.ClassroomService;
import io.swagger.annotations.ApiOperation;

@RequestMapping(value = "/api/classrooms")
@RestController
@CrossOrigin
public class ClassroomController {

	@Autowired
	private ClassroomService classroomService;

	@GetMapping
	public ResponseEntity<Response> getAllClassrooms() {
		List<Classroom> classrooms = classroomService.getAllClassrooms();
		Response response = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (classrooms.isEmpty()) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "Data not found";
		}

		response.setCode(code);
		response.setMessage(message);
		response.setData(classrooms);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> getClassroomById(@PathVariable Integer id) {
		Classroom classroom = classroomService.getClassroomById(id);
		Response response = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (classroom == null) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "No Data Found";
		}

		response.setCode(code);
		response.setMessage(message);
		response.setData(Arrays.asList(classroom));

		return ResponseEntity.ok(response);
	}

	@ApiOperation(notes = "just fill attributes of createdBy, name and user (just id) ", value = "for save classroom")
	@PostMapping
	public ResponseEntity<Response> saveClassroom(@RequestBody Classroom classroom) throws Exception {
		Classroom newClassroom = classroomService.saveClassroom(classroom);
		Response resp = new Response();

		if (newClassroom != null) {
			resp.setCode(String.valueOf(HttpStatus.CREATED.value()));
			resp.setMessage("Sucessfully Save");
			resp.setData(Arrays.asList(newClassroom));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteClassroomById(@PathVariable Integer id) {
		classroomService.deleteClassroomById(id);
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage("Sucessfully Delete");

		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
}
