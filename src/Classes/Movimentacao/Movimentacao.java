package Classes.Movimentacao;

import java.time.LocalDate;

public class Movimentacao {
    private final Float valor;
    private final String tipo;
    private final LocalDate data;

    public Movimentacao(Float valor, String tipo, LocalDate data) {
        this.valor = valor;
        this.tipo = tipo;
        this.data = data;
    }

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
