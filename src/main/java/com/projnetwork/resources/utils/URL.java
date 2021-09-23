package com.projnetwork.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static String decodeParam(String s) {
		
		try {
			return URLDecoder.decode(s,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return "";
			
		}
	
	}
	
	public static List<Integer> decodeList(String arg){
		
		String listaArg[]=arg.split(",");
		List<Integer> lista=new ArrayList<>();
		for(String x:listaArg) {
			lista.add(Integer.valueOf(x));
			
		}
		
		return lista;
		
	}

}
