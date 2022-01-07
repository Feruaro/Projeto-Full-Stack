package br.com.fernanda.projetofullstack;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.fernanda.projetofullstack.domains.Categoria;
import br.com.fernanda.projetofullstack.domains.Cidade;
import br.com.fernanda.projetofullstack.domains.Cliente;
import br.com.fernanda.projetofullstack.domains.Endereco;
import br.com.fernanda.projetofullstack.domains.Estado;
import br.com.fernanda.projetofullstack.domains.ItemPedido;
import br.com.fernanda.projetofullstack.domains.Pagamento;
import br.com.fernanda.projetofullstack.domains.PagamentoBoleto;
import br.com.fernanda.projetofullstack.domains.PagamentoCartao;
import br.com.fernanda.projetofullstack.domains.Pedido;
import br.com.fernanda.projetofullstack.domains.Produto;
import br.com.fernanda.projetofullstack.domains.enums.EstadoPagamento;
import br.com.fernanda.projetofullstack.domains.enums.TipoCliente;
import br.com.fernanda.projetofullstack.repositories.CategoriaRepository;
import br.com.fernanda.projetofullstack.repositories.CidadeRepository;
import br.com.fernanda.projetofullstack.repositories.ClienteRepository;
import br.com.fernanda.projetofullstack.repositories.EnderecoRepository;
import br.com.fernanda.projetofullstack.repositories.EstadoRepository;
import br.com.fernanda.projetofullstack.repositories.ItemPedidoRepository;
import br.com.fernanda.projetofullstack.repositories.PagamentoRepository;
import br.com.fernanda.projetofullstack.repositories.PedidoRepository;
import br.com.fernanda.projetofullstack.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetofullstackApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository cat_repo;
	@Autowired
	private ProdutoRepository prod_repo;
	@Autowired
	private EstadoRepository est_repo;
	@Autowired
	private CidadeRepository cid_repo;
	@Autowired
	private ClienteRepository cli_repo;
	@Autowired
	private EnderecoRepository end_repo;
	@Autowired
	private PedidoRepository pedido_repo;
	@Autowired
	private PagamentoRepository pag_repo;
	@Autowired
	private ItemPedidoRepository item_repo;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetofullstackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama, mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mousse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "Tv true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));		
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		cat_repo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		
		prod_repo.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", e1);
		Cidade c2 = new Cidade(null, "São Paulo", e2);
		Cidade c3 = new Cidade(null, "Campinas", e2);
		
		e1.getCidades().addAll(Arrays.asList(c1));
		e2.getCidades().addAll(Arrays.asList(c2, c3));
		
		est_repo.saveAll(Arrays.asList(e1, e2));
		
		cid_repo.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "12345678912", TipoCliente.PESSOAFISICA);
        
		cli1.getTelefone().addAll(Arrays.asList("999638569", "985643625"));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto 302", "Jardim", "32650456", cli1, c1);
		Endereco end2 = new Endereco(null, "AvenidadeMatos", "105", "Sala 800", "Centro", "36589400", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		cli_repo.saveAll(Arrays.asList(cli1));
		
		end_repo.saveAll(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:32"), cli1, end2);
		
		Pagamento pagto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf2.parse("20/10/2017"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedido_repo.saveAll(Arrays.asList(ped1, ped2));
		pag_repo.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);		
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);		
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
				
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().add(ip3);
		p1.getItens().add(ip1);
		p2.getItens().add(ip3);
		p3.getItens().add(ip2);
		
		item_repo.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
