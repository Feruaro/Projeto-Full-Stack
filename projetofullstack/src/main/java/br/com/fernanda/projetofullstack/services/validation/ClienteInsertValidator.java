package br.com.fernanda.projetofullstack.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.fernanda.projetofullstack.domains.enums.TipoCliente;
import br.com.fernanda.projetofullstack.dto.ClienteNewDTO;
import br.com.fernanda.projetofullstack.resources.exceptions.FieldMessage;
import br.com.fernanda.projetofullstack.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO cliDto, ConstraintValidatorContext context) {
		List<FieldMessage> lista = new ArrayList<>();

		// Validação para PF
		if (cliDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(cliDto.getCpf_cnpj())) {
			lista.add(new FieldMessage("cpf_cnpj", "CPF inválido!"));
		}
		if (cliDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(cliDto.getCpf_cnpj())) {
			lista.add(new FieldMessage("cpf_cnpj", "CNPJ inválido!"));
		}

		for (FieldMessage e : lista) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}

		return lista.isEmpty();
	}
}