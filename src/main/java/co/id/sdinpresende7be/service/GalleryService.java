package co.id.sdinpresende7be.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import co.id.sdinpresende7be.model.File;
import co.id.sdinpresende7be.model.Gallery;
import co.id.sdinpresende7be.repo.GalleryRepo;

@Service
public class GalleryService {

	@Autowired
	private GalleryRepo galleryRepo;

	public List<Gallery> getAllGalleries() {
		return galleryRepo.findAll();
	}

	public Gallery getGalleryById(Integer id) {
		return galleryRepo.findById(id).orElse(null);
	}

	public Gallery saveGallery(Gallery Gallery) {

		Gallery newGallery = new Gallery();

		Gallery existingGallery = getGalleryById(Gallery.getId());

		if (existingGallery == null) {
			newGallery.setCreatedBy(Gallery.getCreatedBy());
			newGallery.setCreatedDate(new Date());
		} else {
			newGallery.setId(Gallery.getId());
			newGallery.setCreatedBy(existingGallery.getCreatedBy());
			newGallery.setCreatedDate(existingGallery.getCreatedDate());
			newGallery.setModifiedBy(Gallery.getCreatedBy());
			newGallery.setModifiedDate(new Date());
		}

		newGallery.setTitle(Gallery.getTitle());
		return galleryRepo.save(newGallery);
	}
	
	public Gallery uploadImage(Integer id, MultipartFile file) throws Exception {
		Gallery gallery = getGalleryById(id);
		if (gallery == null) {
			throw new Exception("Data not found");
		}
		

		File newFile = new File();
		newFile.setName(StringUtils.cleanPath(file.getOriginalFilename()));
		newFile.setType(file.getContentType());
		newFile.setData(file.getBytes());
		newFile.setGallery(Arrays.asList(gallery));
		
		gallery.setFile(newFile);
		
		return galleryRepo.save(gallery);

	}
	
	public void deleteGalleryById(Integer id) {
		galleryRepo.deleteById(id);
	}

}
