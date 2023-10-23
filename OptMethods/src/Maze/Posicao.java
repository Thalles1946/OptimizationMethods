package Maze;

public class Posicao {

    String codigo;
    // ["cima","baixo","esquerda","direita"]
    String[] posicoesPossiveis;
    boolean isWall;

    public Posicao(String codigo, String[] posicoesPossiveis, boolean isWall) {
        this.codigo = codigo;
        this.posicoesPossiveis[0] = posicoesPossiveis[0];
        this.posicoesPossiveis[1] = posicoesPossiveis[1];
        this.posicoesPossiveis[2] = posicoesPossiveis[2];
        this.posicoesPossiveis[3] = posicoesPossiveis[3];
        this.isWall = isWall;

    }

}
