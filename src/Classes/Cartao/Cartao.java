package Classes.Cartao;

import java.time.LocalDate;

public class Cartao {
	private String numeroCartao;
	private String tipo;
	private LocalDate validade;
	private String status;

    public Cartao(String numeroCartao, String tipo, LocalDate validade) {
        this.numeroCartao = numeroCartao;
        this.tipo = tipo;
        this.validade = validade;
        this.status = "ATIVO";
    }

    public String getNumeroCartao() {
        return this.numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getValidade() {
        return this.validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public Boolean isExpired() {
		return validade.isBefore(LocalDate.now());
	}

	public Boolean cancelaCartao(){
		if ("CANCELADO".equals(this.status))
			return false;
		this.status = "CANCELADO";
		return true;
	}
}