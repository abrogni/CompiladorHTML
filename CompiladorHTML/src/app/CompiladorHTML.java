package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mapaDispersao.MapaDispersao;
import pilhaTag.PilhaLista;

public class CompiladorHTML {
    private PilhaLista<String> pilha;
    private MapaDispersao<String> mapa;
    private StringBuilder result;

    public CompiladorHTML() {
        this.pilha = new PilhaLista<>();
        this.mapa = new MapaDispersao<>(10);
        this.result = new StringBuilder();
    }

    private boolean isSingletonTag(String tag) {
        String[] singletonTags = {"meta", "base", "br", "col", "command", "embed", "hr", "img", "input", "link", "param", "source", "!DOCTYPE"};
        for (String stTag : singletonTags) {
            if (tag.equalsIgnoreCase("<" + stTag + ">")) {
                return true;
            }
        }
        return false;
    }

    private void processaTag(String tag) {
        tag = tag.toLowerCase();
        if (!isSingletonTag(tag)) {
            if (tag.startsWith("</")) {
                String startTag = tag.substring(2, tag.length() - 1);
                if (!pilha.estaVazia() && pilha.peek().equalsIgnoreCase(startTag)) {
                    pilha.pop();
                } else {
                    result.append("Erro: Tag final inesperada encontrada: ").append(tag).append("\n");
                }
            } else if (!tag.startsWith("<")) {
                return;
            } else {
                String nomeTag = tag.substring(1, tag.length() - 1);
                String quantidade = mapa.buscar(nomeTag);
                quantidade = (quantidade != null) ? String.valueOf(Integer.parseInt(quantidade) + 1) : "1";

                mapa.inserir(nomeTag, quantidade);
                pilha.push(nomeTag);
            }
        }
    }

    public String validaArquivo(String caminho) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            StringBuilder conteudo = new StringBuilder();
            while ((linha = br.readLine()) != null) {
                conteudo.append(linha);
            }

            Pattern pattern = Pattern.compile("<[^>]+>");
            Matcher matcher = pattern.matcher(conteudo.toString());
            while (matcher.find()) {
                String tag = matcher.group();
                processaTag(tag);
            }
        } catch (IOException e) {
            return "Erro: " + e.getMessage();
        }

        if (pilha.estaVazia()) {
            result.append("Arquivo HTML está bem formatado.\n");
            result.append("Relação de tags e suas quantidades:\n");
            for (String chave : mapa.chaves()) {
                result.append(chave).append(": ").append(mapa.buscar(chave)).append(" vezes\n");
            }
        } else {
            result.append("Erro: Tags de início sem tag final correspondente.\n");
            result.append("Tags não finalizadas: ");
            while (!pilha.estaVazia()) {
                result.append(pilha.pop()).append(" ");
            }
            result.append("\n");
        }

        return result.toString();
    }
}