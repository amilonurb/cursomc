package br.com.brlima.cursomc.service;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.brlima.cursomc.config.security.user.UserService;
import br.com.brlima.cursomc.config.security.user.UserSpringSecurity;
import br.com.brlima.cursomc.model.cliente.Cliente;
import br.com.brlima.cursomc.model.cliente.dto.ClienteDTO;
import br.com.brlima.cursomc.model.cliente.dto.ClienteNewDTO;
import br.com.brlima.cursomc.model.enums.Perfil;
import br.com.brlima.cursomc.model.enums.TipoCliente;
import br.com.brlima.cursomc.model.localizacao.Cidade;
import br.com.brlima.cursomc.model.localizacao.Endereco;
import br.com.brlima.cursomc.repository.ClienteRepository;
import br.com.brlima.cursomc.repository.EnderecoRepository;
import br.com.brlima.cursomc.service.exception.AuthorizationException;
import br.com.brlima.cursomc.service.exception.DataIntegrityException;
import br.com.brlima.cursomc.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private AmazonS3Service s3Service;

    @Autowired
    private ImageService imageService;

    @Value("${img.prefix.client.profile}")
    private String prefix;

    @Value("${img.profile.size}")
    private Integer imageSize;

    public Cliente find(Long id) {
        UserSpringSecurity user = UserService.authenticated();
        if (user == null || !user.hasHole(Perfil.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Acesso negado");
        }

        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public Cliente findByEmail(String email) {
        UserSpringSecurity user = UserService.authenticated();
        if (user == null || !user.hasHole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
            throw new AuthorizationException("Acesso negado");
        }

        Optional<Cliente> cliente = clienteRepository.findByEmail(email);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + user.getId() + ", Tipo: " + Cliente.class.getName()));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Transactional
    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        cliente = this.clienteRepository.save(cliente);
        this.enderecoRepository.saveAll(cliente.getEnderecos());
        return cliente;
    }

    public Cliente update(Cliente cliente) {
        Cliente clienteDB = this.find(cliente.getId());
        updateFromData(clienteDB, cliente);
        return this.clienteRepository.save(cliente);
    }

    public void delete(Long id) {
        this.find(id);
        try {
            this.clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
        }
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String sortDirection) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(sortDirection), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO dto) {
        return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null, null);
    }

    public Cliente fromDTO(ClienteNewDTO dto) {
        Cliente cliente = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(), TipoCliente.toEnum(dto.getCodigoTipoCliente()), passwordEncoder.encode(dto.getPassword()));

        Cidade cidade = new Cidade(dto.getCidadeId(), null, null);

        Endereco endereco = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(), dto.getCep(), cidade, cliente);
        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(dto.getTelefoneObrigatorio());

        if (dto.getTelefoneOpcional1() != null) {
            cliente.getTelefones().add(dto.getTelefoneOpcional1());
        }

        if (dto.getTelefoneOpcional2() != null) {
            cliente.getTelefones().add(dto.getTelefoneOpcional2());
        }

        return cliente;
    }

    private void updateFromData(Cliente current, Cliente data) {
        current.setNome(data.getNome());
        current.setEmail(data.getEmail());
    }

    public URI uploadProfilePicture(MultipartFile multipartFile) {
        UserSpringSecurity user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Acesso negado");
        }

        BufferedImage image = imageService.getJPGImageFromFile(multipartFile);
        image = imageService.cropSquare(image);
        image = imageService.resize(image, imageSize);

        String filename = prefix + user.getId() + ".jpg";

        return s3Service.uploadFile(filename, imageService.getInputStream(image, "jpg"), "image");
    }
}
