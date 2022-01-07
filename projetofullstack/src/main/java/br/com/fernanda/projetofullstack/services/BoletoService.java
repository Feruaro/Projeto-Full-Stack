package br.com.fernanda.projetofullstack.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.fernanda.projetofullstack.domains.PagamentoBoleto;

@Service
public class BoletoService {

	public void preencherPagtoBoleto(PagamentoBoleto pagto, Date instante) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instante);
		cal.add(Calendar.DAY_OF_MONTH, 7);		
		
		pagto.setDataVencimento(cal.getTime());		
	}
	
	
}
