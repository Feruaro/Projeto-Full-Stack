package br.com.fernanda.projetofullstack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.fernanda.projetofullstack.domains.Cidade;
import br.com.fernanda.projetofullstack.domains.Cliente;
import br.com.fernanda.projetofullstack.domains.Endereco;
import br.com.fernanda.projetofullstack.domains.enums.TipoCliente;
import br.com.fernanda.projetofullstack.dto.ClienteDTO;
import br.com.fernanda.projetofullstack.dto.ClienteNewDTO;
import br.com.fernanda.projetofullstack.repositories.ClienteRepository;
import br.com.fernanda.projetofullstack.repositories.EnderecoRepository;
import br.com.fernanda.projetofullstack.services.exceptions.DataIntegrityException;
import br.com.fernanda.projetofullstack.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	@Autowired
	private EnderecoRepository end_repo;
	
	public Cliente Find(Integer id) {
		Optional<Cliente> cliente = repo.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(id, "Cliente"));	
	}
	
	public List<Cliente> FindAll() {
		return repo.findAll();
	}
	
	public Cliente Insert(Cliente cliente) {
		cliente.setId(null);
		cliente = repo.save(cliente);
		end_repo.saveAll(cliente.getEnderecos());
		
		return cliente;
	}

	public Cliente Update(Cliente cliente) {
		Cliente cli = Find(cliente.getId()); 
		updateData(cli, cliente);
		
		return repo.save(cli);
	}
	
	private void updateData(Cliente cli, Cliente cliente) {
		cli.setNome(cliente.getNome());
		cli.setEmail(cliente.getEmail());
	}

	public void Delete(Integer id) {
		Cliente cliente = Find(id); 

		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(id, "Cliente", cliente.getNome());
		}
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPages, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPages, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente FromDTO(ClienteDTO cliDTO) {
		return new Cliente(cliDTO.getId(), cliDTO.getNome(), cliDTO.getEmail(), null, null);
	}
	
	public Cliente FromDTO(ClienteNewDTO cliDTO) {
		Cliente cli = new Cliente(null, cliDTO.getNome(), cliDTO.getEmail(), cliDTO.getCpf_cnpj(), TipoCliente.toEnum(cliDTO.getTipo()));
		Cidade cid = new Cidade(cliDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, cliDTO.getLogradouro(), cliDTO.getNumero(), cliDTO.getComplemento(), cliDTO.getBairro(), cliDTO.getCep(), cli, cid);
		
		cli.getEnderecos().add(end);
		cli.getTelefone().add(cliDTO.getTelefone1());
		
		if(cliDTO.getTelefone2() != null) {
			cli.getTelefone().add(cliDTO.getTelefone2());
		}
		if(cliDTO.getTelefone3() != null) {
			cli.getTelefone().add(cliDTO.getTelefone3());
		}
		
		
		
		return cli;
	}

}
