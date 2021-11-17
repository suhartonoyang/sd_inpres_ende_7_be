package co.id.sdinpresende7be.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import co.id.sdinpresende7be.model.File;
import co.id.sdinpresende7be.model.News;
import co.id.sdinpresende7be.repo.NewsRepo;

@Service
public class NewsService {

	@Autowired
	private NewsRepo newsRepo;

	public List<News> getAllNews() {
		return newsRepo.findAll();
	}

	public News getNewsById(Integer id) {
		return newsRepo.findById(id).orElse(null);
	}

	public News saveNews(News news) {

		News newNews = new News();

		News existingNews = getNewsById(news.getId());

		if (existingNews == null) {
			newNews.setCreatedBy(news.getCreatedBy());
			newNews.setCreatedDate(new Date());
		} else {
			newNews.setId(news.getId());
			newNews.setCreatedBy(existingNews.getCreatedBy());
			newNews.setCreatedDate(existingNews.getCreatedDate());
			newNews.setModifiedBy(news.getCreatedBy());
			newNews.setModifiedDate(new Date());
		}

		newNews.setTitle(news.getTitle());
		newNews.setDescription(news.getDescription());

		return newsRepo.save(newNews);
	}

	public News uploadImage(Integer id, MultipartFile file) throws Exception {
		News news = getNewsById(id);
		if (news == null) {
			throw new Exception("Data not found");
		}

		File newFile = new File();
		newFile.setName(StringUtils.cleanPath(file.getOriginalFilename()));
		newFile.setType(file.getContentType());
		newFile.setData(file.getBytes());
		newFile.setNews(Arrays.asList(news));

		news.setFile(newFile);
		return newsRepo.save(news);
	}

	public void deleteNewsById(Integer id) {
		newsRepo.deleteById(id);
	}

}
