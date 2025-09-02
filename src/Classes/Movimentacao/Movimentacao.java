package Classes.Movimentacao;

import java.time.LocalDate;

public class Movimentacao {
	private Float valor;
	private String tipo;
	private LocalDate data;

    public Float getValor() {
        return this.valor;
    }

    public String getTipo() {
        return this.tipo;
    }

    public LocalDate getData() {
        return this.data;
    }
}
