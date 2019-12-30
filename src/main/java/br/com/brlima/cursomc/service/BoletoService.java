package br.com.brlima.cursomc.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.brlima.cursomc.model.pagamento.PagamentoBoleto;

@Service
public class BoletoService {

    private static final long PRAZO_VENCIMENTO_EM_DIAS = 7;

    public void preencherPagamentoBoleto(PagamentoBoleto pagamento, LocalDateTime dataPedido) {

        pagamento.setDataVencimento(dataPedido.toLocalDate().plusDays(PRAZO_VENCIMENTO_EM_DIAS));
    }
}