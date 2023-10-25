package Maze;

public class LabirintoForcaBruta {

    private static final int ROWS = 6;
    private static final int COLS = 11;
    private static final char[][] labirinto = new char[ROWS][COLS];
    private static final int START_X = ROWS - 1;
    private static final int START_Y = COLS - 1;
    private static final int END_X = 0;
    private static final int END_Y = 0;

    public static void main(String[] args) {
        // Inicializa o labirinto (0 representa caminho livre, 1 representa parede)
        // Aqui você pode definir seu labirinto.
        // Por exemplo, '.' pode representar um caminho livre, '#' uma parede.
        char[][] labirinto = {
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '#', '#', '#', '#', '#', '#', '#', '#', '#', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '#', '#', '#', '.' },
                { '#', '#', '#', '#', '#', '#', '.', '#', '#', '#', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.' },
                { '.', '#', '#', '#', '#', '#', '#', '#', '#', '#', '.' }
        };

        boolean[][] visited = new boolean[ROWS][COLS];
        StringBuilder path = new StringBuilder();

        boolean encontrouCaminho = encontrarCaminho(labirinto, visited, START_X, START_Y, path);

        if (encontrouCaminho) {
            System.out.println("Caminho encontrado: " + path.reverse().toString());
        } else {
            System.out.println("Não há caminho possível para o destino.");
        }
    }

    private static boolean encontrarCaminho(char[][] labirinto, boolean[][] visited, int x, int y, StringBuilder path) {
        if (x == END_X && y == END_Y) {
            return true;
        }

        if (x < 0 || x >= ROWS || y < 0 || y >= COLS || visited[x][y] || labirinto[x][y] == '#') {
            return false;
        }

        visited[x][y] = true;

        // Tentar mover para cima
        if (encontrarCaminho(labirinto, visited, x - 1, y, path)) {
            path.append('U');
            return true;
        }
        // Tentar mover para a direita
        if (encontrarCaminho(labirinto, visited, x, y + 1, path)) {
            path.append('R');
            return true;
        }
        // Tentar mover para baixo
        if (encontrarCaminho(labirinto, visited, x + 1, y, path)) {
            path.append('D');
            return true;
        }
        // Tentar mover para a esquerda
        if (encontrarCaminho(labirinto, visited, x, y - 1, path)) {
            path.append('L');
            return true;
        }

        visited[x][y] = false;
        return false;
    }
}
