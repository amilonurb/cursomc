package br.com.brlima.cursomc.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.brlima.cursomc.model.cliente.Cliente;
import br.com.brlima.cursomc.service.ClienteService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteService clienteService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteService.findByEmail(email);

        if (cliente == null) {
            throw new UsernameNotFoundException(email);
        }

        return new UserSpringSecurity(cliente.getId(), cliente.getEmail(), cliente.getPassword(), cliente.getPerfis());
    }
}