package src.menus;

import java.awt.*;
import java.util.List;
import javax.swing.*;

public class GridMatrix extends JPanel {
    private char[][] matrix;
    private final int cellSize = 50;
    private final int largeSize = 100;
    private int maxRow, maxCol;
    public List<Color> colors = List.of(
        Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE,
        Color.MAGENTA, Color.CYAN, new Color(255, 91, 91), new Color(144, 238, 144),
        new Color(255, 255, 153), new Color(173, 216, 230), new Color(255, 105, 180), new Color(102, 205, 170),
        new Color(178, 34, 34), new Color(34, 139, 34), new Color(255, 165, 0), new Color(0, 0, 255),
        new Color(139, 0, 139), new Color(0, 139, 139), new Color(255, 69, 0), new Color(144, 238, 144),
        new Color(255, 215, 0), new Color(70, 130, 180), new Color(186, 85, 211), new Color(72, 209, 204),
        new Color(255, 99, 71), new Color(30, 144, 255)
    );
    public char[] alphabets = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };


    public GridMatrix(char[][] solution) {
        this.matrix = solution;
        this.maxRow = solution.length;
        this.maxCol = solution[0].length;
        
        int rows = solution.length;
        int cols = solution[0].length;
        setLayout(new GridLayout(rows, cols, 2, 2));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JPanel panel = new JPanel();
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                panel.setBackground(findColor(solution[i][j]));

                if (i == maxRow && j == maxCol) {
                    panel.setPreferredSize(new Dimension(largeSize, largeSize));
                    panel.setBackground(Color.ORANGE);
                } else {
                    panel.setPreferredSize(new Dimension(cellSize, cellSize));
                }

                JLabel label = new JLabel(String.valueOf(matrix[i][j]));
                label.setHorizontalAlignment(JLabel.CENTER);
                panel.add(label);
                add(panel);
            }
        }
    }
    public Color findColor(char alphabet){
        for (int i = 0; i < alphabets.length; i++ ){
            if (Character.toUpperCase(alphabet)== alphabets[i]){
                return colors.get(i);
            }
        }
        return Color.BLACK;
    }
}