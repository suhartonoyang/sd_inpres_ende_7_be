package co.id.sdinpresende7be.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import co.id.sdinpresende7be.model.ProfileType;

public interface ProfileTypeRepo extends JpaRepository<ProfileType, Integer>{
	
	public ProfileType findByName(String name);
}
