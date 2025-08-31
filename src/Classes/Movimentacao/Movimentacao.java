package Classes.Movimentacao;

import java.time.LocalDate;

public class Movimentacao {
	private Float valor;
	private String tipo;
	private LocalDate data;

    public Float getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDate getData() {
        return data;
    }
}
