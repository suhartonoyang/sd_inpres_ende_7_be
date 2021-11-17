package co.id.sdinpresende7be.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import co.id.sdinpresende7be.model.Event;

public interface EventRepo extends JpaRepository<Event, Integer> {

}
