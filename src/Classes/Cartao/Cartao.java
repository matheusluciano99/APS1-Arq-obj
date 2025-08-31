package Classes.Cartao;

import java.time.LocalDate;

public class Cartao {
	private String numeroCartao;
	private String tipo;
	private LocalDate validade;
	private String status;

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public Boolean isExpired() {
		return validade.isBefore(LocalDate.now());
	}

	public Boolean cancelaCartao(){
		setStatus("CANCELADO");
		return true;
	}
}