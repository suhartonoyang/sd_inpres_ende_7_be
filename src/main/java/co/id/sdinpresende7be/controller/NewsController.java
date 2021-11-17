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

import com.google.common.net.HttpHeaders;

import co.id.sdinpresende7be.bean.Response;
import co.id.sdinpresende7be.model.News;
import co.id.sdinpresende7be.service.NewsService;

@RequestMapping(value = "/api/news")
@RestController
@CrossOrigin
public class NewsController {

	@Autowired
	private NewsService newsService;

	@GetMapping
	public ResponseEntity<Response> getAllNewses() {
		List<News> newses = newsService.getAllNews();
		Response response = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (newses.isEmpty()) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "Data not found";
		}

		response.setCode(code);
		response.setMessage(message);
		response.setData(newses);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> getNewsById(@PathVariable Integer id) {
		News news = newsService.getNewsById(id);
		Response response = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (news == null) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "No Data Found";
		}

		response.setCode(code);
		response.setMessage(message);
		response.setData(Arrays.asList(news));

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}/image")
	public ResponseEntity<?> getImage(@PathVariable Integer id) {
		News news = newsService.getNewsById(id);
		if (news == null) {
			Response response = new Response();
			response.setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
			response.setMessage("No Data Found");
			return ResponseEntity.ok(response);
		}

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; " + "filename=\"" + news.getFile().getName() + "\"")
				.body(news.getFile().getData());
	}

	@PostMapping
	public ResponseEntity<Response> saveNews(@RequestBody News news) {
		News newNews = newsService.saveNews(news);
		Response resp = new Response();

		if (newNews != null) {
			resp.setCode(String.valueOf(HttpStatus.CREATED.value()));
			resp.setMessage("Sucessfully Save");
			resp.setData(Arrays.asList(newNews));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	@PostMapping("/{id}/upload")
	public ResponseEntity<Response> uploadImage(@PathVariable Integer id, @RequestParam MultipartFile file) throws Exception {
		News updateNews = newsService.uploadImage(id, file);
		Response resp = new Response();

		if (updateNews != null) {
			resp.setCode(String.valueOf(HttpStatus.CREATED.value()));
			resp.setMessage("Sucessfully Save");
			resp.setData(Arrays.asList(updateNews));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteNewsById(@PathVariable Integer id) {
		newsService.deleteNewsById(id);
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage("Sucessfully Delete");

		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
}
