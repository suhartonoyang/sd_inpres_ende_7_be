package co.id.sdinpresende7be.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	@Value("${upload.path}")
	private String uploadPath;

	@PostConstruct
	public void init() {
		try {
			Files.createDirectories(Paths.get(uploadPath));
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize folder for upload");
		}
	}

	public void save(File file) {
		try {
			InputStream inputStream = new FileInputStream(file);

			Path copyLocation = Paths.get(uploadPath + File.separator + StringUtils.cleanPath(file.getName()));
			Files.copy(inputStream, copyLocation, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}

	public void save(File file, String type) {
		try {
			String filename = StringUtils.cleanPath(file.getName());
			filename = filename.replace(".", type + ".");

			InputStream inputStream = new FileInputStream(file);

			Path copyLocation = Paths.get(uploadPath + File.separator + filename);
			Files.copy(inputStream, copyLocation, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}
}
