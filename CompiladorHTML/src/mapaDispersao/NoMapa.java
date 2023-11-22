package mapaDispersao;

public class NoMapa<T> {
    String chave;
    T valor;

    public NoMapa(String chave, T valor) {
        this.chave = chave;
        this.valor = valor;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NoMapa<?> noMapa = (NoMapa<?>) obj;
        return chave == noMapa.chave;
    }
}