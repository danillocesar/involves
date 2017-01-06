package br.com.testeinvolves.main;

import java.util.Scanner;

import br.com.testeinvolves.utils.CsvReader;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String comando = "";
		System.out.println("Bem vindo, Involvido!");
		while(true){
			System.out.println("Colunas para referência: " + CsvReader.retornaColunas());
			System.out.println("Lista de Comandos");
			System.out.println("count (escreve no console a contagem total de registros importados)"
								+ "\ncount distinct [coluna] (escreve no console o total de valores distintos da propriedade (coluna) enviada)"
								+ "\nfilter [coluna] [valor] (escreve no console a linha de cabeçalho e todas as linhas em que a propriedade enviada possua o valor enviado)"
								+ "\nclose (fecha o programa)");
			comando = sc.nextLine();
			if(comando.equals("close")){
				System.out.println("Bye Involvido!");
				System.exit(0);
			}else{
				//executa comando
				System.out.println(CsvReader.comandos(comando));
			}
		}
	}
	
	
}
