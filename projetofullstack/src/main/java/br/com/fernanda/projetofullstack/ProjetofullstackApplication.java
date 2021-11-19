package br.com.fernanda.projetofullstack;

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
import br.com.fernanda.projetofullstack.domains.Produto;
import br.com.fernanda.projetofullstack.domains.enums.TipoCliente;
import br.com.fernanda.projetofullstack.repositories.CategoriaRepository;
import br.com.fernanda.projetofullstack.repositories.CidadeRepository;
import br.com.fernanda.projetofullstack.repositories.ClienteRepository;
import br.com.fernanda.projetofullstack.repositories.EnderecoRepository;
import br.com.fernanda.projetofullstack.repositories.EstadoRepository;
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
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetofullstackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mousse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));	
		
		cat_repo.saveAll(Arrays.asList(cat1, cat2));
		
		prod_repo.saveAll(Arrays.asList(p1, p2, p3));
		
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
	}

}
