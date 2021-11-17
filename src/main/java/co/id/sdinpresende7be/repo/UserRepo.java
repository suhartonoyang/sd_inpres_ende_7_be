package co.id.sdinpresende7be.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.id.sdinpresende7be.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	public User findByUsername(String username);
	
	public List<User> findByRole(String role);
}
