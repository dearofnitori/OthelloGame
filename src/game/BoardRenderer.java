package game;

import java.awt.Color;
import java.awt.Graphics;

//　自動調整追加修正　game > BoardRendererに分離
public class BoardRenderer {
    private static final int SIZE = 8; // オセロ盤のサイズ（8x8）

    // 盤面と石の描画処理（画面サイズに応じて自動調整）
    public void drawBoard(Graphics g, int[][] board, int panelWidth, int panelHeight) {
        // パネルサイズに合わせてタイルサイズを計算（正方形を維持）
        int tileSize = Math.min(panelWidth, panelHeight) / SIZE;

        // 盤面を中央に配置するためのずらし（オフセット）
        int offsetX = (panelWidth - tileSize * SIZE) / 2;
        int offsetY = (panelHeight - tileSize * SIZE) / 2;

        // 盤面と石の描画処理
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int x = offsetX + j * tileSize;
                int y = offsetY + i * tileSize;

                // マス目の枠線を描画
                g.setColor(Color.BLACK);
                g.drawRect(x, y, tileSize, tileSize);

                // 石がある場合は描画（黒 or 白）
                if (board[i][j] != 0) {
                    g.setColor(board[i][j] == 1 ? Color.BLACK : Color.WHITE);

                    // 石のサイズと位置を調整（内側の余白を画面サイズの10%で設定）
                    int margin = tileSize / 10;
                    g.fillOval(x + margin, y + margin, tileSize - 2 * margin, tileSize - 2 * margin);
                }
            }
        }
    }
}
