package Classes.ContaCorrente;

import java.util.ArrayList;

public class ContaCorrente {
	private String agencia;
	private String numero;
	private Float saldo;
	private Float limite;

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Float getSaldo() {
        return saldo;
    }

    public Float getLimite() {
        return limite;
    }

	public Float saque(Float valor){
		if(valor <= this.saldo + this.limite)
			this.saldo -= valor;
		return this.saldo;
	}

	public Float deposito(Float valor){
		this.saldo += valor;
		return this.saldo;
	}

	public ArrayList<Float> listaMovimentacoes() {
		return new ArrayList<>();
	}

	public ArrayList<String> listaCartoes() {
		return new ArrayList<>();
	}

}