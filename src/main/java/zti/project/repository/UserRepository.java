package zti.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import zti.project.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
    User findByName(String name);
}
