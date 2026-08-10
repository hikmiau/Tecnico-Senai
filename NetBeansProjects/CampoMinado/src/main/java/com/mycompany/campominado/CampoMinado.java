package com.mycompany.campominado;

import java.util.Scanner;
import java.util.Random;

public class CampoMinado {
    private static final int BOARD_SIZE = 8;
    private static final int TOTAL_BOMBS = 10;
    private static final char HIDDEN = '-';
    private static final char REVEALED = 'X';
    private static final char BOMB = 'B';

    private char[][] board;
    private boolean[][] bombs;
    private int totalAttempts;
    private int safeMovesFound;
    private int totalSafeCells;
    private String playerName;
    private int score;

    public static void main(String[] args) {
        CampoMinado game = new CampoMinado();
        game.start();
    }

    private void start() {
        Scanner input = new Scanner(System.in);
        boolean continuePlaying = true;

        displayWelcomeMenu(input);

        while (continuePlaying) {
            initializeGame();
            playGame(input);

            System.out.println("\n====================================");
            System.out.print("Deseja jogar novamente? (s/n): ");
            String response = input.next();
            continuePlaying = response.equalsIgnoreCase("s");

            if (!continuePlaying) {
                System.out.println("\nObrigado por jogar, " + playerName + "!");
                System.out.println("Pontuação final: " + score);
            }
        }

        input.close();
    }

    private void displayWelcomeMenu(Scanner input) {
        System.out.println("====================================");
        System.out.println("         CAMPO MINADO JAVA");
        System.out.println("====================================");
        System.out.println("1. Jogar");
        System.out.println("2. Instruções");
        System.out.println("3. Sair");
        System.out.println("====================================");

        System.out.print("Escolha uma opção: ");
        int choice = input.nextInt();
        input.nextLine();

        while (choice < 1 || choice > 3) {
            System.out.print("Opção inválida. Tente novamente: ");
            choice = input.nextInt();
            input.nextLine();
        }

        if (choice == 2) {
            displayInstructions();
        } else if (choice == 3) {
            System.out.println("Saindo do jogo...");
            System.exit(0);
        }

        System.out.print("Digite seu nome: ");
        playerName = input.nextLine();
        score = 0;
    }

    private void displayInstructions() {
        System.out.println("\n====================================");
        System.out.println("         INSTRUÇÕES DO JOGO");
        System.out.println("====================================");
        System.out.println("Objetivo: Encontrar todas as posições seguras.");
        System.out.println("Total de bombas: " + TOTAL_BOMBS);
        System.out.println("Tamanho do tabuleiro: " + BOARD_SIZE + "x" + BOARD_SIZE);
        System.out.println("\nComo jogar:");
        System.out.println("1. Insira a linha (1 a " + BOARD_SIZE + ")");
        System.out.println("2. Insira a coluna (1 a " + BOARD_SIZE + ")");
        System.out.println("3. Se encontrar uma bomba, o jogo acaba.");
        System.out.println("4. Se encontrar todas as posições seguras, você vence!");
        System.out.println("\nSimbologia:");
        System.out.println("- : Posição não revelada");
        System.out.println("X : Posição segura revelada");
        System.out.println("B : Bomba (revelada ao perder)");
        System.out.println("Número: Bombas adjacentes");
        System.out.println("====================================\n");
    }

    private void initializeGame() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        bombs = new boolean[BOARD_SIZE][BOARD_SIZE];
        totalAttempts = 0;
        safeMovesFound = 0;
        totalSafeCells = (BOARD_SIZE * BOARD_SIZE) - TOTAL_BOMBS;

