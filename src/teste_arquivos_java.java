import java.nio.file.*;
import java.io.IOException;
import java.util.*;

public class teste_arquivos_java {
    public static void imprimirCartas(List<String> caminhosArquivos) {
        List<String[]> cartas = new ArrayList<>();

        // Ler cada arquivo e armazenar suas linhas
        for (String caminho : caminhosArquivos) {
            try {
                List<String> linhas = Files.readAllLines(Paths.get(caminho));
                cartas.add(linhas.toArray(new String[0]));
            } catch (IOException e) {
                System.err.println("Erro ao ler arquivo: " + caminho);
            }
        }

        // Determinar o número máximo de linhas
        int maxLinhas = cartas.stream().mapToInt(arr -> arr.length).max().orElse(0);

        // Imprimir as cartas lado a lado
        for (int i = 0; i < maxLinhas; i++) {
            StringBuilder linhaMontada = new StringBuilder();
            for (String[] carta : cartas) {
                if (i < carta.length) {
                    linhaMontada.append(carta[i]).append("   "); // Espaço entre cartas
                } else {
                    linhaMontada.append(" ".repeat(carta[0].length())).append("   "); // Espaço vazio se faltar linhas
                }
            }
            System.out.println(linhaMontada);
        }
    }

    public static void main(String[] args) {
        List<String> caminhos = List.of("src/resources/cards/clubs/ace.txt", "src/resources/cards/clubs/eight.txt");
        imprimirCartas(caminhos);
    }
}
