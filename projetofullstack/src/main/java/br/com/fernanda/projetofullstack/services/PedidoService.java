package br.com.fernanda.projetofullstack.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernanda.projetofullstack.domains.ItemPedido;
import br.com.fernanda.projetofullstack.domains.PagamentoBoleto;
import br.com.fernanda.projetofullstack.domains.Pedido;
import br.com.fernanda.projetofullstack.domains.enums.EstadoPagamento;
import br.com.fernanda.projetofullstack.repositories.ItemPedidoRepository;
import br.com.fernanda.projetofullstack.repositories.PagamentoRepository;
import br.com.fernanda.projetofullstack.repositories.PedidoRepository;
import br.com.fernanda.projetofullstack.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	@Autowired
	private PagamentoRepository repoPagto;
	@Autowired
	private ItemPedidoRepository repoItem;
	
	@Autowired
	private BoletoService boletoService;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ClienteService clienteService;
	
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> pedido =  repo.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(id, "Pedido"));
	}
	
	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.setCliente(clienteService.Find(pedido.getCliente().getId()));
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		
		if(pedido.getPagamento() instanceof PagamentoBoleto) {
			PagamentoBoleto pagto = (PagamentoBoleto) pedido.getPagamento();
			boletoService.preencherPagtoBoleto(pagto, pedido.getInstante());
		}
		pedido = repo.save(pedido);
		repoPagto.save(pedido.getPagamento());
		
		for(ItemPedido i : pedido.getItens()) {
			i.setDesconto(0.0);
			i.setProduto(produtoService.Buscar(i.getProduto().getId()));
			i.setPreco(i.getProduto().getValor());
			i.setPedido(pedido);			
		}
		repoItem.saveAll(pedido.getItens());
		
		return pedido;		
	}
	
}
