package Classes.ContaCorrente;

import Classes.Cartao.Cartao;
import Classes.Cliente.Cliente;
import Classes.Movimentacao.Movimentacao;
import java.util.ArrayList;
import java.util.List;

public class ContaCorrente {
	private String agencia;
	private String numero;
	private Float saldo;
	private final Float limite;
	private final List<Movimentacao> movimentacoes;
	private final List<Cartao> cartoes;
	private final Cliente cliente;

	public ContaCorrente(String agencia, String numero, Float saldoInicial, Float limite, Cliente cliente) {
		this.agencia = agencia;
		this.numero = numero;
		this.saldo = saldoInicial;
		this.limite = limite;
		this.movimentacoes = new ArrayList<>();
		this.cartoes = new ArrayList<>();
		this.cliente = cliente;
	}

    public String getAgencia() {
        return this.agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Float getSaldo() {
        return this.saldo;
    }

    public Float getLimite() {
        return this.limite;
    }

	public Float saque(Float valor){
		try {
			if (valor != null && valor > 0 && valor <= this.saldo + this.limite) {
				this.saldo -= valor;
				this.movimentacoes.add(new Movimentacao(valor, "SAQUE", java.time.LocalDate.now()));
			}
		} catch (Exception e) {
			System.out.println("Erro ao realizar saque: " + e.getMessage());
		}
		return this.saldo;
	}

	public Float deposito(Float valor){
		try {
			if (valor == null || valor <= 0) throw new Exception("Valor inválido");
			this.saldo += valor;
			this.movimentacoes.add(new Movimentacao(valor, "DEPOSITO", java.time.LocalDate.now()));
		} catch (Exception e) {
			System.out.println("Erro ao realizar depósito: " + e.getMessage());
		}
		return this.saldo;
	}

	public List<Movimentacao> listaMovimentacoes() {
		return this.movimentacoes;
	}

	public List<Cartao> listaCartoes() {
		return this.cartoes;
	}

    public Cliente getCliente() {
        return this.cliente;
    }

}