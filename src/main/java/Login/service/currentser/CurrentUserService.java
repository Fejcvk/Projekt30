package Login.service.currentser;

/**
 * Created by karol on 27.12.2016.
 */
import Login.domain.CurrentUser;

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, Long userId);

}