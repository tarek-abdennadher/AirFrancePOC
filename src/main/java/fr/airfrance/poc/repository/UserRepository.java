package fr.airfrance.poc.repository;

import fr.airfrance.poc.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User> getAllBy(Pageable pageable);
    Optional<User> findByLogin(String login);

}
