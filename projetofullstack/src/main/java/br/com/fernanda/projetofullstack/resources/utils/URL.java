package br.com.fernanda.projetofullstack.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static String decodeParam(String param) {
		try {
			return URLDecoder.decode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static List<Integer> decodeIntLis(String cat) {
		String array[] = cat.split(",");
		List<Integer> lista = new ArrayList<>();
		
		for(int i=0; i<array.length; i++) {
			lista.add(Integer.parseInt(array[i]));
		}
		
		return lista;
	}
}
