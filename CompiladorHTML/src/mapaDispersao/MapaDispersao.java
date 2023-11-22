package mapaDispersao;

import listaEncadeada.ListaEncadeada;

public class MapaDispersao<T> {
    private ListaEncadeada<NoMapa<T>>[] vetor;

    public MapaDispersao(int tamanho) {
        vetor = new ListaEncadeada[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = new ListaEncadeada<>();
        }
    }

    private int calcularHash(String chave) {
        // Use uma função de hash apropriada para strings
        return chave.hashCode() % vetor.length;
    }

    public void inserir(String chave, T valor) {
        int indice = calcularHash(chave);
        ListaEncadeada<NoMapa<T>> lista = vetor[indice];

        for (NoMapa<T> no : lista) {
            if (no.chave.equals(chave)) {
                no.valor = valor;
                return;
            }
        }

        lista.inserir(new NoMapa<>(chave, valor));
    }

    public void remover(String chave) {
        int indice = calcularHash(chave);
        ListaEncadeada<NoMapa<T>> lista = vetor[indice];

        NoMapa<T> noParaRemover = null;
        for (NoMapa<T> no : lista) {
            if (no.chave.equals(chave)) {
                noParaRemover = no;
                break;
            }
        }

        if (noParaRemover != null) {
            lista.retirar(noParaRemover);
        }
    }

    public T buscar(String chave) {
        int indice = calcularHash(chave);
        ListaEncadeada<NoMapa<T>> lista = vetor[indice];

        for (NoMapa<T> no : lista) {
            if (no.chave.equals(chave)) {
                return no.valor;
            }
        }
        return null;
    }
    
    public Iterable<String> chaves() {
        ListaEncadeada<String> chaves = new ListaEncadeada<>();
        for (ListaEncadeada<NoMapa<T>> lista : vetor) {
            for (NoMapa<T> no : lista) {
                chaves.inserir(no.chave);
            }
        }
        return chaves;
    }
}