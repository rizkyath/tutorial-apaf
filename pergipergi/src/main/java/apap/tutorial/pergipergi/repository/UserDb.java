package apap.tutorial.pergipergi.repository;

import apap.tutorial.pergipergi.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDb extends JpaRepository<UserModel, Long> {
    UserModel getUserModelById(String userId);
    UserModel getUserModelByUsername(String username);
    UserModel findByUsername(String username);
    Long deleteById(String id);
}
