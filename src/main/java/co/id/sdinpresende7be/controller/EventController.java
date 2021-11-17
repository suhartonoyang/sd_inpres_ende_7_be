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

import com.google.common.net.HttpHeaders;

import co.id.sdinpresende7be.bean.Response;
import co.id.sdinpresende7be.model.Event;
import co.id.sdinpresende7be.service.EventService;

@RequestMapping(value = "/api/events")
@RestController
@CrossOrigin
public class EventController {

	@Autowired
	private EventService eventService;

	@GetMapping
	public ResponseEntity<Response> getAllEvents() {
		List<Event> events = eventService.getAllEvents();
		Response response = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (events.isEmpty()) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "Data not found";
		}

		response.setCode(code);
		response.setMessage(message);
		response.setData(events);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> getEventById(@PathVariable Integer id) {
		Event event = eventService.getEventById(id);
		Response response = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (event == null) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "No Data Found";
		}

		response.setCode(code);
		response.setMessage(message);
		response.setData(Arrays.asList(event));

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}/image")
	public ResponseEntity<?> getImage(@PathVariable Integer id) {
		Event event = eventService.getEventById(id);
		if (event == null) {
			Response response = new Response();
			response.setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
			response.setMessage("No Data Found");
			return ResponseEntity.ok(response);
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; " + "filename=\"" + event.getFile().getName() + "\"")
				.body(event.getFile().getData());
	}

	@PostMapping
	public ResponseEntity<Response> saveEvent(@RequestBody Event event) throws IOException {
		Event newEvent = eventService.saveEvent(event);
		Response resp = new Response();

		if (newEvent != null) {
			resp.setCode(String.valueOf(HttpStatus.CREATED.value()));
			resp.setMessage("Sucessfully Save");
			resp.setData(Arrays.asList(newEvent));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	@PostMapping("/{id}/upload")
	public ResponseEntity<Response> uploadImage(@PathVariable Integer id, @RequestParam MultipartFile file) throws Exception {
		Event updateEvent = eventService.uploadImage(id, file);
		Response resp = new Response();

		if (updateEvent != null) {
			resp.setCode(String.valueOf(HttpStatus.CREATED.value()));
			resp.setMessage("Sucessfully Save");
			resp.setData(Arrays.asList(updateEvent));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteEventById(@PathVariable Integer id) {
		eventService.deleteEventById(id);
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage("Sucessfully Delete");

		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
}
