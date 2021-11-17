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
import co.id.sdinpresende7be.model.CriticsmSuggestion;
import co.id.sdinpresende7be.service.CriticsmSuggestionService;

@RequestMapping(value = "/api/CriticsmSuggestions")
@RestController
@CrossOrigin
public class CriticsmSuggestionController {

	@Autowired
	private CriticsmSuggestionService criticsmSuggestionService;

	@GetMapping
	public ResponseEntity<Response> getAllCriticsmSuggestions() {
		List<CriticsmSuggestion> criticsmSuggestions = criticsmSuggestionService.getAllCriticsmSuggestions();
		Response response = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (criticsmSuggestions.isEmpty()) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "Data not found";
		}

		response.setCode(code);
		response.setMessage(message);
		response.setData(criticsmSuggestions);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> getCriticsmSuggestionsById(@PathVariable Integer id) {
		CriticsmSuggestion criticsmSuggestions = criticsmSuggestionService.getCriticsmSuggestionById(id);
		Response response = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (criticsmSuggestions == null) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "No Data Found";
		}

		response.setCode(code);
		response.setMessage(message);
		response.setData(Arrays.asList(criticsmSuggestions));

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response> saveCriticsmSuggestions(@RequestBody CriticsmSuggestion criticsmSuggestions) throws Exception {
		CriticsmSuggestion newCriticsmSuggestions = criticsmSuggestionService.saveCriticsmSuggestion(criticsmSuggestions);
		Response resp = new Response();

		if (newCriticsmSuggestions != null) {
			resp.setCode(String.valueOf(HttpStatus.CREATED.value()));
			resp.setMessage("Sucessfully Save");
			resp.setData(Arrays.asList(newCriticsmSuggestions));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteCriticsmSuggestionsById(@PathVariable Integer id) {
		criticsmSuggestionService.deleteCriticsmSuggestionById(id);
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage("Sucessfully Delete");

		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
}
