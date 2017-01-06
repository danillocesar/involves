package br.com.testeinvolves.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import br.com.testeinvolves.entity.Cidade;

public class CsvReader {
	
	public List<Cidade> load(){
		try {
			List<Cidade> cidades = new ArrayList<>();
			BufferedReader bfr = new BufferedReader(new InputStreamReader(CsvReader.class.getResourceAsStream("/resources/cidades.csv")));
			String s;
			String[] line; 
			bfr.readLine();
			while((s = bfr.readLine()) != null){
				line = s.split(",");
				cidades.add(new Cidade(line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7], line[8], line[9]));
			}
			bfr.close();
			return cidades;
		}catch (FileNotFoundException e){
			e.printStackTrace();
			return null;
		}catch (IOException ex){
			ex.printStackTrace();
			return null;
		}catch(NullPointerException ne){
			ne.printStackTrace();
			return null;
		}
	}
	public static String retornaColunas(){
		try {
			BufferedReader bfr = new BufferedReader(new InputStreamReader(CsvReader.class.getResourceAsStream("/resources/cidades.csv")));
			String colunas = bfr.readLine();
			bfr.close();
			return colunas;
		}catch (FileNotFoundException e){
			e.printStackTrace();
			return "";
		}catch (IOException ex){
			ex.printStackTrace();
			return "";
		}
	}
}
