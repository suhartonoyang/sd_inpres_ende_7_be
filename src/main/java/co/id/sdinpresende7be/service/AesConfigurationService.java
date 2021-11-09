package co.id.sdinpresende7be.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.sdinpresende7be.model.AesConfiguration;
import co.id.sdinpresende7be.repo.AesConfigurationRepo;

@Service
public class AesConfigurationService {

	@Autowired
	private AesConfigurationRepo aesConfigurationRepo;
	
	public List<AesConfiguration> getAllAesConfigurations(){
		return aesConfigurationRepo.findAll();
	}
	
	public AesConfiguration getAesConfigurationById(Integer id) {
		return aesConfigurationRepo.findById(id).orElse(null);
	}
}
