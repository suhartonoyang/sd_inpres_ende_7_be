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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.id.sdinpresende7be.bean.Response;
import co.id.sdinpresende7be.model.Student;
import co.id.sdinpresende7be.service.StudentService;
import co.id.sdinpresende7be.service.UploadFileService;
import io.swagger.annotations.ApiOperation;

@RequestMapping(value = "/api/students")
@RestController
@CrossOrigin
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private UploadFileService uploadFileService;

	@GetMapping
	public ResponseEntity<Response> getAllStudents() {
		List<Student> students = studentService.getAllStudents();
		Response response = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (students.isEmpty()) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "Data not found";
		}

		response.setCode(code);
		response.setMessage(message);
		response.setData(students);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> getStudentById(@PathVariable Integer id) {
		Student student = studentService.getStudentById(id);
		Response response = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (student == null) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "No Data Found";
		}

		response.setCode(code);
		response.setMessage(message);
		response.setData(Arrays.asList(student));

		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "For save student", notes="just fill the attribute of nis, name, gender, createdBy and user(only id)")
	@PostMapping
	public ResponseEntity<Response> saveStudent(@RequestBody Student student) throws Exception {
		Student newStudent = studentService.saveStudent(student);
		Response resp = new Response();

		if (newStudent != null) {
			resp.setCode(String.valueOf(HttpStatus.CREATED.value()));
			resp.setMessage("Sucessfully Save ");
			resp.setData(Arrays.asList(newStudent));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	@PostMapping("/upload")
	public ResponseEntity<Response> uploadFile(@RequestParam String username, @RequestParam MultipartFile file) throws Exception {
		List<?> alumnis = uploadFileService.uploadFile("student", username, file);
		Response resp = new Response();

		if (!alumnis.isEmpty()) {
			resp.setCode(String.valueOf(HttpStatus.CREATED.value()));
			resp.setMessage("Sucessfully upload");
			resp.setData(Arrays.asList(alumnis));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteStudentById(@PathVariable Integer id) {
		studentService.deleteStudentById(id);
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage("Sucessfully Delete");

		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
}
