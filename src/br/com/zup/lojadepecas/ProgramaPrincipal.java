package br.com.zup.lojadepecas;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import br.com.zup.DAO.PecasDAO;
import br.com.zup.POJO.Pecas;
import br.com.zup.connectionfactory.ConnectionFactory;
import br.com.zup.vendasDAO.Vendas;

public class ProgramaPrincipal {

	public static void cabecalho() {
		System.out.println("-----------------------------------------");
		System.out.println("*      Loja de pe�as do Seu Jos�        *");
		System.out.println("-----------------------------------------");
	}

	public static void menuPrincipal() {

		System.out.println("\n(A) - Gest�o de pe�as");
		System.out.println("(B) - Gest�o de vendas");
		System.out.println("(C) - Finalizar programa");

	}

	public static void menuPecas() {

		System.out.println("\n(1) - Cadastrar nova pe�a");
		System.out.println("(2) - Consultar pe�a");
		System.out.println("(3) - Listar pe�as em estoque");
		System.out.println("(4) - Listar pe�as iniciadas com a mesma letra");
		System.out.println("(5) - Listar pe�as por um modelo de carro");
		System.out.println("(6) - Listar pe�as por categoria");
		System.out.println("(7) - Remover pe�a do estoque");
		System.out.println("(0) - Voltar para o menu principal");

	}

	public static void menuVendas() {

		System.out.println("\n(1) - Realizar venda");
		System.out.println("(2) - Relat�rio de vendas");
		System.out.println("(0) - Voltar para o menu principal");

	}

	public static void cadastrarNovaPeca(Scanner teclado, PecasDAO pecasDao) {

		System.out.print("\nDigite o c�digo de barras: ");
		String codigoDeBarras = teclado.next();

		teclado.nextLine();
		System.out.print("\nDigite o nome da pe�a: ");
		String nomePeca = teclado.nextLine();

		System.out.print("\nDigite o modelo do carro: ");
		String modeloCarro = teclado.next();

		System.out.print("\nDigite o fabricante: ");
		String fabricante = teclado.next();

		System.out.print("\nDigite o pre�o de custo: ");
		float precoCusto = teclado.nextFloat();

		System.out.print("\nDigite o pre�o de venda: ");
		float precoVenda = teclado.nextFloat();

		System.out.print("\nDigite a quantidade em estoque: ");
		int quantidadeEstoque = teclado.nextInt();

		teclado.nextLine();
		System.out.print("\nDigite a categoria: ");
		String categoria = teclado.nextLine();

		Pecas peca = new Pecas(codigoDeBarras, nomePeca, modeloCarro, fabricante, precoCusto, precoVenda,
				quantidadeEstoque, categoria);

		pecasDao.cadastrarPeca(peca);

	}

	public static void consultarPecaPorCodigoBarra(Scanner teclado, PecasDAO pecasDao) {

		System.out.print("\nInforme o n�mero do c�digo de barras: ");
		String codigoBarra = teclado.next();

		Pecas pecas = pecasDao.consultarPecaPorCodigoBarra(codigoBarra);

		System.out.printf("\nRela��o das pe�as com c�digo de barras %s: \n\n", codigoBarra);

		System.out.println("C�digo de Barras = " + pecas.getCodigoBarra() + " Nome = " + pecas.getNome()
				+ " Modelo do carro = " + pecas.getModeloCarro() + " Fabricante = " + pecas.getFabricante()
				+ " Pre�o de Custo = R$" + pecas.getPrecoCusto() + " Pre�o de Venda = R$" + pecas.getPrecoVenda()
				+ " Quantidade Estoque = " + pecas.getQuantidadeEstoque() + " Categoria = " + pecas.getCategoria());

	}

	public static void listarPecasEstoque(Scanner teclado, PecasDAO pecasDao) {

		List<Pecas> estoqueBd = pecasDao.listarPecasEstoque();

		System.out.println("\nRela��o das pe�as em estoque: \n");

		for (Pecas listaEstoque : estoqueBd) {
			System.out.println(listaEstoque);
		}

	}

	public static void listarPecasPorLetra(Scanner teclado, PecasDAO pecasDao) {

		System.out.print("\nDigite uma letra para buscar uma pe�a:");
		String letra = teclado.next().toUpperCase();

		List<Pecas> pecasLetra = pecasDao.listarPecaPorLetra(letra);

		System.out.printf("\nRela��o das pe�as inciadas por %s: \n\n", letra);

		for (Pecas nomePecas : pecasLetra) {
			System.out.println(nomePecas);

		}
	}

	public static void listarPecasPorModelo(Scanner teclado, PecasDAO pecasDao) {

		System.out.print("\nDigite o modelo do carro: ");
		String modelo = teclado.next();

		List<Pecas> pecasModelo = pecasDao.listarPecaPorModelo(modelo);

		System.out.printf("\nRela��o das pe�as para o modelo %s: \n\n", modelo);

		for (Pecas modelos : pecasModelo) {
			System.out.println(modelos);
		}

	}

