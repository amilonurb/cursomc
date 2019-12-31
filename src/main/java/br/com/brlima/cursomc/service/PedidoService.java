package br.com.brlima.cursomc.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brlima.cursomc.model.pagamento.EstadoPagamento;
import br.com.brlima.cursomc.model.pagamento.PagamentoBoleto;
import br.com.brlima.cursomc.model.pedido.ItemPedido;
import br.com.brlima.cursomc.model.pedido.Pedido;
import br.com.brlima.cursomc.repository.ItemPedidoRepository;
import br.com.brlima.cursomc.repository.PagamentoRepository;
import br.com.brlima.cursomc.repository.PedidoRepository;
import br.com.brlima.cursomc.service.exception.ObjectNotFoundException;
import br.com.brlima.cursomc.service.mail.EmailService;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ClienteService clienteService;

    public Pedido find(Long id) {
        Optional<Pedido> pedido = repository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! ID: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    public Pedido insert(Pedido pedido) {

        pedido.setId(null);
        pedido.setData(LocalDateTime.now());

        pedido.setCliente(clienteService.find(pedido.getCliente().getId()));

        pedido.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);

        if (pedido.getPagamento() instanceof PagamentoBoleto) {
            PagamentoBoleto pagamento = (PagamentoBoleto) pedido.getPagamento();
            boletoService.preencherPagamentoBoleto(pagamento, pedido.getData());
        }

        pedido = repository.save(pedido);

        pagamentoRepository.save(pedido.getPagamento());

        for (ItemPedido item : pedido.getItens()) {
            item.setDesconto(BigDecimal.ZERO);// Por enquanto não é dado desconto
            item.setProduto(produtoService.find(item.getProduto().getId()));
            item.setPreco(produtoService.find(item.getProduto().getId()).getPreco());
            item.setPedido(pedido);
        }

        itemPedidoRepository.saveAll(pedido.getItens());

        emailService.sendOrderConfirmationMail(pedido);

        return pedido;
    }
}
