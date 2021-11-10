package co.id.sdinpresende7be.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@PostMapping("/register")
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
}
