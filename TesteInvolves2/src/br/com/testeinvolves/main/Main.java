package br.com.testeinvolves.main;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Scanner;

import br.com.testeinvolves.entity.Cidade;
import br.com.testeinvolves.utils.CidadeUtils;
import br.com.testeinvolves.utils.CsvReader;

public class Main {
	public static void main(String[] args) {
		//CsvReader.readCsv();
		Scanner sc = new Scanner(System.in);
		int choice = 99;
		CsvReader reader = new CsvReader();
		List<Cidade> cidades = reader.load();
		if(cidades != null){
			do {
				try {
					System.out.println("Lista de Comandos");
					System.out.println("1 - escreve no console a contagem total de registros importados"
							+ "\n2 - escreve no console o total de valores distintos da propriedade (coluna) selecionada"
							+ "\n3 - escreve no console a linha de cabeçalho e todas as linhas em que a propriedade selecionada possua o valor enviado"
							+ "\n0 - Close (fecha o programa)");
					choice = Integer.parseInt(sc.nextLine());
				} catch (Exception e) {
					choice = 99;
				}
				switch (choice) {
				case 0:
					System.out.println("Bye bye!");
					System.exit(0);
					break;
				case 1:
					System.out.println("O total de registros encontrado(s) é igual a " + cidades.size());
					break;
				case 2:
					System.out.println("Escolha uma coluna: \n" + CsvReader.retornaColunas());
					try {
						String coluna = sc.nextLine();
						System.out.println("O total de registros distintos encontrados nesta coluna é de " + CidadeUtils.countDistinct(cidades, coluna));;
					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
						System.out.println("Coluna inválida.");
					}
					break;
				case 3:
					System.out.println("Escolha uma coluna: \n" + CsvReader.retornaColunas());
					try {
						String coluna = sc.nextLine();
						System.out.println("Digite o valor a ser pesquisado:");
						String valor = sc.nextLine();
						String resultado = CidadeUtils.filter(cidades, coluna, valor);
						if(resultado.isEmpty()){
							System.out.println("Nenhum valor encontrado");
						}else{
							System.out.println(CsvReader.retornaColunas());
							System.out.println(resultado);;
						}
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("Coluna inválida.");
					}
					break;
				default:
					System.out.println("Comando inválido.");
					break;
				}
			} while (choice > 0);
		}else{
			System.out.println("Ocorreu um erro ao ler o arquivo. Contate o suporte.");
		}
	}
}
