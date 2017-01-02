package logowanie.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
/**
 * Created by Tomasz on 29.12.2016.
 */
@Service
public class AdminService {
    @PreAuthorize("hasRole(@roles.ADMIN)")
    public boolean ensureAdmin()
    {
        return true;
    }
}
