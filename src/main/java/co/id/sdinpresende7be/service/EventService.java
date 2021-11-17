package co.id.sdinpresende7be.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import co.id.sdinpresende7be.model.Event;
import co.id.sdinpresende7be.model.File;
import co.id.sdinpresende7be.repo.EventRepo;

@Service
public class EventService {

	@Autowired
	private EventRepo eventRepo;

	public List<Event> getAllEvents() {
		return eventRepo.findAll();
	}

	public Event getEventById(Integer id) {
		return eventRepo.findById(id).orElse(null);
	}

	public Event saveEvent(Event event) {

		Event newEvent = new Event();

		Event existingEvent = getEventById(event.getId());

		if (existingEvent == null) {
			newEvent.setCreatedBy(event.getCreatedBy());
			newEvent.setCreatedDate(new Date());
		} else {
			newEvent.setId(event.getId());
			newEvent.setCreatedBy(existingEvent.getCreatedBy());
			newEvent.setCreatedDate(existingEvent.getCreatedDate());
			newEvent.setModifiedBy(event.getCreatedBy());
			newEvent.setModifiedDate(new Date());
		}

		newEvent.setTitle(event.getTitle());
		newEvent.setDescription(event.getDescription());

		return eventRepo.save(newEvent);
	}

	public Event uploadImage(Integer id, MultipartFile file) throws Exception {
		Event event = getEventById(id);
		if (event == null) {
			throw new Exception("Data not found");
		}
		
		File newFile = new File();
		newFile.setName(StringUtils.cleanPath(file.getOriginalFilename()));
		newFile.setType(file.getContentType());
		newFile.setData(file.getBytes());
		newFile.setEvent(Arrays.asList(event));
		
		event.setFile(newFile);

		return eventRepo.save(event);
	}

	public void deleteEventById(Integer id) {
		eventRepo.deleteById(id);
	}

}
