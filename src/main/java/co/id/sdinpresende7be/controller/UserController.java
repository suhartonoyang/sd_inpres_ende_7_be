package co.id.sdinpresende7be.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.id.sdinpresende7be.bean.Response;
import co.id.sdinpresende7be.model.User;
import co.id.sdinpresende7be.service.UserService;

@RequestMapping(value = "/api/users")
@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<Response> getAllUsers() {
		List<User> users = userService.getAllUsers();
		Response resp = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (users.isEmpty()) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "No Data Found";
		}

		resp.setCode(code);
		resp.setMessage(message);
		resp.setData(users);

		return ResponseEntity.ok(resp);
	}

	@PostMapping
	public ResponseEntity<Response> register(@RequestBody User user) throws Exception {
		User newUser = userService.register(user.getUsername(), user.getPassword());
		Response resp = new Response();

		if (newUser != null) {
			resp.setCode(String.valueOf(HttpStatus.CREATED.value()));
			resp.setMessage("Sucessfully Register!");
			resp.setData(Arrays.asList(newUser));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}
}
