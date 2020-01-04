package br.com.brlima.cursomc.config.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthRest {

    @Autowired
    private JWTUtils jwtUtils;

    @PostMapping("/refresh")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSpringSecurity user = UserService.authenticated();
        String newToken = jwtUtils.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + newToken);
        return ResponseEntity.noContent().build();
    }
}