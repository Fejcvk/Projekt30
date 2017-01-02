package Login.service.user;

/**
 * Created by karol on 27.12.2016.
 */
import Login.domain.User;
import Login.domain.UserCreateForm;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);

}