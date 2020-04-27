package br.com.brlima.cursomc.config.security.user;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    private UserService() {
        // Construtor vazio
    }

    public static UserSpringSecurity authenticated() {
        try {
            return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