        initializeBoard();
        placeBombsRandomly();
    }

    private void initializeBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = HIDDEN;
                bombs[row][col] = false;
            }
        }
    }

    private void placeBombsRandomly() {
        Random random = new Random();
        int bombsPlaced = 0;

        while (bombsPlaced < TOTAL_BOMBS) {
            int row = random.nextInt(BOARD_SIZE);
            int col = random.nextInt(BOARD_SIZE);

            if (!bombs[row][col]) {
                bombs[row][col] = true;
                bombsPlaced++;
            }
        }
    }

    private void playGame(Scanner input) {
        boolean gameActive = true;

        System.out.println("\n====================================");
        System.out.println("Bem-vindo, " + playerName + "!");
        System.out.println("Total de bombas: " + TOTAL_BOMBS);
        System.out.println("Casas seguras para encontrar: " + totalSafeCells);
        System.out.println("====================================");

        while (gameActive) {
            displayBoard();
            displayGameStatus();

            int row = getValidRowInput(input);
            if (row == -1) continue;

            int col = getValidColInput(input);
            if (col == -1) continue;

            row--;
            col--;

            if (isCellAlreadyRevealed(row, col)) {
                System.out.println("Esta posição já foi escolhida!");
                continue;
            }

            totalAttempts++;

            if (bombs[row][col]) {
                handleBombHit();
                gameActive = false;
            } else {
                handleSafeMove(row, col);

                if (safeMovesFound == totalSafeCells) {
                    displayVictoryScreen();
                    gameActive = false;
                }
            }
        }
    }

    private int getValidRowInput(Scanner input) {
        System.out.print("Digite a linha (1 a " + BOARD_SIZE + "): ");

        if (!input.hasNextInt()) {
            input.nextLine();
            System.out.println("Entrada inválida! Digite um número.");
            return -1;
        }

        int row = input.nextInt();

        if (row < 1 || row > BOARD_SIZE) {
            System.out.println("Linha fora dos limites! (1 a " + BOARD_SIZE + ")");
            return -1;
        }

        return row;
    }

    private int getValidColInput(Scanner input) {
        System.out.print("Digite a coluna (1 a " + BOARD_SIZE + "): ");

        if (!input.hasNextInt()) {
            input.nextLine();
            System.out.println("Entrada inválida! Digite um número.");
            return -1;
        }

        int col = input.nextInt();

        if (col < 1 || col > BOARD_SIZE) {
            System.out.println("Coluna fora dos limites! (1 a " + BOARD_SIZE + ")");
            return -1;
        }

        return col;
    }

    private boolean isCellAlreadyRevealed(int row, int col) {
        return board[row][col] == REVEALED || Character.isDigit(board[row][col]);
    }

    private void handleSafeMove(int row, int col) {
        int adjacentBombs = countAdjacentBombs(row, col);

        if (adjacentBombs > 0) {
            board[row][col] = Character.forDigit(adjacentBombs, 10);
        } else {
            board[row][col] = REVEALED;
        }

        safeMovesFound++;
        score += 10;

        System.out.println("Posição segura!");
        if (adjacentBombs > 0) {
            System.out.println("Bombas adjacentes: " + adjacentBombs);
        }
    }

    private int countAdjacentBombs(int row, int col) {
        int count = 0;

        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                if (isValidPosition(r, c) && bombs[r][c]) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
    }

    private void handleBombHit() {
        System.out.println("\n====================================");
        System.out.println("💣 BOOM! 💣");
        System.out.println("Você encontrou uma bomba!");
        System.out.println("====================================");

        revealAllBombs();
        displayBoard();
        displayDefeatMessage();
    }

    private void revealAllBombs() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (bombs[row][col]) {
                    board[row][col] = BOMB;
                }
            }
        }
    }

    private void displayDefeatMessage() {
        System.out.println("\n====================================");
        System.out.println("         GAME OVER!");
        System.out.println("====================================");
        System.out.println("Jogador: " + playerName);
        System.out.println("Tentativas: " + totalAttempts);
        System.out.println("Posições seguras encontradas: " + safeMovesFound);
        System.out.println("Pontuação: " + score);
        System.out.println("====================================");
    }

    private void displayVictoryScreen() {
        score += (totalSafeCells - totalAttempts) * 5;

        System.out.println("\n====================================");
        System.out.println("         🎉 PARABÉNS! 🎉");
        System.out.println("====================================");
        System.out.println("Jogador: " + playerName);
        System.out.println("Você encontrou todas as posições seguras!");
        System.out.println("Tentativas: " + totalAttempts);
        System.out.println("Posições seguras encontradas: " + safeMovesFound);
        System.out.println("Bonificação por eficiência: " + ((totalSafeCells - totalAttempts) * 5));
        System.out.println("Pontuação final: " + score);
        System.out.println("====================================");
    }

    private void displayBoard() {
        System.out.println("\nTABULEIRO:");
        System.out.print("   ");

        for (int col = 1; col <= BOARD_SIZE; col++) {
            System.out.print(col + " ");
        }

        System.out.println();

        for (int row = 0; row < BOARD_SIZE; row++) {
            System.out.print((row + 1) + "  ");

            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print(board[row][col] + " ");
            }

            System.out.println();
        }
    }

    private void displayGameStatus() {
        System.out.println("\n--- Status do Jogo ---");
        System.out.println("Tentativas: " + totalAttempts);
        System.out.println("Posições seguras encontradas: " + safeMovesFound + "/" + totalSafeCells);
        System.out.println("Bombas no tabuleiro: " + TOTAL_BOMBS);
        System.out.println("Pontuação: " + score);
        System.out.println("---");
    }
}
