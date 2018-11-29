package io.altar.jseproject.textinterface;

import java.util.ArrayList;
import java.util.Scanner;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.ProductRepository;
import io.altar.jseproject.repositories.ShelfRepository;

public class TextInterface {
	// menu inicial;
	private static ProductRepository productRepository = ProductRepository.getInstance();
	private static ShelfRepository shelfRepository = ShelfRepository.getInstance();
	public Scanner sc = new Scanner(System.in);

	public void screenInitial() {
		char temp;
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
		do {
			System.out.println(productRepository.getAll());
			System.out.println("Por favor selecione uma das seguintes opcoes:");
			System.out.println("1 - Criar um novo Produto");
			System.out.println("2 - Editar produto existente");
			System.out.println("3 - Consultar detalhe do produto");
			System.out.println("4 - Remover um produto");
			System.out.println("5 - Voltar ao Ecra anterior");
			temp = sc.nextLine().charAt(0);
			switch (temp) {
			case '1':
				newProduct();
				screenListProducts();
				break;
			case '2':
				System.out.println(
						"Em seguida será apresentado o valor de cada atributo para o objecto com o ID que indicar.");
				System.out.println(
						"Caso pretenda alterar esse valor deverá introduzir um novo. Se pretender manter o mesmo valor deverá unicamente precionar Enter.");
				editProduct();
				screenInitial();
				break;
			case '3':
				consultProduct();
				screenInitial();
				break;
			case '4':
				removeProduct();
				screenInitial();
				break;
			case '5':
				screenInitial();
				break;
			default:
				System.out.println("opcao inválida");
				sc.nextLine();
				sc.close();
				screenListProducts();
				break;
			}
		} while (temp < '1' || temp > '5');

	}

	// menu prateleiras;
	public void screenListShelves() {
		char temp;
		System.out.println(shelfRepository.getAll());
		System.out.println("Por favor selecione uma das seguintes opcoes:");
		do {
			System.out.println("1 - Criar uma nova prateleira");
			System.out.println("2 - Editar prateleira existente");
			System.out.println("3 - Consultar detalhe da prateleira");
			System.out.println("4 - Remover uma prateleira");
			System.out.println("5 - Voltar ao Ecra anterior");
			temp = sc.nextLine().charAt(0);

			switch (temp) {
			case '1':
				newShelf();
				screenInitial();
				break;
			case '2':
				editShelf();
				screenInitial();
				break;
			case '3':
				consultShelf();
				screenInitial();
				break;
			case '4':
				removeShelf();
				screenInitial();
				break;
			case '5':
				screenInitial();
				break;
			default:
				System.out.println("opcao inválida");
				sc.nextLine();
				sc.close();
				screenListShelves();
				break;
			}
		} while (temp < '1' || temp > '5');
		sc.nextLine();

	}

