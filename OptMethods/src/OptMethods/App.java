package OptMethods;

import java.util.Scanner;

import Maze.Backtracking;
import Maze.LabirintoGuloso;

public class App {
    static Scanner sc = new Scanner(System.in);
    static int opt = sc.nextInt();

    public static void main(String[] args) throws Exception {
        // System.out.println("Olá usuário, qual algoritmo deseja visualizar a
        // execução?");
        // opt = sc.nextInt();
        // algorithms(opt);
        // LabirintoGuloso.execute();

    }

    public static void algorithms(int option) {
        switch (option) {
            case 0:

                Backtracking.execute();
                break;

            case 1:

                break;

            default:
                System.out.println("A opção digitada foi invalida, digite outra e tente novamente : \n");
                opt = sc.nextInt();
                algorithms(opt);
                break;

        }
    }
}