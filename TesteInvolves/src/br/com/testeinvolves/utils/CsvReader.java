package br.com.testeinvolves.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

	public static String comandos(String comando){
		if(comando.equalsIgnoreCase("count")){
			return "O total de registros encontrado(s) é igual a " +count();
		}
		if(comando.toLowerCase().split(" ").length == 3 && 
		   comando.toLowerCase().split(" ")[0].equals("count") && 
		   comando.toLowerCase().split(" ")[1].equals("distinct")){
			String[] colunas = retornaColunas().split(",");
			String coluna = comando.split(" ")[2];
			for(int i = 0; i < colunas.length; i++){
				if(colunas[i].equalsIgnoreCase(coluna)){
					return "Foram encontrados " + countColuna(i);
				}
			}
			return "Nenhum valor encontrado";
		}
		if(comando.toLowerCase().split(" ").length >= 3 && 
		   comando.toLowerCase().split(" ")[0].equals("filter")){
			String cabecalho = retornaColunas();
			String[] colunas = cabecalho.split(",");
			String[] comands = comando.split(" ");
			String valor = "";
			for(int i = 2; i < comands.length; i++){
				if(!valor.equals("")) valor += " ";
				valor += comands[i];
			}
			for(int i = 0; i < colunas.length; i++){
				if(colunas[i].equalsIgnoreCase(comands[1])){
					return cabecalho + "\n" + filter(i, valor);
				}
			}
		}
		return "Comando inválido";
	}
	public static String filter(int coluna, String valor){
		try {
			BufferedReader bfr = new BufferedReader(new InputStreamReader(CsvReader.class.getResourceAsStream("/resources/cidades.csv")));
			String retorno = "";
			String s;
			String[] line;
			bfr.readLine();
			while((s = bfr.readLine()) != null){
				line = s.split(",");
				if(line[coluna].equalsIgnoreCase(valor)){
					retorno += s + "\n";
				}
			}
			bfr.close();
			return retorno;
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException ex){
			ex.printStackTrace();
		}
		return "Valor não encontrado";
	}
	public static int countColuna(int coluna){
		try {
			BufferedReader bfr = new BufferedReader(new InputStreamReader(CsvReader.class.getResourceAsStream("/resources/cidades.csv")));
			String s;
			String[] line;
			bfr.readLine();
			List<String> colunas = new ArrayList<>();
			while((s = bfr.readLine()) != null){
				boolean encontrado = false;
				line = s.split(",");
				for(String col : colunas){
					if(col.equals(line[coluna])){
						encontrado = true;
					}
				}
				if(!encontrado) colunas.add(line[coluna]);
			}
			bfr.close();
			return colunas.size();
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException ex){
			ex.printStackTrace();
		}
		return 0;
	}
	public static String retornaColunas(){
		String s = "";
		try {
			BufferedReader bfr = new BufferedReader(new InputStreamReader(CsvReader.class.getResourceAsStream("/resources/cidades.csv")));
			s = bfr.readLine();
			bfr.close();
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException ex){
			ex.printStackTrace();
		}
		return s;
	}
	public static int count(){
		try {
			int count = 0;
			BufferedReader bfr = new BufferedReader(new InputStreamReader(CsvReader.class.getResourceAsStream("/resources/cidades.csv")));
			String s;
			bfr.readLine();
			while((s = bfr.readLine()) != null){
				count++;
			}
			bfr.close();
			return count;
		}catch (FileNotFoundException e){
			e.printStackTrace();
			return 0;
		}catch (IOException ex){
			ex.printStackTrace();
			return 0;
		}
	}
}
