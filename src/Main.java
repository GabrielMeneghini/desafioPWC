import java.text.Normalizer;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        int escolha = -1;
        while(escolha != 0) {
            System.out.println("""
                \n*** Desafio PWC ***
                
                Digite um número para escolher um dos tipos de manipulação abaixo:
                1- Reverta a ordem das palavras da string, mantendo a ordem das palavras.
                2- Remova todos os caracteres duplicados da string.
                3- Encontre a substring palíndroma mais longa em uma frase.
                4- Coloque em maiúscula a primeira letra de cada frase em uma string.
                5- Verifique se a string é um anagrama de um palíndromo.
                
                0- Sair.""");
            escolha = sc.nextInt(); sc.nextLine();
            switch(escolha) {
                case 1:
                    reverterOrdemPalavras();
                    break;
                case 2:
                    removerCaracteresDuplicados();
                    break;
                case 3:
                    maiorSubStringPalindroma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Número inválido.");
                    break;
            }
        }
    }

    public static void reverterOrdemPalavras() {
        System.out.print("Digite a string a ser invertida: ");
        String frase = sc.nextLine();
        List<String> stringList = Arrays.stream(frase.split(" ")).collect(Collectors.toList());
        Collections.reverse(stringList);
        System.out.println("Frase invertida:");
        for(String x : stringList) {
            System.out.print(x + " ");
        }
    }

    public static void removerCaracteresDuplicados() {
        System.out.print("Digite a string a ser formatada: ");
        String frase = sc.nextLine();
        String[] strings = frase.split("");
        Set<String> stringSet = new LinkedHashSet<>(Arrays.asList(strings));
        stringSet.forEach(System.out::print);
    }

    public static void maiorSubStringPalindroma() {
        System.out.print("Digite a string: ");
        String s = sc.nextLine();
        List<String> subPalindromos = new ArrayList<>();
        int n = s.length();

        for(int i=0; i<n; i++) {
            for(int j = i+1; j <= n; j++) {
                String substring = s.substring(i, j);
                if(ehPalindromo(substring) && !substring.equals(s)) {
                    subPalindromos.add(substring);
                }
            }
        }
        subPalindromos.sort((s1, s2) -> Integer.compare(s2.length(), s1.length()));
        if(!subPalindromos.isEmpty()) {
            System.out.println(subPalindromos.get(0));
        } else {
            System.out.println("Nenhuma substring palindroma encontrada para a string.");
        }
    }

    public static boolean ehPalindromo(String palavra) {
        String palavraNormalizada = normalizeString(palavra);
        int esquerda = 0;
        int direita = palavraNormalizada.length()-1;
        while(esquerda <= direita) {
            if(palavraNormalizada.charAt(esquerda)!=palavraNormalizada.charAt(direita) || palavraNormalizada.length()<3) {
                return false;
            }
            esquerda++;
            direita--;
        }
        return true;
    }

    public static String normalizeString(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        normalized = normalized.replaceAll("[^a-zA-Z]", "").toLowerCase();
        return normalized;
    }

}