	// funcao para criar novo produto
	public void newProduct() {
		float preco = 0;
		float iva = 0;
		float pvp = 0;
		float discount = 0;
		Shelf prateleira;
		Product produto = new Product(0, 0, 0, 0); // inicializo o produto e
													// depois faco sets no fim
													// para alterar
		float rentPrice = 0;
		Long prateleiraId;
		System.out.println("Criar novo produto:");
		while (true) { // garante que volta a ser chamado ate ser introduzido um
						// inteiro valido;
			System.out.println("Qual o preco?");
			if (sc.hasNextFloat()) {
				preco = sc.nextFloat();
				break;
			} else {
				sc.nextLine(); // limpa o scanner
			}
		}
		sc.nextLine();
		while (true) {
			System.out.println("Qual o iva?");
			if (sc.hasNextFloat()) {
				iva = sc.nextFloat();
				break;
			} else {
				sc.nextLine();
			}
		}
		sc.nextLine();
		while (true) {
			System.out.println("Qual o valor do desconto?");
			if (sc.hasNextFloat()) {
				discount = sc.nextFloat();
				break;
			} else {
				sc.nextLine();
			}
		}
		sc.nextLine();
		while (true) {
			System.out.println("Qual o pvp?");
			if (sc.hasNextFloat()) {
				pvp = sc.nextFloat();
				break;
			} else {
				sc.nextLine();
			}
		}
		while (true) {
			System.out.println("Quer adicionar um produto existente a esta prateleira? (responda S ou N)");
			char temp = sc.next().charAt(0);
			if (temp == 's' || temp == 'S') {
				System.out.println("Indique o ID da prateleira na qual quer adicionar o produto");
				if (!sc.hasNextLong()) {
					continue;
				}
				prateleiraId = sc.nextLong();
				if (!existingShelfID(prateleiraId)) {
					prateleira = new Shelf(100, rentPrice);
					theRentingPriceIs();
					produto.addShelf(prateleira); // adiciona a prateleira ao
													// produto
					prateleira.setProduct(produto); // adiciona o produto a
													// prateleira
					shelfRepository.save(prateleira); // grava a prateleira
					sc.nextLine();
					break;
				} else {
					prateleira = shelfRepository.findById(prateleiraId);
					if (prateleira.getProduct() != null) {
						System.out.println("Sem espaco disponível");
					} else {
						prateleira.setProduct(produto);
						produto.addShelf(prateleira);
					}
					sc.nextLine();
				}
			} else {
				sc.nextLine();
				break;
			}
		}
		produto.setIva(iva);
		produto.setDiscountValue(discount);
		produto.setPrice(preco);
		produto.setPvp(pvp);
		productRepository.save(produto);
		System.out.println("O novo produto é:" + produto.toString());
	}

	// funcao para criar uma nova prateleira
	public void newShelf() {
		float capacidade = 0;
		float rentPrice = 0;
		Product produto;
		System.out.println("Criar nova prateleira:");
		Shelf prateleira = new Shelf(0, 0);
		while (true) { // garante que volta a ser chamado ate ser introduzido um
						// inteiro valido;
			System.out.println("Qual a capacidade?");
			if (sc.hasNextFloat()) {
				capacidade = sc.nextFloat();
				break;
			} else {
				sc.nextLine(); // limpa o scanner
			}
		}
		sc.nextLine();

		while (true) {
			System.out.println("Qual o preco de aluguer da localizacao (diario)?");
			if (sc.hasNextFloat()) {
				rentPrice = sc.nextFloat();
				break;
			} else {
				sc.nextLine();
			}
		}
		while (true) {
			System.out.println("Quer adicionar um produto existente a esta prateleira? (responda S ou N)");
			sc.nextLine();
			char temp = sc.next().charAt(0);
			if (temp == 's' || temp == 'S') {
				
				System.out.println("Indique o ID do produto que pretende adicionar");

				if (!sc.hasNextFloat()) {
					sc.nextLine();
					continue;
				}
				long id = sc.nextLong();
				if (existingProductID(id)) {
					sc.nextLine();
					produto = productRepository.findById(id);
					prateleira.setProduct(produto);
					produto.addShelf(prateleira);
					break;
				} else {
					System.out.println("O produto nao existe.");
				}

			} else {
				break;
			}
		}
		prateleira.setCapacity(capacidade);
		prateleira.setRentPrice(rentPrice);
		shelfRepository.save(prateleira);
		System.out.println(prateleira);
	}

	public void theRentingPriceIs() {
		while (true) {
			System.out.println("Qual o preco de aluguer da localizacao (diario)?");
			float rentPrice = 0;
			if (sc.hasNextFloat()) {
				rentPrice = sc.nextFloat();
				break;
			} else {
				sc.nextLine();
			}
		}
	}

	// funcao para remover produto
	public void removeProduct() {
		System.out.println("Qual o ID do produto que pretende remover?");
		long id = 0;
		while (true) {
			if (sc.hasNextInt()) {
				id = sc.nextInt();
				System.out.println("Tem a certeza que pretende remover? (responda S ou N)");
				char temp = sc.next().charAt(0);
				if (temp == 's' || temp == 'S') {
					productRepository.removeById(id);
					System.out.println(productRepository.getAll());
					sc.nextLine();
				}
				break;
			} else {
				sc.nextLine();
			}
		}
	}

