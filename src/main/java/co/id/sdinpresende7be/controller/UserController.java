package co.id.sdinpresende7be.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.id.sdinpresende7be.bean.Response;
import co.id.sdinpresende7be.model.User;
import co.id.sdinpresende7be.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping(value = "/api/users")
@RestController
@CrossOrigin
@Api(description = "for user admin and teacher")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "for admin and teacher registration", notes = "for register teacher, just fill attribute of username, password, role=teacher and name")
	@PostMapping("/register")
	public ResponseEntity<Response> register(@RequestBody User user) throws Exception {
		User newUser = userService.register(user);
		Response resp = new Response();

		if (newUser != null) {
			resp.setCode(String.valueOf(HttpStatus.CREATED.value()));
			resp.setMessage("Sucessfully Register!");
			resp.setData(Arrays.asList(newUser));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	@ApiOperation(value = "for login")
	@GetMapping("/login")
	public ResponseEntity<Response> login(@RequestParam String username, @RequestParam String password) throws Exception {
		User user = userService.login(username, password);
		Response resp = new Response();
		if (user == null) {
			resp.setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
			resp.setMessage("Incorrect username or password!");
			resp.setData(null);
		} else {
			resp.setCode(String.valueOf(HttpStatus.OK.value()));
			resp.setMessage("Sucessfully Login!");
			resp.setData(Arrays.asList(user));
		}
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}

	@ApiOperation(value = "for list of teachers")
	@GetMapping("/teachers")
	public ResponseEntity<Response> getAllTeacher() {
		List<User> teachers = userService.getAllTeachers();
		Response resp = new Response();
		if (teachers.isEmpty()) {
			resp.setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
			resp.setMessage("Data not found");
			resp.setData(null);
		} else {
			resp.setCode(String.valueOf(HttpStatus.OK.value()));
			resp.setMessage(HttpStatus.OK.name());
			resp.setData(teachers);
		}
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}

	@ApiOperation(value = "for get user information by user id")
	@GetMapping("/{id}")
	public ResponseEntity<Response> getUserById(@PathVariable Integer id) {
		User user = userService.getUserById(id);
		Response resp = new Response();
		if (user == null) {
			resp.setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
			resp.setMessage("Data not found");
			resp.setData(null);
		} else {
			resp.setCode(String.valueOf(HttpStatus.OK.value()));
			resp.setMessage(HttpStatus.OK.name());
			resp.setData(Arrays.asList(user));
		}
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}

}
