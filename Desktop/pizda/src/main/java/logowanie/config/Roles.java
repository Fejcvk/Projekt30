package logowanie.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by Tomasz on 29.12.2016.
 */

@Component
public class Roles {
    public final String ADMIN;

    @Autowired
        public Roles(Environment env)
    {
        ADMIN = env.getProperty("stormpath.authorized.group.admin");
    }
}
