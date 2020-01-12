package br.com.brlima.cursomc.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.brlima.cursomc.model.cliente.Cliente;
import br.com.brlima.cursomc.repository.ClienteRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Cliente> clienteOptional = clienteRepository.findByEmail(email);

        if (!clienteOptional.isPresent()) {
            throw new UsernameNotFoundException(email);
        }

        Cliente cliente = clienteOptional.get();

        return new UserSpringSecurity(cliente.getId(), cliente.getEmail(), cliente.getPassword(), cliente.getPerfis());
    }
}