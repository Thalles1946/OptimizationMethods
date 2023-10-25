package Maze;

public class ForcaBrutaDinamico {
    private static final int ROWS = 6;
    private static final int COLS = 11;

    public static void main(String[] args) {
        char[][] labirinto = {
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '#', '#', '#', '#', '#', '#', '#', '#', '#', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '#', '#', '#', '.' },
                { '#', '#', '#', '#', '#', '#', '.', '#', '#', '#', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.' },
                { '.', '#', '#', '#', '#', '#', '#', '#', '#', '#', '.' }
        };

        int menorCaminho = encontrarMenorCaminho(labirinto);
        System.out.println("Menor caminho: " + menorCaminho);
    }

    private static int encontrarMenorCaminho(char[][] labirinto) {
        int[][] dp = new int[ROWS][COLS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[0][0] = 0;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (labirinto[i][j] == '.') {
                    if (i > 0 && dp[i - 1][j] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                    }
                    if (j > 0 && dp[i][j - 1] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                    }
                }
            }
        }

        return dp[ROWS - 1][COLS - 1];
    }
}
