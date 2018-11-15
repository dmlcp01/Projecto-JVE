package io.altar.jseproject.textinterface;

import java.util.Scanner;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;

public class TextInterface {

	public void screenInitial() {
		char temp;
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("Selecione uma opcao:");
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
		sc.nextLine();
		sc.close();
	}

	// menu produtos;
	public void screenListProducts() {
		char temp;
		Scanner sc = new Scanner(System.in);
		System.out.println("Por favor selecione uma das seguintes opcoes:");

		do {
			System.out.println("1 - Criar um novo Produto");
			System.out.println("2 - Editar produto existente");
			System.out.println("3 - Consultar detalhe do produto");
			System.out.println("4 - Remover um produto");
			System.out.println("5 - Voltar ao Ecra anterior");
			temp = sc.nextLine().charAt(0);

			switch (temp) {
			case '1':
				newProduct();
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
		sc.nextLine();
		sc.close();
	}

	// menu prateleiras;
	public void screenListShelves() {
		char temp;
		Scanner sc = new Scanner(System.in);
		System.out.println("Por favor selecione uma das seguintes opcoes:");
		do {
			System.out.println("1 - Criar uma novaÏ prateleira");
			System.out.println("2 - Editar prateleira existente");
			System.out.println("3 - Consultar detalhe da prateleira");
			System.out.println("4 - Remover uma prateleira");
			System.out.println("5 - Voltar ao Ecra anterior");
			temp = sc.nextLine().charAt(0);

			switch (temp) {
			case '1':
				newShelf();
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
		sc.nextLine();
		sc.close();
	}

	// funcao para criar novo produto

	public void newProduct() {
		Scanner sc = new Scanner(System.in);
		int preco = 0;
		int iva = 0;
		int pvp = 0;
		System.out.println("Criar novo produto:");
		while (true) { // garante que volta a ser chamado ate ser introduzido
						// inteiro valido;
			System.out.println("Qual o preco?");
			if (sc.hasNextInt()) {
				preco = sc.nextInt();
				break;
			} else {
				sc.nextLine(); // limpa o scanner
			}

		}
		sc.nextLine();
		while (true) {
			System.out.println("Qual o iva?");
			if (sc.hasNextInt()) {
				iva = sc.nextInt();
				break;
			} else {
				sc.nextLine();
			}
		}
		sc.nextLine();

		while (true) {
			System.out.println("Qual o pvp?");
			if (sc.hasNextInt()) {
				pvp = sc.nextInt();
				break;
			} else {
				sc.nextLine();
			}
		}
		Product produto = new Product(null, preco, iva, pvp);
		System.out.println(produto);
		sc.nextLine();
		sc.close();
	}

	// funcao para criar uma nova prateleira

	public void newShelf() {
		Scanner sc = new Scanner(System.in);
		int capacidade = 0;
		int rentPrice = 0;
		System.out.println("Criar nova prateleira:");
		while (true) { // garante que volta a ser chamado ate ser introduzido
						// inteiro valido;
			System.out.println("Qual a capacidade?");
			if (sc.hasNextInt()) {
				capacidade = sc.nextInt();
				break;
			} else {
				sc.nextLine(); // limpa o scanner
			}
		}
		sc.nextLine();
		while (true) {
			System.out.println("Qual o preco de aluguer da localizacao (diario)?");
			if (sc.hasNextInt()) {
				rentPrice = sc.nextInt();
				break;
			} else {
				sc.nextLine();
			}
		}
		Shelf prateleira = new Shelf(capacidade, null, rentPrice);
		System.out.println(prateleira);
		sc.nextLine();
		sc.close();
	}
}