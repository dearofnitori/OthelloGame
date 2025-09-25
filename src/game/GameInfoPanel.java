package game;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameInfoPanel extends JPanel {
    private JLabel turnLabel;   // 手番表示用ラベル
    private JLabel countLabel;  // 石の数表示用ラベル

    public GameInfoPanel() {
        setLayout(new GridLayout(2, 1)); // 縦に2段並べるレイアウト
        turnLabel = new JLabel("現在の手番：黒"); // 初期表示
        countLabel = new JLabel("黒：2個　白：2個"); // 初期表示
        add(turnLabel);
        add(countLabel);
    }

    // 手番と石の数を更新するメソッド
    public void updateInfo(int currentPlayer, int[][] board) {
        String turnText = "現在の手番：" + (currentPlayer == 1 ? "黒" : "白");

        // 石の数をカウント
        int black = 0, white = 0;
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 1) black++;
                else if (cell == 2) white++;
            }
        }

        String countText = "黒：" + black + "個　白：" + white + "個";

        // ラベルに反映
        turnLabel.setText(turnText);
        countLabel.setText(countText);
    }
}
