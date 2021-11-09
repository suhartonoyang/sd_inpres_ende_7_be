package co.id.sdinpresende7be.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import co.id.sdinpresende7be.model.Profile;

public interface ProfileRepo extends JpaRepository<Profile, Integer>{
	
}
