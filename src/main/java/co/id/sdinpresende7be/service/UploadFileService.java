package co.id.sdinpresende7be.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import co.id.sdinpresende7be.model.Alumni;
import co.id.sdinpresende7be.model.Enroll;
import co.id.sdinpresende7be.model.Graduation;
import co.id.sdinpresende7be.model.Student;
import co.id.sdinpresende7be.model.SubjectClassroom;
import co.id.sdinpresende7be.model.User;
import co.id.sdinpresende7be.repo.AlumniRepo;
import co.id.sdinpresende7be.repo.EnrollRepo;
import co.id.sdinpresende7be.repo.GraduationRepo;
import co.id.sdinpresende7be.repo.StudentRepo;
import co.id.sdinpresende7be.repo.SubjectClassroomRepo;
import co.id.sdinpresende7be.repo.UserRepo;
import co.id.sdinpresende7be.utils.EncryptorAesGcm;

@Service
public class UploadFileService {

	@Autowired
	private AlumniRepo alumniRepo;

	@Autowired
	private GraduationRepo graduationRepo;

	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private EnrollRepo enrollRepo;

	@Autowired
	private SubjectClassroomRepo subjectClassroomRepo;

	@Autowired
	private FileService fileService;

	@Autowired
	private EncryptorAesGcm encryptorFile;

	@Value("${password.key.aes}")
	private String PASSWORD_KEY;

	@Value("${upload.path}")
	private String uploadPath;

	@Transactional
	public List<?> uploadFile(String type, String username, MultipartFile file) throws Exception {

		List<?> objects = new ArrayList<>();
		switch (type.toLowerCase()) {
		case "alumni":
			objects = uploadFileAlumni(username, file);
			break;
		case "graduation":
			objects = uploadFileGraduation(username, file);
			break;
		case "student":
			objects = uploadFileStudent(username, file);
		default:
			break;
		}

		return objects;
	}

	private List<?> uploadFileAlumni(String username, MultipartFile file) throws Exception {
		Workbook workbook = new XSSFWorkbook(file.getInputStream());
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();

		List<Alumni> alumnis = new ArrayList<Alumni>();

		while (rows.hasNext()) {
			Row currentRow = rows.next();
			Iterator<Cell> cells = currentRow.iterator();

			Alumni alumni = new Alumni();
			if (currentRow.getRowNum() == 0) {
				continue;
			}

			int cellIdx = 1;
			while (cells.hasNext()) {
				Cell currentCell = cells.next();
				switch (cellIdx) {
				case 1:
					alumni.setNis((int) currentCell.getNumericCellValue());
					break;
				case 2:
					alumni.setName(currentCell.getStringCellValue());
					break;
				case 3:
					alumni.setGender(currentCell.getStringCellValue());
					break;
				case 4:
					alumni.setGraduationYear((int) currentCell.getNumericCellValue());
					break;
				case 5:
					alumni.setLastEducation(currentCell.getStringCellValue());
					break;
				default:
					break;
				}

				cellIdx++;
			}

			alumni.setCreatedBy(username);
			alumni.setCreatedDate(new Date());
			alumnis.add(alumni);
		}

		if (!alumnis.isEmpty()) {
			alumniRepo.saveAll(alumnis);
		}

		workbook.close();

		fileService.saveMultipartFile("alumni", file);
		String filename = StringUtils.cleanPath(file.getOriginalFilename());

		String encryptedPath = fileService.getPath("alumni", (filename + ".encrypted"));
		File encryptedFile = new File(encryptedPath);
		encryptorFile.encrypt(getFile(fileService.getPath("alumni", filename)), encryptedFile);

		String decryptedPath = fileService.getPath("alumni", filename.replace(".", "-decrypted."));
		File decryptedFile = new File(decryptedPath);
		encryptorFile.decrypt(encryptedFile, decryptedFile);

		return alumnis;
	}

	private List<?> uploadFileGraduation(String username, MultipartFile file) throws Exception {
		Workbook workbook = new XSSFWorkbook(file.getInputStream());
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();

		List<Graduation> graduations = new ArrayList<Graduation>();

		while (rows.hasNext()) {
			Row currentRow = rows.next();
			Iterator<Cell> cells = currentRow.iterator();

			Graduation graduation = new Graduation();
			if (currentRow.getRowNum() == 0) {
				continue;
			}

			int cellIdx = 1;
			while (cells.hasNext()) {
				Cell currentCell = cells.next();
				switch (cellIdx) {
				case 1:
					graduation.setNis((int) currentCell.getNumericCellValue());
					break;
				case 2:
					graduation.setName(currentCell.getStringCellValue());
					break;
				case 3:
					graduation.setGender(currentCell.getStringCellValue());
					break;
				case 4:
					graduation.setYear((int) currentCell.getNumericCellValue());
					break;
				case 5:
					graduation.setNextEducation(currentCell.getStringCellValue());
					break;
				default:
					break;
				}

				cellIdx++;
			}

			graduation.setCreatedBy(username);
			graduation.setCreatedDate(new Date());
			graduations.add(graduation);
		}

		workbook.close();

		if (!graduations.isEmpty()) {
			graduationRepo.saveAll(graduations);
		}

		fileService.saveMultipartFile("graduation", file);
		String filename = StringUtils.cleanPath(file.getOriginalFilename());

		String encryptedPath = fileService.getPath("graduation", (filename + ".encrypted"));
		File encryptedFile = new File(encryptedPath);
		encryptorFile.encrypt(getFile(fileService.getPath("graduation", filename)), encryptedFile);

		String decryptedPath = fileService.getPath("graduation", filename.replace(".", "-decrypted."));
		File decryptedFile = new File(decryptedPath);
		encryptorFile.decrypt(encryptedFile, decryptedFile);

		return graduations;
	}

