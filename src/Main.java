import java.text.Normalizer;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("*** Desafio PWC ***");
        menu();
        sc.close();
    }

    public static void menu() {
        int escolha = -1;
        while(escolha != 0) {
            try {
                System.out.print("""
                \n1- Reverta a ordem das palavras da string, mantendo a ordem das palavras.
                2- Remova todos os caracteres duplicados da string.
                3- Encontre a substring palíndroma mais longa em uma frase/palavra.
                4- Coloque em maiúscula a primeira letra de cada frase em uma string.
                5- Verifique se a string é um anagrama de um palíndromo.
                0- Sair.
                
                Digite um número para escolher um dos tipos de manipulação acima:""");

                escolha = sc.nextInt(); sc.nextLine();
                switch(escolha) {
                    case 1:
                        System.out.print("Digite a string a ser invertida: ");
                        String s1 = sc.nextLine();
                        System.out.println("Frase invertida: " + reverterOrdemPalavras(s1));
                        break;
                    case 2:
                        System.out.print("Digite a string a ser formatada: ");
                        String s2 = sc.nextLine();
                        System.out.println("String sem caracteres duplicados: " + removerCaracteresDuplicados(s2));
                        break;
                    case 3:
                        System.out.print("Digite a string: ");
                        String s3 = sc.nextLine();
                        System.out.println("Substring palíndroma mais longa encontrada: " + maiorSubStringPalindroma(s3));
                        break;
                    case 4:
                        System.out.print("Digite uma frase: ");
                        String s4 = sc.nextLine();
                        System.out.println("String formatada: " + primeiraLetraMaiuscula(s4));
                        break;
                    case 5:
                        System.out.print("Digite uma frase: ");
                        String s5 = sc.nextLine();
                        if(ehAnagramaDePalindromo(s5)) {
                            System.out.println("\"" + s5 + "\" é um anagrama de um palíndromo.");
                        } else {
                            System.out.println("\"" + s5 + "\" não é um anagrama de um palíndromo.");
                        }
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Número inválido.");
                        break;
                }
            } catch(InputMismatchException e) {
                sc.nextLine();
                System.out.println("Caractere inválido.");
            }
        }
    }

    public static String reverterOrdemPalavras(String s) {
        List<String> stringList = stream(s.split(" ")).collect(Collectors.toList());
        Collections.reverse(stringList);
        String stringInvertida = "";
        for(String x : stringList) {
            stringInvertida += x + " ";
        }
        return stringInvertida.trim();
    }

    public static String removerCaracteresDuplicados(String s) {
        String[] strings = s.split("");
        Set<String> stringSet = new LinkedHashSet<>(Arrays.asList(strings));
        return stringSet.stream().reduce("", (s1, s2) -> s1 + s2);
    }

    public static String maiorSubStringPalindroma(String s) {
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
            return subPalindromos.get(0);
        } else {
            return "Nenhuma substring palindroma encontrada para a string.";
        }
    }

    public static String primeiraLetraMaiuscula(String s) {
        List<String> frases = stream(s.split("(?<=[.!?])\\s*")).toList();

        return frases.stream().map(x -> x.substring(0, 1).toUpperCase() + x.substring(1)).reduce("", (x1, x2)  -> x1 + " " + x2).trim();
    }

    public static boolean ehAnagramaDePalindromo(String s) {
        s = normalizeString(s);

        // Adicionando chaves e valores no Map
        Map<Character, Integer> map = new HashMap<>();
        for(char c: s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0)+1);
        }

        // Contando chaves com valores impares
        int totChavesImpares = 0;
        for(Integer mapValue: map.values()) {
            if(mapValue%2 != 0) {
                totChavesImpares++;
            }
        }

        return (totChavesImpares <= 1);
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