package pl.jkan.digitalcommerce.components;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.system.SystemUserContext;
import pl.jkan.ecommerce.system.infrastructure.InMemorySystemUserContext;

@Component
public class SystemComponent {
    @Bean
    private SystemUserContext getSystemUserContext() {
        InMemorySystemUserContext systemUserContext = new InMemorySystemUserContext();
        systemUserContext.authenticate(new Identifier("user_1"));

        return systemUserContext;
    }
}