	private List<?> uploadFileStudent(String username, MultipartFile file) throws Exception {
		Workbook workbook = new XSSFWorkbook(file.getInputStream());
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();

		List<Student> students = new ArrayList<Student>();

		while (rows.hasNext()) {
			Row currentRow = rows.next();
			Iterator<Cell> cells = currentRow.iterator();

			Student student = new Student();
			if (currentRow.getRowNum() == 0) {
				continue;
			}

			int cellIdx = 1;
			while (cells.hasNext()) {
				Cell currentCell = cells.next();
				switch (cellIdx) {
				case 1:
					student.setNis((int) currentCell.getNumericCellValue());
					break;
				case 2:
					student.setName(currentCell.getStringCellValue());
					break;
				case 3:
					student.setGender(currentCell.getStringCellValue());
					break;
				case 4:
					String teacherUsername = currentCell.getStringCellValue();
					User teacher = userRepo.findByUsername(teacherUsername);
					if (teacher == null) {
						throw new Exception("Teacher not found in row " + (1 + currentRow.getRowNum()));
					}

					student.setTeacher(teacher);
					break;
				case 5:
					student.setGuardian(currentCell.getStringCellValue());
					break;
				default:
					break;
				}

				cellIdx++;
			}

			student.setCreatedBy(username);
			student.setCreatedDate(new Date());
			students.add(student);
		}

		workbook.close();

		if (!students.isEmpty()) {
			studentRepo.saveAll(students);
		}

		fileService.saveMultipartFile("student", file);
		String filename = StringUtils.cleanPath(file.getOriginalFilename());

		String encryptedPath = fileService.getPath("student", (filename + ".encrypted"));
		File encryptedFile = new File(encryptedPath);
		encryptorFile.encrypt(getFile(fileService.getPath("student", filename)), encryptedFile);

		String decryptedPath = fileService.getPath("student", filename.replace(".", "-decrypted."));
		File decryptedFile = new File(decryptedPath);
		encryptorFile.decrypt(encryptedFile, decryptedFile);

		return students;
	}

	@Transactional
	public List<?> uploadFileNilai(Integer subjectClassroomId, String username, MultipartFile file) throws Exception {
		SubjectClassroom subjectClassroom = subjectClassroomRepo.findById(subjectClassroomId).orElse(null);
		enrollRepo.deleteBysubjectClassroom(subjectClassroom);
		Workbook workbook = new XSSFWorkbook(file.getInputStream());
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();

		List<Enroll> enrolls = new ArrayList<Enroll>();

		while (rows.hasNext()) {
			Row currentRow = rows.next();
			Iterator<Cell> cells = currentRow.iterator();

			Enroll enroll = new Enroll();
			if (currentRow.getRowNum() == 0) {
				continue;
			}

			int cellIdx = 1;
			while (cells.hasNext()) {
				Cell currentCell = cells.next();
				switch (cellIdx) {
				case 1:
					int nis = (int) currentCell.getNumericCellValue();
					System.out.println(nis);
					Student student = studentRepo.findByNis(nis);
					if (student == null) {
						throw new Exception("Student not found");
					}

					enroll.setStudent(student);
					break;
				case 2:
					enroll.setScore((double) currentCell.getNumericCellValue());
					break;
				default:
					break;
				}

				cellIdx++;
			}

			enroll.setSubjectClassroom(subjectClassroom);
			enroll.setCreatedBy(username);
			enroll.setCreatedDate(new Date());
			enrolls.add(enroll);
		}

		workbook.close();

		if (!enrolls.isEmpty()) {
			enrollRepo.saveAll(enrolls);
		}

		fileService.saveMultipartFile("nilai", file);
		String filename = StringUtils.cleanPath(file.getOriginalFilename());

		String encryptedPath = fileService.getPath("nilai", (filename + ".encrypted"));
		File encryptedFile = new File(encryptedPath);
		encryptorFile.encrypt(getFile(fileService.getPath("nilai", filename)), encryptedFile);

		String decryptedPath = fileService.getPath("nilai", filename.replace(".", "-decrypted."));
		File decryptedFile = new File(decryptedPath);
		encryptorFile.decrypt(encryptedFile, decryptedFile);

		return enrolls;
	}

	private File getFile(String path) throws FileNotFoundException {
		File file = ResourceUtils.getFile(path);
		return file;
	}

}
