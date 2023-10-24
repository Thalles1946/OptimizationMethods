package Maze;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;

class Node implements Comparable<Node> {
    int x, y, cost;

    public Node(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.cost, other.cost);
    }
}

public class LabirintoGulosoCusto {
    private static final int ROWS = 6;
    private static final int COLS = 11;
    private static final int[][] labirinto = new int[ROWS][COLS];
    private static final int START_X = ROWS - 1;
    private static final int START_Y = COLS - 1;
    private static final int END_X = 0;
    private static final int END_Y = 0;

    public static void main(String[] args) {
        initLabirinto();
        LabirintoGulosoCusto labirintoGuloso = new LabirintoGulosoCusto();
        labirintoGuloso.solveLabirinto();
    }

    private static void initLabirinto() {
        Random random = new Random();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                labirinto[i][j] = random.nextInt(3) + 1; // Custo aleatório de 1 a 3
            }
        }
    }

    public void solveLabirinto() {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        boolean[][] visited = new boolean[ROWS][COLS];
        Stack<Node> path = new Stack<>();

        Node startNode = new Node(START_X, START_Y, labirinto[START_X][START_Y]);
        queue.offer(startNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            int x = currentNode.x;
            int y = currentNode.y;
            int cost = currentNode.cost;

            if (x == END_X && y == END_Y) {
                System.out.println("Caminho encontrado com custo total: " + cost);
                System.out.println("Caminho percorrido:");
                path.push(currentNode);
                while (!path.isEmpty()) {
                    Node node = path.pop();
                    System.out.println("(" + node.x + ", " + node.y + ") - Custo: " + node.cost);
                }
                return;
            }

            visited[x][y] = true;

            // Verifica os vizinhos
            int[] dx = { -1, 1, 0, 0 };
            int[] dy = { 0, 0, -1, 1 };

            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (isValid(newX, newY) && !visited[newX][newY]) {
                    int newCost = cost + labirinto[newX][newY];
                    Node neighbor = new Node(newX, newY, newCost);
                    queue.offer(neighbor);
                    path.push(neighbor);
                }
            }
        }

        System.out.println("Não há caminho possível para o destino.");
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < ROWS && y >= 0 && y < COLS;
    }
}
