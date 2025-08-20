package game;
//OthelloGUI
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel implements MouseListener {
    private static final int SIZE = 8;
    private int[][] board = new int[SIZE][SIZE]; // 0=空, 1=黒, 2=白
    private int currentPlayer = 1; // 1=黒, 2=白
    
    public Game() {
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.GREEN);
        addMouseListener(this);
        initBoard();
    }
    private void initBoard() {
        board[3][3] = 2; // 白
        board[3][4] = 1; // 黒
        board[4][3] = 1; // 黒
        board[4][4] = 2; // 白
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int tileSize = getWidth() / SIZE;

        // 盤面描画
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                g.setColor(Color.BLACK);
                g.drawRect(j * tileSize, i * tileSize, tileSize, tileSize);

                // 石描画
                if (board[i][j] != 0) {
                    g.setColor(board[i][j] == 1 ? Color.BLACK : Color.WHITE);
                    g.fillOval(j * tileSize + 5, i * tileSize + 5, tileSize - 10, tileSize - 10);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int tileSize = getWidth() / SIZE;
        int col = e.getX() / tileSize;
        int row = e.getY() / tileSize;
        
//追加した箇所
        if (board[row][col] == 0) {
//修正箇所、GUI側で合法手のチェックを導入
        	 ReverseStone3 reverser = new ReverseStone3(board, currentPlayer);
// 追加した箇所、
            if (reverser.isLegalMove(row, col, currentPlayer)) {
                board[row][col] = currentPlayer;
            reverser.reverseStones(row, col, currentPlayer);
            repaint();
            currentPlayer = (currentPlayer == 1) ? 2 : 1; // 手番交代
            }
        }
    }

    // 必須の空メソッド
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("オセロ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Game());
        frame.pack();
        frame.setVisible(true);
    }
}