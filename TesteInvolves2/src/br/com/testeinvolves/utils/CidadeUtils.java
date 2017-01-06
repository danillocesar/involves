package br.com.testeinvolves.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import br.com.testeinvolves.entity.Cidade;

public class CidadeUtils {

	public static String retornaLinha(Cidade c){
		String s = "";
		Field[] fields = c.getClass().getDeclaredFields();
        for (Field classField : fields){
        	if(!s.isEmpty()) s += ", ";
        	try {
        		classField.setAccessible(true);
				s += classField.get(c).toString();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return s;
	}
	public static String filter(List<Cidade> cidades, String coluna, String valor) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		String s = "";
		for(Cidade c : cidades){
			Field field = c.getClass().getDeclaredField(coluna);
			field.setAccessible(true);
			if(field.get(c).toString().equalsIgnoreCase(valor)){
				s += retornaLinha(c) + "\n";
			}
		}
		return s;
	}
	public static int countDistinct(List<Cidade> cidades, String coluna) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		List<Cidade> cs = new ArrayList<>();
		for(Cidade c : cidades){
			boolean b = false;
			for(Cidade c2 : cs){
				Field field = c.getClass().getDeclaredField(coluna);
				field.setAccessible(true);
				Field field2 = c2.getClass().getDeclaredField(coluna);
				field2.setAccessible(true);
				if(field.get(c).toString().equals(field2.get(c2).toString())){
					b = true;
				}
			}
			if(!b) cs.add(c);
		}
		return cs.size();
	}
}
