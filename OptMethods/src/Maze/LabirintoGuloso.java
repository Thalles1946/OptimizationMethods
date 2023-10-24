package Maze;

import java.util.PriorityQueue;

class Node implements Comparable<Node> {
    int x, y, heuristicCost, totalCost;

    public Node(int x, int y, int heuristicCost, int totalCost) {
        this.x = x;
        this.y = y;
        this.heuristicCost = heuristicCost;
        this.totalCost = totalCost;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.totalCost, other.totalCost);
    }
}

public class LabirintoGuloso {
    private static final int ROWS = 6;
    private static final int COLS = 11;
    private static final int[][] labirinto = {
            { 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0 },
            { 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1 },
            { 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0 },
            { 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0 },
            { 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0 }
    };

    private static final int START_X = ROWS - 1;
    private static final int START_Y = COLS - 1;
    private static final int END_X = 0;
    private static final int END_Y = 0;

    public static void main(String[] args) {
        LabirintoGuloso labirintoGuloso = new LabirintoGuloso();
        labirintoGuloso.solveLabirinto();
    }

    public void solveLabirinto() {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        boolean[][] visited = new boolean[ROWS][COLS];

        Node startNode = new Node(START_X, START_Y, calculateHeuristic(START_X, START_Y), 0);
        queue.offer(startNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            int x = currentNode.x;
            int y = currentNode.y;

            System.out.println(x + " " + y + " | " + END_X + " " + END_Y);

            if (x == END_X && y == END_Y) {
                System.out.println("Caminho encontrado!");
                return;
            }

            visited[x][y] = true;

            // Verifica os vizinhos
            int[] dx = { -1, 1, 0, 0 };
            int[] dy = { 0, 0, -1, 1 };

            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (isValid(newX, newY) && !visited[newX][newY] && labirinto[newX][newY] == 0) {
                    int heuristicCost = calculateHeuristic(newX, newY);
                    Node neighbor = new Node(newX, newY, heuristicCost, heuristicCost);
                    queue.offer(neighbor);
                }
            }
        }

        System.out.println("Não há caminho possível para o destino.");
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < ROWS && y >= 0 && y < COLS;
    }

    private int calculateHeuristic(int x, int y) {
        return Math.abs(x - END_X) + Math.abs(y - END_Y);
    }
}
