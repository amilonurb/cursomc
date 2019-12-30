package br.com.brlima.cursomc.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brlima.cursomc.model.cliente.Cliente;
import br.com.brlima.cursomc.model.cliente.TipoCliente;
import br.com.brlima.cursomc.model.localizacao.Cidade;
import br.com.brlima.cursomc.model.localizacao.Endereco;
import br.com.brlima.cursomc.model.localizacao.Estado;
import br.com.brlima.cursomc.model.pagamento.EstadoPagamento;
import br.com.brlima.cursomc.model.pagamento.Pagamento;
import br.com.brlima.cursomc.model.pagamento.PagamentoBoleto;
import br.com.brlima.cursomc.model.pagamento.PagamentoCartao;
import br.com.brlima.cursomc.model.pedido.ItemPedido;
import br.com.brlima.cursomc.model.pedido.Pedido;
import br.com.brlima.cursomc.model.produto.Categoria;
import br.com.brlima.cursomc.model.produto.Produto;
import br.com.brlima.cursomc.repository.CategoriaRepository;
import br.com.brlima.cursomc.repository.CidadeRepository;
import br.com.brlima.cursomc.repository.ClienteRepository;
import br.com.brlima.cursomc.repository.EnderecoRepository;
import br.com.brlima.cursomc.repository.EstadoRepository;
import br.com.brlima.cursomc.repository.ItemPedidoRepository;
import br.com.brlima.cursomc.repository.PagamentoRepository;
import br.com.brlima.cursomc.repository.PedidoRepository;
import br.com.brlima.cursomc.repository.ProdutoRepository;

@Service
public class DBService {

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

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    
    public void instantiateTestDatabase() {
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");
        Categoria cat3 = new Categoria(null, "Cama, mesa e banho");
        Categoria cat4 = new Categoria(null, "Eletrônicos");
        Categoria cat5 = new Categoria(null, "Jardinagem");
        Categoria cat6 = new Categoria(null, "Decoração");
        Categoria cat7 = new Categoria(null, "Perfumaria");

        Produto p1 = new Produto(null, "Computador", new BigDecimal(2000.00));
        Produto p2 = new Produto(null, "Impressora", new BigDecimal(800.00));
        Produto p3 = new Produto(null, "Mouse", new BigDecimal(80.00));
        Produto p4 = new Produto(null, "Mesa de escritório", new BigDecimal(300.00));
        Produto p5 = new Produto(null, "Toalha", new BigDecimal(50.00));
        Produto p6 = new Produto(null, "Colcha", new BigDecimal(200.00));
        Produto p7 = new Produto(null, "TV true color", new BigDecimal(1200.00));
        Produto p8 = new Produto(null, "Roçadeira", new BigDecimal(800.00));
        Produto p9 = new Produto(null, "Abajour", new BigDecimal(100.00));
        Produto p10 = new Produto(null, "Pendente", new BigDecimal(180.00));
        Produto p11 = new Produto(null, "Shampoo", new BigDecimal(90.00));

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2, p4));
        cat3.getProdutos().addAll(Arrays.asList(p5, p6));
        cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProdutos().add(p8);
        cat6.getProdutos().addAll(Arrays.asList(p9, p10));
        cat7.getProdutos().add(p11);

        p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p4.getCategorias().add(cat2);
        p5.getCategorias().add(cat3);
        p6.getCategorias().add(cat3);
        p7.getCategorias().add(cat4);
        p8.getCategorias().add(cat5);
        p9.getCategorias().add(cat6);
        p10.getCategorias().add(cat6);
        p11.getCategorias().add(cat7);

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

        DateTimeFormatter dtfSimple = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dtfFull = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null, LocalDateTime.parse("30/09/2017 10:32", dtfFull), cli1, e1);
        Pedido ped2 = new Pedido(null, LocalDateTime.parse("10/10/2017 19:35", dtfFull), cli1, e2);

        Pagamento pagto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, LocalDate.parse("20/10/2017", dtfSimple), null);
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        ItemPedido ip1 = new ItemPedido(ped1, p1, 1, new BigDecimal(0.00), new BigDecimal(2000.00));
        ItemPedido ip2 = new ItemPedido(ped1, p3, 2, new BigDecimal(0.00), new BigDecimal(80.00));
        ItemPedido ip3 = new ItemPedido(ped2, p2, 1, new BigDecimal(100.00), new BigDecimal(800.00));

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));

        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

        clienteRepository.save(cli1);
        enderecoRepository.saveAll(Arrays.asList(e1, e2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
    }
}