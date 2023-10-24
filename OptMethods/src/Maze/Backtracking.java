package Maze;

import java.util.Random;

public class Backtracking {
    private static final int ROWS = 6;
    private static final int COLS = 11;
    private static final int EMPTY = 0;
    private static final int WALL = 1;

    private int[][] labirinto;
    private int startX, startY, endX, endY;

    public Backtracking() {
        labirinto = new int[ROWS][COLS];
        initLabirinto();
        generateRandomStartEnd();
    }

    private void initLabirinto() {
        // Inicializa o labirinto com paredes
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                labirinto[i][j] = WALL;
            }
        }

        // Crie caminhos vazios aleatórios no labirinto
        Random random = new Random();
        for (int i = 1; i < ROWS - 1; i += 2) {
            for (int j = 1; j < COLS - 1; j += 2) {
                labirinto[i][j] = EMPTY;
                if (i < ROWS - 2) {
                    labirinto[i + 1][j] = EMPTY;
                }
                if (j < COLS - 2) {
                    labirinto[i][j + 1] = EMPTY;
                }
            }
        }
    }

    private void generateRandomStartEnd() {
        Random random = new Random();
        do {
            startX = random.nextInt(ROWS);
            startY = random.nextInt(COLS);
        } while (labirinto[startX][startY] != EMPTY);

        do {
            endX = random.nextInt(ROWS);
            endY = random.nextInt(COLS);
        } while (labirinto[endX][endY] != EMPTY || (endX == startX && endY == startY));
    }

    public boolean solveLabirinto() {
        return solve(startX, startY);
    }

    private boolean solve(int x, int y) {
        if (x == endX && y == endY) {
            return true; // Chegou ao destino
        }
        if (isSafe(x, y)) {
            labirinto[x][y] = 2; // Marca o caminho como visitado

            // Tenta mover para cima
            if (solve(x - 1, y)) {
                return true;
            }
            // Tenta mover para a direita
            if (solve(x, y + 1)) {
                return true;
            }
            // Tenta mover para baixo
            if (solve(x + 1, y)) {
                return true;
            }
            // Tenta mover para a esquerda
            if (solve(x, y - 1)) {
                return true;
            }

            labirinto[x][y] = 0; // Marca o caminho de volta como não visitado
        }

        return false;
    }

    private boolean isSafe(int x, int y) {
        return x >= 0 && x < ROWS && y >= 0 && y < COLS && labirinto[x][y] == EMPTY;
    }

    public void printLabirinto() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (i == startX && j == startY) {
                    System.out.print("S ");
                } else if (i == endX && j == endY) {
                    System.out.print("E ");
                } else {
                    System.out.print(labirinto[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void execute() {
        Backtracking lab = new Backtracking();
        System.out.println("Labirinto gerado:");
        lab.printLabirinto();
        if (lab.solveLabirinto()) {
            System.out.println("\nLabirinto resolvido:");
            lab.printLabirinto();
        } else {
            System.out.println("Não há solução para o labirinto.");
        }
    }
}
