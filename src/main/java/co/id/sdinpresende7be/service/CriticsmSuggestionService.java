package co.id.sdinpresende7be.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.sdinpresende7be.model.CriticsmSuggestion;
import co.id.sdinpresende7be.repo.CriticsmSuggestionRepo;
import co.id.sdinpresende7be.utils.Validation;

@Service
public class CriticsmSuggestionService {

	@Autowired
	private CriticsmSuggestionRepo criticsmSuggestionRepo;

	@Autowired
	private Validation validation;

	public List<CriticsmSuggestion> getAllCriticsmSuggestions() {
		return criticsmSuggestionRepo.findAll();
	}

	public CriticsmSuggestion getCriticsmSuggestionById(Integer id) {
		return criticsmSuggestionRepo.findById(id).orElse(null);
	}

	public void deleteCriticsmSuggestionById(Integer id) {
		criticsmSuggestionRepo.deleteById(id);
	}

	public CriticsmSuggestion saveCriticsmSuggestion(CriticsmSuggestion criticsmSuggestion) throws Exception {

		CriticsmSuggestion newCriticsmSuggestion = new CriticsmSuggestion();

		CriticsmSuggestion existingCriticsmSuggestion = getCriticsmSuggestionById(criticsmSuggestion.getId());
		if (existingCriticsmSuggestion == null) {
			newCriticsmSuggestion.setCreatedBy(criticsmSuggestion.getCreatedBy());
			newCriticsmSuggestion.setCreatedDate(new Date());
		} else {
			newCriticsmSuggestion.setId(criticsmSuggestion.getId());
			newCriticsmSuggestion.setCreatedBy(existingCriticsmSuggestion.getCreatedBy());
			newCriticsmSuggestion.setCreatedDate(existingCriticsmSuggestion.getCreatedDate());
			newCriticsmSuggestion.setModifiedBy(criticsmSuggestion.getCreatedBy());
			newCriticsmSuggestion.setModifiedDate(new Date());
		}

		// validation
//		System.out.println("validate email: " + validation.validateEmail(criticsmSuggestion.getEmail()));

		if (criticsmSuggestion.getEmail() != null && !validation.validateEmail(criticsmSuggestion.getEmail())) {
			throw new Exception("Email not valid");
		}

		if (criticsmSuggestion.getPhoneNumber() != null && !validation.validatePhoneNumber(criticsmSuggestion.getPhoneNumber())) {
			throw new Exception("Phone Number not valid");
		}

		newCriticsmSuggestion.setName(criticsmSuggestion.getName());
		newCriticsmSuggestion.setEmail(criticsmSuggestion.getEmail());
		newCriticsmSuggestion.setPhoneNumber(criticsmSuggestion.getPhoneNumber());
		newCriticsmSuggestion.setDescription(criticsmSuggestion.getDescription());

		return criticsmSuggestionRepo.save(newCriticsmSuggestion);
	}
}
