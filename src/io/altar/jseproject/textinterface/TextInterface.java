package io.altar.jseproject.textinterface;

import java.util.Scanner;

public class TextInterface {

	public void screenInitial() {
		char temp;
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("Selecione uma opcao");
			System.out.println("1 - Listar Produtos");
			System.out.println("2 - Listar Prateleiras");
			System.out.println("3 - Sair");
			
			temp = sc.nextLine().charAt(0);

			switch (temp) {
			case '1':
				screenListProducts();
				break;
			case '2':
				screenListShelves();
				break;
			case '3':
				break;
			default:
				System.out.println("opcao inválida");

				break;
			}
		} while (temp < '1' || temp > '3');
		sc.close();
	}

	public void screenListProducts() {
		char temp;
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("1 - Criar um novo Produto");
			System.out.println("2 - Editar produto existente");
			System.out.println("3 - Consultar detalhe do produto");
			System.out.println("4 - Remover um produto");
			System.out.println("5 - Voltar ao Ecra anterior");
			temp = sc.nextLine().charAt(0);

			switch (temp) {
			case '1':
				break;
			case '2':
				break;
			case '3':
				break;
			case '4':
				break;
			case '5':
				screenInitial();
				break;
			default:
				System.out.println("opcao inválida");

				break;
			}
		} while (temp < '1' || temp > '5');
		sc.close();
	}

	public void screenListShelves() {
		char temp;
		Scanner sc = new Scanner(System.in);
		do{
			System.out.println("1 - Criar um novo prateleira");
			System.out.println("2 - Editar prateleira existente");
			System.out.println("3 - Consultar detalhe da prateleira");
			System.out.println("4 - Remover uma prateleira");
			System.out.println("5 - Voltar ao Ecra anterior");
			temp = sc.nextLine().charAt(0);

	
		
		switch (temp) {
		case '1':
			break;
		case '2':
			break;
		case '3': 
			break;
		case '4':
			break;
		case '5':
			screenInitial();
			break;
		default:
			System.out.println("opcao inválida");
			
			break;
		}
		}while(temp<'1' || temp>'5');
		sc.close();
	}

}

