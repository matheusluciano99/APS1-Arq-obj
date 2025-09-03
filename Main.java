import Classes.Cartao.Cartao;
import Classes.Cliente.Cliente;
import Classes.ContaCorrente.ContaCorrente;
import Classes.Movimentacao.Movimentacao;
import java.time.LocalDate;
import java.util.*;

public class Main {
	private static final Map<String, ContaCorrente> contasPorCpf = new HashMap<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("""
				Menu:
				1) Cadastrar cliente e conta
				2) Listar clientes e contas
				3) Saque na conta
				4) Dep\u00f3sito na conta
				5) Emitir novo cart\u00e3o
				6) Cancelar cart\u00e3o
				7) Listar movimenta\u00e7\u00f5es da conta
				0) Sair"""
			);
			System.out.print("Opção: ");
			String op = sc.nextLine().trim();
			switch (op) {
				case "1" -> cadastrarClienteEConta(sc);
				case "2" -> listarClientesEContas();
				case "3" -> saque(sc);
				case "4" -> deposito(sc);
				case "5" -> emitirCartao(sc);
				case "6" -> cancelarCartao(sc);
				case "7" -> listarMovimentacoes(sc);
				case "0" -> {
					System.out.println("Até mais!");
					return;
				}
				default -> System.out.println("Opção inválida.");
			}
		}
	}

	private static void cadastrarClienteEConta(Scanner sc) {
		System.out.print("CPF: ");
		String cpf = sc.nextLine().trim();
		if (cpf.isEmpty() || !cpf.chars().allMatch(Character::isDigit)) {
			System.out.println("CPF inválido (use apenas dígitos).");
			return;
		}
		if (contasPorCpf.containsKey(cpf)) {
			System.out.println("Cliente já possui conta.");
			return;
		}
		System.out.print("Nome: ");
		String nome = sc.nextLine().trim();
		if (nome.isEmpty()) {
			System.out.println("Nome é obrigatório.");
			return;
		}
		System.out.print("Data nascimento (YYYY-MM-DD): ");
		LocalDate dn;
		try {
			dn = LocalDate.parse(sc.nextLine().trim());
		} catch (Exception e) {
			System.out.println("Data de nascimento inválida.");
			return;
		}
		System.out.print("Salário: ");
		Float salario;
		try {
			salario = Float.valueOf(sc.nextLine().trim());
			if (salario < 0) { System.out.println("Salário não pode ser negativo."); return; }
		} catch (NumberFormatException e) {
			System.out.println("Salário inválido.");
			return;
		}

		System.out.print("Agência: ");
		String ag = sc.nextLine().trim();
		System.out.print("Número da conta: ");
		String num = sc.nextLine().trim();
		System.out.print("Limite: ");
		Float limite;
		try {
			limite = Float.valueOf(sc.nextLine().trim());
			if (limite < 0) { System.out.println("Limite não pode ser negativo."); return; }
		} catch (NumberFormatException e) {
			System.out.println("Limite inválido.");
			return;
		}

		Cliente cli = new Cliente(cpf, nome, dn, salario);
		Float saldoInicial = 0f; // regra: conta inicia com saldo 0
		ContaCorrente conta = new ContaCorrente(ag, num, saldoInicial, limite, cli);
		contasPorCpf.put(cpf, conta);
		System.out.println("Conta criada para " + cli);
	}

	private static ContaCorrente pegarContaPorCpf(Scanner sc) {
		System.out.print("CPF do cliente: ");
		String cpf = sc.nextLine().trim();
		ContaCorrente conta = contasPorCpf.get(cpf);
		if (conta == null) System.out.println("Conta não encontrada para CPF.");
		return conta;
	}

	private static void listarClientesEContas() {
		if (contasPorCpf.isEmpty()) {
			System.out.println("Nenhuma conta cadastrada.");
			return;
		}
		contasPorCpf.values().forEach(c -> {
			System.out.println(c.toString());
		});
	}

	private static void saque(Scanner sc) {
		ContaCorrente conta = pegarContaPorCpf(sc);
		if (conta == null) return;
		System.out.print("Valor do saque: ");
		Float v;
		try {
			v = Float.valueOf(sc.nextLine().trim());
			if (v <= 0) { System.out.println("Valor deve ser positivo."); return; }
		} catch (NumberFormatException e) {
			System.out.println("Valor inválido.");
			return;
		}
		conta.saque(v);
		System.out.printf("Novo saldo: %.2f\n", conta.getSaldo());
	}

	private static void deposito(Scanner sc) {
		ContaCorrente conta = pegarContaPorCpf(sc);
		if (conta == null) return;
		System.out.print("Valor do depósito: ");
		Float v;
		try {
			v = Float.valueOf(sc.nextLine().trim());
			if (v <= 0) { System.out.println("Valor deve ser positivo."); return; }
		} catch (NumberFormatException e) {
			System.out.println("Valor inválido.");
			return;
		}
		conta.deposito(v);
		System.out.printf("Novo saldo: %.2f\n", conta.getSaldo());
	}

	private static void emitirCartao(Scanner sc) {
		ContaCorrente conta = pegarContaPorCpf(sc);
		if (conta == null) return;
		System.out.print("Número do cartão: ");
		String numero = sc.nextLine().trim();
		if (numero.isEmpty()) { System.out.println("Número do cartão é obrigatório."); return; }
		System.out.print("Tipo (DEBITO/CREDITO): ");
		String tipo = sc.nextLine().trim().toUpperCase();
		if (!("DEBITO".equals(tipo) || "CREDITO".equals(tipo))) {
			System.out.println("Tipo inválido. Use DEBITO ou CREDITO.");
			return;
		}
		System.out.print("Validade (YYYY-MM-01): ");
		LocalDate validade;
		try {
			validade = LocalDate.parse(sc.nextLine().trim());
		} catch (Exception e) {
			System.out.println("Validade inválida.");
			return;
		}
		if (validade.isBefore(LocalDate.now())) { System.out.println("Validade não pode ser no passado."); return; }
		// checar duplicidade de número na conta
		for (Cartao c : conta.listaCartoes()) {
			if (numero.equals(c.getNumeroCartao())) { System.out.println("Já existe cartão com esse número."); return; }
		}
		Cartao cartao = new Cartao(numero, tipo, validade);
		// adicionar cartão à lista da conta
		conta.listaCartoes().add(cartao);
		System.out.println("Cartão emitido: " + cartao);
	}

	private static void cancelarCartao(Scanner sc) {
		ContaCorrente conta = pegarContaPorCpf(sc);
		if (conta == null) return;
		System.out.print("Número do cartão a cancelar: ");
		String numero = sc.nextLine().trim();
		if (conta.listaCartoes().isEmpty()) { System.out.println("Conta sem cartões."); return; }
		boolean ok = false;
		for (Cartao c : conta.listaCartoes()) {
			if (numero.equals(c.getNumeroCartao())) {
				c.cancelaCartao();
				ok = true;
				break;
			}
		}
		System.out.println(ok ? "Cartão cancelado." : "Cartão não encontrado para essa conta.");
	}

	private static void listarMovimentacoes(Scanner sc) {
		ContaCorrente conta = pegarContaPorCpf(sc);
		if (conta == null) return;
		List<Movimentacao> movs = conta.listaMovimentacoes();
		if (movs.isEmpty()) {
			System.out.println("Sem movimentações.");
		} else {
			for (Movimentacao m : movs) {
				System.out.println("Data: " + m.getData() + " | Tipo: " + m.getTipo() + " | Valor: " + m.getValor());
			}
		}
	}
}