	// funcao para consultar produto
	public void consultProduct() {
		System.out.println("Qual o ID do produto que pretende consultar?");
		long id = sc.nextInt();
		System.out.println(productRepository.findById(id));
		sc.nextLine();
	}

	// funcao para converter inteiro em produto
	public boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean existingProductID(long id) {

		if (productRepository.findById(id) != null)
			return true;
		else
			return false;

	}

	public boolean existingShelfID(long id) {

		if (shelfRepository.findById(id) != null)
			return true;
		else
			return false;

	} // funcao para editar produto

	public boolean existingProductInShelf(long id) {
		if (shelfRepository.findById(id).getProduct() != null)
			return true;
		else
			return false;
	}

	public void editProduct() {
		long id = 0;
		while (true) { // garante que volta a ser chamado ate ser introduzido um
						// inteiro valido;
			System.out.println("Qual o ID do produto que pretende editar?");
			if (sc.hasNextInt()) {
				id = sc.nextInt();
				break;
			} else {
				sc.nextLine(); // limpa o scanner
			}
		}
		if (existingProductID(id)) {
			String line = "";
			Product produtoActual = productRepository.findById(id);
			float preco = produtoActual.getPrice();
			float iva = produtoActual.getIva();
			float pvp = produtoActual.getPvp();
			float discount = produtoActual.getDiscountValue();
			ArrayList<Shelf> listaPrateleirasActuais = produtoActual.getList();
			long prateleiraId;
			Shelf prateleiraNova;
			int rentPrice = 0;
			sc.nextLine();
			while (true) {
				System.out.println("O preco actual é:" + preco);
				line = sc.nextLine();
				if (line.length() == 0) { // para ver os enters
					System.out.println("Enter");
					break;
				} else {
					if (isInteger(line)) {
						preco = Integer.parseInt(line);
						break;
					} else {
						System.out.println("Asneira");

					}
				}
			}
			while (true) {
				System.out.println("O desconto actual é:" + discount);
				line = sc.nextLine();

				if (line.length() == 0) {
					System.out.println("Enter");
					break;
				} else {
					if (isInteger(line)) {
						discount = Integer.parseInt(line);
						break;
					} else {
						System.out.println("Asneira");

					}
				}
			}

			while (true) {
				System.out.println("O iva actual é:" + iva);
				line = sc.nextLine();

				if (line.length() == 0) {
					System.out.println("Enter");
					break;
				} else {
					if (isInteger(line)) {
						iva = Integer.parseInt(line);
						break;
					} else {
						System.out.println("Asneira");

					}
				}
			}

			while (true) {
				System.out.println("O pvp actual é:" + pvp);
				line = sc.nextLine();
				if (line.length() == 0) {
					System.out.println("Enter");
					break;
				} else {
					if (isInteger(line)) {
						pvp = Integer.parseInt(line);
						break;
					} else {
						System.out.println("Asneira");

					}
				}
			}
			while (true) {
				System.out.println("A prateleira actual é:" + listaPrateleirasActuais);
				line = sc.nextLine();
				if (line.length() == 0) {
					produtoActual.removeListShelf();
					break;
				}
				System.out.println("Quer adicionar uma nova prateleira a este produto? (responda S ou N)");
				char temp = sc.next().charAt(0);
				if (temp == 's' || temp == 'S') {
					System.out.println("Indique o ID da prateleira na qual quer adicionar o produto");
					if (!sc.hasNextLong()) {
						continue;
					}
					prateleiraId = sc.nextLong();
					if (!existingShelfID(prateleiraId)) {
						prateleiraNova = new Shelf(100, rentPrice);
						theRentingPriceIs();
						produtoActual.addShelf(prateleiraNova); // adiciona a
																// prateleira ao
																// produto
						prateleiraNova.setProduct(produtoActual); // adiciona o
																	// produto a
																	// prateleira
						shelfRepository.save(prateleiraNova); // grava a
																// prateleira
						sc.nextLine();
						break;
					} else {
						prateleiraNova = shelfRepository.findById(prateleiraId);
						if (prateleiraNova.getProduct() != null) { // verifica
																	// se a
																	// prateleira
																	// está
																	// cheia
							System.out.println("Sem espaco disponível");
						} else {
							prateleiraNova.setProduct(produtoActual);
							produtoActual.addShelf(prateleiraNova);
						}
						sc.nextLine();
					}
				} else {
					sc.nextLine();
					break;
				}
			}
			Product produto = new Product(preco, discount, iva, pvp);
			produto.setId((long) id);
			productRepository.update(produto);
			System.out.println(produto.toString());
			sc.nextLine();
		} else {
			System.out.println("O ID introduzido nao é válido.");
			editProduct();
		}
	}

