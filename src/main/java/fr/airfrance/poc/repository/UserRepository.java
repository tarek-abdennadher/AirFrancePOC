package fr.airfrance.poc.repository;

import fr.airfrance.poc.entity.User;
import fr.airfrance.poc.entity.UserPk;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, UserPk> {

    List<User> getAllBy(Pageable pageable);
    List<User> findByUserPkUserName(String userName);

}
