package co.id.sdinpresende7be.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import co.id.sdinpresende7be.model.News;

public interface NewsRepo extends JpaRepository<News, Integer> {

}