	// funcao para remover prateleira
	public void removeShelf() {
		System.out.println("Qual o ID da prateleira que pretende remover?");
		long id = 0;
		while (true) {
			if (sc.hasNextInt()) {
				id = sc.nextInt();
				System.out.println("Tem a certeza que pretende remover? (responda S para sim ou N para nao)");
				char temp = sc.next().charAt(0);
				if (temp == 's' || temp == 'S') {
					shelfRepository.removeById(id);
					System.out.println(shelfRepository.getAll());
					sc.nextLine();
				}
				break;
			} else {
				sc.nextLine();
			}
		}
	}

	// funcao para consultar prateleira
	public void consultShelf() {
		System.out.println("Qual o ID da prateleira que pretende consultar?");
		long id = sc.nextInt();
		System.out.println(shelfRepository.findById(id));
		sc.nextLine();
	}

	// funcao para editar Prateleira
	public void editShelf() {
		long id = 0;
		while (true) { // garante que volta a ser chamado ate ser introduzido um
						// inteiro valido;
			System.out.println("Qual o ID da prateleira que pretende editar?");
			if (sc.hasNextInt()) {
				id = sc.nextInt();
				break;
			} else {
				sc.nextLine(); // limpa o scanner
			}
		}
		if (existingShelfID(id)) {
			String line = "";
			Shelf prateleiraOriginal = shelfRepository.findById(id);
			float capacidade = prateleiraOriginal.getCapacity();
			float rentPrice = prateleiraOriginal.getRentPrice();
			Product product = prateleiraOriginal.getProduct();

			sc.nextLine();
			while (true) {
				System.out.println("A capacidade actual é:" + capacidade);
				line = sc.nextLine();
				if (line.length() == 0) { // para ver os enters
					System.out.println("Enter");
					break;
				} else {
					if (isInteger(line)) {
						capacidade = Integer.parseInt(line);
						break;
					} else {
						System.out.println("Asneira");

					}
				}
			}

			while (true) {
				System.out.println("O preco de aluguer actual é:" + rentPrice);
				line = sc.nextLine();

				if (line.length() == 0) {
					System.out.println("Enter");
					break;
				} else {
					if (isInteger(line)) {
						rentPrice = Integer.parseInt(line);
						break;
					} else {
						System.out.println("Asneira");

					}
				}
			}
			while (true) {
				System.out.println("O producto actual :" + product.getId());
				line = sc.nextLine();
				if (line.length() == 0) {
					if (product != null) {
						product.removeShelf(prateleiraOriginal);
					}
					prateleiraOriginal.removeProduct();
					System.out.println("Quer adicionar um produto existente a esta prateleira? (responda S ou N)");
					char temp = sc.next().charAt(0);
					if (temp == 's' || temp == 'S') {
						System.out.print("Qual o id do novo produto que quer adicionar a esta prateleira?");
						if (!sc.hasNextFloat()) {
							sc.nextLine();
							continue;
						}
						long idProduto = sc.nextLong();
						if (existingProductID(idProduto)) {
							sc.nextLine();
							product = productRepository.findById(idProduto);
							prateleiraOriginal.setProduct(product);
							product.addShelf(prateleiraOriginal);
							break;
						} else {
							System.out.println("O produto nao existe.");
						}
					}else{
						break;
					}
				
				Shelf prateleira = new Shelf(capacidade, rentPrice);
				prateleira.setId((long) id);
				prateleira.setProduct(prateleiraOriginal.getProduct());
				shelfRepository.update(prateleira);
				System.out.println(prateleira.toString());
				}
			}
		}
	}
}