package com.mycompany.campominado;

import java.util.Scanner;

public class CampoMinado {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        final int TAMANHO = 5;
        final int TOTAL_BOMBAS = 3;

        // Tabuleiro visível ao jogador
        char[][] tabuleiro = new char[TAMANHO][TAMANHO];

        // Matriz que armazena as bombas
        boolean[][] bombas = new boolean[TAMANHO][TAMANHO];

        // Inicialização das matrizes
        for (int linha = 0; linha < TAMANHO; linha++) {
            for (int coluna = 0; coluna < TAMANHO; coluna++) {
                tabuleiro[linha][coluna] = '-';
                bombas[linha][coluna] = false;
            }
        }

        // Definição das posições das bombas
        bombas[1][2] = true;
        bombas[3][4] = true;
        bombas[4][0] = true;

        int jogadasSeguras = 0;
        int totalCasasSeguras = (TAMANHO * TAMANHO) - TOTAL_BOMBAS;

        boolean jogoAtivo = true;

        System.out.println("====================================");
        System.out.println("         CAMPO MINADO JAVA");
        System.out.println("====================================");
        System.out.println("Encontre as posições seguras.");
        System.out.println("Digite linhas e colunas de 1 a 5.");
        System.out.println("====================================");

        while (jogoAtivo) {

            // Exibe o tabuleiro
            System.out.println("\nTABULEIRO:");

            System.out.println("   1 2 3 4 5");

            for (int linha = 0; linha < TAMANHO; linha++) {

                System.out.print((linha + 1) + "  ");

                for (int coluna = 0; coluna < TAMANHO; coluna++) {
                    System.out.print(tabuleiro[linha][coluna] + " ");
                }

                System.out.println();
            }

            // Entrada do usuário
            System.out.print("\nDigite a linha (1 a 5): ");
            int linha = entrada.nextInt();

            System.out.print("Digite a coluna (1 a 5): ");
            int coluna = entrada.nextInt();

            // Conversão para índice da matriz
            linha--;
            coluna--;

            // Validação
            if (linha < 0 || linha >= TAMANHO
                    || coluna < 0 || coluna >= TAMANHO) {

                System.out.println("\nERRO!");
                System.out.println("Digite valores entre 1 e 5.");
                continue;
            }

            // Verifica se já foi escolhida
            if (tabuleiro[linha][coluna] == 'X') {

                System.out.println("\nEsta posição já foi escolhida!");
                continue;
            }

            // Verifica bomba
            if (bombas[linha][coluna]) {

                System.out.println("\nBOOM!");
                System.out.println("Você encontrou uma bomba!");

                // Revela todas as bombas
                for (int i = 0; i < TAMANHO; i++) {
                    for (int j = 0; j < TAMANHO; j++) {

                        if (bombas[i][j]) {
                            tabuleiro[i][j] = 'B';
                        }
                    }
                }

                // Exibe tabuleiro final
                System.out.println("\nTABULEIRO FINAL:");

                System.out.println("   1 2 3 4 5");

                for (int i = 0; i < TAMANHO; i++) {

                    System.out.print((i + 1) + "  ");

                    for (int j = 0; j < TAMANHO; j++) {
                        System.out.print(tabuleiro[i][j] + " ");
                    }

                    System.out.println();
                }

                System.out.println("\nFIM DE JOGO!");

                jogoAtivo = false;

            } else {

                tabuleiro[linha][coluna] = 'X';

                jogadasSeguras++;

                System.out.println("\nPosição segura!");

                if (jogadasSeguras == totalCasasSeguras) {

                    System.out.println("\nPARABÉNS!");
                    System.out.println("Você encontrou todas as posições seguras!");
                    System.out.println("VOCÊ VENCEU!");

                    jogoAtivo = false;
                }
            }
        }

        entrada.close();
    }
}