	public static void listarPecasPorCategoria(Scanner teclado, PecasDAO pecasDao) {

		String opcao = "";
		String categoria = "";

		System.out.println("\n(1) - Som e v�deo");
		System.out.println("(2) - Personaliza��o");
		System.out.println("(3) - Performance");
		System.out.print("\nEscolha uma das op��es acima: ");
		opcao = teclado.next();

		switch (opcao) {

		case "1":

			categoria = "Som e v�deo";

			List<Pecas> pecasCategoria1 = pecasDao.listarPecaPorCategoria(categoria);

			System.out.printf("\nRela��o das pe�as para a categoria %s: \n\n", categoria);

			for (Pecas categorias : pecasCategoria1) {
				System.out.println(categorias);
			}

			break;

		case "2":

			categoria = "Personaliza��o";

			List<Pecas> pecasCategoria2 = pecasDao.listarPecaPorCategoria(categoria);

			System.out.printf("\nRela��o das pe�as para a categoria %s: \n\n", categoria);

			for (Pecas categorias : pecasCategoria2) {
				System.out.println(categorias);
			}

			break;

		case "3":

			categoria = "Perfomance";

			List<Pecas> pecasCategoria3 = pecasDao.listarPecaPorCategoria(categoria);

			System.out.printf("\nRela��o das pe�as para a categoria %s: \n\n", categoria);

			for (Pecas categorias : pecasCategoria3) {
				System.out.println(categorias);
			}

			break;

		}

	}

	public static void removerPeca(Scanner teclado, PecasDAO pecasDao) {

		System.out.print("\nDigite o c�digo de barras da pe�a que deseja excluir: ");
		String codigoDeBarra = teclado.next();

		pecasDao.removerPeca(codigoDeBarra);

	}

	public static void realizaVenda(Scanner teclado, Vendas vendas) {

		System.out.print("\nDigite o c�digo de barras: ");
		String codigoBarra = teclado.next();

		System.out.print("Digite a quantidade desejada: ");
		int quantidadeDesejada = teclado.nextInt();

		vendas.realizaVenda(teclado, quantidadeDesejada, codigoBarra);

	}

	public static void main(String[] args) throws SQLException {
		Connection conn = new ConnectionFactory().getConnection();
		List<Pecas> relatorioVendas = new ArrayList<Pecas>();
		Pecas pecasPojo = new Pecas();
		PecasDAO pecasDao = new PecasDAO();
		Vendas vendas = new Vendas();
		Scanner teclado = new Scanner(System.in);
		String opcaoPrimaria = "";
		String opcaoPecas = "";
		String opcaoVendas = "";

		cabecalho();

		do {
			menuPrincipal();

			System.out.print("\nEscolha uma das op��es acima: ");
			opcaoPrimaria = teclado.next().toUpperCase();

			switch (opcaoPrimaria) {

			case "A":

				do {

					menuPecas();
					System.out.print("\nDigite uma das op��es acima: ");
					opcaoPecas = teclado.next();

					switch (opcaoPecas) {

					case "1":

						cadastrarNovaPeca(teclado, pecasDao);

						break;

					case "2":

						consultarPecaPorCodigoBarra(teclado, pecasDao);

						break;

					case "3":

						listarPecasEstoque(teclado, pecasDao);

						break;

					case "4":

						listarPecasPorLetra(teclado, pecasDao);

						break;

					case "5":

						listarPecasPorModelo(teclado, pecasDao);

						break;

					case "6":

						listarPecasPorCategoria(teclado, pecasDao);

						break;

					case "7":

						removerPeca(teclado, pecasDao);

						break;

					case "0":

						break;

					default:

						System.out.println("\nOp��o inv�lida.Tente uma op��o de '0' a '7'.");

						break;
					}

				} while (!opcaoPecas.equals("0"));

			case "B":

				do {

					menuVendas();
					System.out.print("\nEscolha uma das op��es acima: ");
					opcaoVendas = teclado.next();

					switch (opcaoVendas) {

					case "1":

						realizaVenda(teclado, vendas);

						break;

					case "2":

						vendas.imprimeRelatorioVendas();

						vendas.arquivoVendas();

						break;

					case "0":

						break;

					default:

						System.out.println("\nOp��o inv�lida.Tente uma op��o de '0' a '2'.");

						break;
					}

				} while (!opcaoVendas.equals("0"));

				break;

			case "C":

				System.out.println("\nObrigada por utilizar nosso sistema! =D ");

				break;

			default:
				System.out.println("\nOp��o inv�lida. Tente uma op��o de 'A' a 'C'.");
				break;
			}

		} while (!opcaoPrimaria.equals("C"));

		teclado.close();
		conn.close();

	}

}