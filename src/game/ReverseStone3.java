// ReverseStone3.java
package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class ReverseStone3 {
    public static final int BLACK = 1;
    public static final int WHITE = 2;
    private int[][] board;

    public ReverseStone3(int[][] board, int currentPlayer) {
        this.board = board;
    }
// 追加した箇所,置けないマスに置けるバグを防止改善
    public boolean isLegalMove(int x, int y, int playerColor) {
        if (board[x][y] != 0) return false; // すでに石がある

        int opponentColor = (playerColor == BLACK) ? WHITE : BLACK;
        int[][] directions = { {-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1} };

        for (int[] dir : directions) {
            int dx = dir[0], dy = dir[1];
            int cx = x + dx, cy = y + dy;
            boolean hasOpponent = false;

            while (isValid(cx, cy) && board[cx][cy] == opponentColor) {
                hasOpponent = true;
                cx += dx;
                cy += dy;
            }

            if (hasOpponent && isValid(cx, cy) && board[cx][cy] == playerColor) {
                return true; // この方向で反転できる！
            }
        }

        return false; // どの方向でも反転できない
    }
 
    
    private boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < board.length && y < board[0].length;
    }
// 石の反転処理の追加
    public void reverseStones(int x, int y, int playerColor) {
        int opponentColor = (playerColor == BLACK) ? WHITE : BLACK;
        int[][] directions = { {-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1} };

        for (int[] dir : directions) {
            int dx = dir[0], dy = dir[1];
            int cx = x + dx, cy = y + dy;
            List<Point> toFlip = new ArrayList<>();

            while (isValid(cx, cy) && board[cx][cy] == opponentColor) {
                toFlip.add(new Point(cx, cy));
                cx += dx;
                cy += dy;
            }

            if (isValid(cx, cy) && board[cx][cy] == playerColor) {
                for (Point p : toFlip) {
                    board[p.x][p.y] = playerColor;
                }
            }
        }
    }
}