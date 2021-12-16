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
import co.id.sdinpresende7be.model.Gallery;
import co.id.sdinpresende7be.service.GalleryService;

@RequestMapping(value = "/api/galleries")
@RestController
@CrossOrigin
public class GalleryController {

	@Autowired
	private GalleryService galleryService;

	@GetMapping
	public ResponseEntity<Response> getAllGalleries() {
		List<Gallery> Galleries = galleryService.getAllGalleries();
		Response response = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (Galleries.isEmpty()) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "Data not found";
		}

		response.setCode(code);
		response.setMessage(message);
		response.setData(Galleries);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> getGalleryById(@PathVariable Integer id) {
		Gallery gallery = galleryService.getGalleryById(id);
		Response response = new Response();
		String code = String.valueOf(HttpStatus.OK.value());
		String message = HttpStatus.OK.name();

		if (gallery == null) {
			code = String.valueOf(HttpStatus.NOT_FOUND.value());
			message = "No Data Found";
		}

		response.setCode(code);
		response.setMessage(message);
		response.setData(Arrays.asList(gallery));

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}/image")
	public ResponseEntity<?> getImage(@PathVariable Integer id) {
		Gallery gallery = galleryService.getGalleryById(id);
		if (gallery == null) {
			Response response = new Response();
			response.setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
			response.setMessage("No Data Found");
			return ResponseEntity.ok(response);
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; " + "filename=\"" + gallery.getFile().getName() + "\"")
				.body(gallery.getFile().getData());
	}

	@PostMapping
	public ResponseEntity<Response> saveGallery(@RequestBody Gallery Gallery) {
		Gallery newGallery = galleryService.saveGallery(Gallery);
		Response resp = new Response();

		if (newGallery != null) {
			resp.setCode(String.valueOf(HttpStatus.CREATED.value()));
			resp.setMessage("Sucessfully Save");
			resp.setData(Arrays.asList(newGallery));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	@PostMapping("/{id}/upload")
	public ResponseEntity<Response> uploadImage(@PathVariable Integer id, @RequestParam MultipartFile file) throws Exception {
		Gallery updateGallery = galleryService.uploadImage(id, file);
		Response resp = new Response();

		if (updateGallery != null) {
			resp.setCode(String.valueOf(HttpStatus.CREATED.value()));
			resp.setMessage("Sucessfully Save");
			resp.setData(Arrays.asList(updateGallery));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteGalleryById(@PathVariable Integer id) {
		galleryService.deleteGalleryById(id);
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage("Sucessfully Delete");

		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
}
