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
    // 指定したマスに石を置けるかどうかを判定する（合法手チェック）
    public boolean isLegalMove(int x, int y, int playerColor) {
        if (board[x][y] != 0) return false; // すでに石がある

        int opponentColor = (playerColor == BLACK) ? WHITE : BLACK;
        int[][] directions = {
            {-1,-1}, {-1,0}, {-1,1},
            {0,-1},         {0,1},
            {1,-1},  {1,0}, {1,1}
        };

        for (int[] dir : directions) {
            int dx = dir[0], dy = dir[1];
            int cx = x + dx, cy = y + dy;
            boolean hasOpponent = false;

            // 相手の石が連続して並んでいるか確認
            while (isValid(cx, cy) && board[cx][cy] == opponentColor) {
                hasOpponent = true;
                cx += dx;
                cy += dy;
            }

            // 相手の石の後に自分の石があれば合法
            if (hasOpponent && isValid(cx, cy) && board[cx][cy] == playerColor) {
                return true; // この方向で反転できる！
            }
        }

        return false; // どの方向でも反転できない
    }

    // 盤面の範囲内かどうかを判定する補助メソッド
    private boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < board.length && y < board[0].length;
    }

    // 石の反転処理の追加
    // 指定したマスに石を置いたあと、相手の石を反転する
    public void reverseStones(int x, int y, int playerColor) {
        int opponentColor = (playerColor == BLACK) ? WHITE : BLACK;
        int[][] directions = {
            {-1,-1}, {-1,0}, {-1,1},
            {0,-1},         {0,1},
            {1,-1},  {1,0}, {1,1}
        };

        for (int[] dir : directions) {
            int dx = dir[0], dy = dir[1];
            int cx = x + dx, cy = y + dy;
            List<Point> toFlip = new ArrayList<>();

            // 相手の石を一時的に記録
            while (isValid(cx, cy) && board[cx][cy] == opponentColor) {
                toFlip.add(new Point(cx, cy));
                cx += dx;
                cy += dy;
            }

            // 最後に自分の石があれば、記録した石を反転
            if (isValid(cx, cy) && board[cx][cy] == playerColor) {
                for (Point p : toFlip) {
                    board[p.x][p.y] = playerColor;
                }
            }
        }
    }
}
