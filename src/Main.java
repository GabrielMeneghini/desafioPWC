import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
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
        System.out.print("Digite a frase a ser invertida: ");
        String frase = sc.nextLine();
        List<String> stringList = Arrays.stream(frase.split(" ")).collect(Collectors.toList());
        Collections.reverse(stringList);
        System.out.println("Frase invertida:");
        for(String x : stringList) {
            System.out.print(x + " ");
        }
    }
}