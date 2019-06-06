package br.com.brlima.cursomc;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.brlima.cursomc.model.Categoria;
import br.com.brlima.cursomc.model.Cidade;
import br.com.brlima.cursomc.model.Cliente;
import br.com.brlima.cursomc.model.Endereco;
import br.com.brlima.cursomc.model.Estado;
import br.com.brlima.cursomc.model.Produto;
import br.com.brlima.cursomc.model.TipoCliente;
import br.com.brlima.cursomc.repository.CategoriaRepository;
import br.com.brlima.cursomc.repository.CidadeRepository;
import br.com.brlima.cursomc.repository.ClienteRepository;
import br.com.brlima.cursomc.repository.EnderecoRepository;
import br.com.brlima.cursomc.repository.EstadoRepository;
import br.com.brlima.cursomc.repository.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática, meu");
		Categoria cat2 = new Categoria(null, "Escritório, jão");
		
		Produto p1 = new Produto(null, "Computador", new BigDecimal(2000.00));
		Produto p2 = new Produto(null, "Impressora", new BigDecimal(800.00));
		Produto p3 = new Produto(null, "Mouse", new BigDecimal(80.00));
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().add(p2);
		
		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().add(cat1);
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().add(c1);
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA);
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", c1, cli1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", c2, cli1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838993"));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}
}
