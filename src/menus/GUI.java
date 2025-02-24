package src.menus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import src.Algorithm;
import src.Grid;
import src.Piece;
import src.ReadFile;

public class GUI {
    private JFrame frame;
    private FileUploadPanel subLeftJPanel3;
    private JButton solveButton;
    private GridMatrix matrix;

    public GUI() {
        frame = new JFrame("IQ puzzler solver");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(new Color(224, 224, 224));
        leftPanel.setPreferredSize(new Dimension(250, 600));

        JLabel descriptionLabel = new JLabel("");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        leftPanel.add(descriptionLabel,BorderLayout.CENTER);

        solveButton = new JButton("Solve");
        solveButton.setVisible(false);
        

        leftPanel.add(solveButton, BorderLayout.NORTH);

        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.WHITE);
        JLabel noSolution = new JLabel("No found solution");
       

       

        subLeftJPanel3 = new FileUploadPanel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String filePath = subLeftJPanel3.getFilePath();
        
                if (filePath != null && !filePath.isEmpty()) {
                    
                    ArrayList<Piece> allPieces = new ArrayList<>();
                    Grid grid = ReadFile.readCase(filePath, allPieces);
                    System.out.println(filePath);
                    ArrayList<char[][]> solutions = new ArrayList<>();
                    solveButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            long startTime = System.currentTimeMillis();

                            Algorithm.counter = 0;
                            Algorithm.allSolution(solutions, grid, allPieces, 0, allPieces.size()-1);
                            long endTime = System.currentTimeMillis();
                            long elapsedTime = endTime - startTime;
                            if (solutions.isEmpty()){
                                rightPanel.add(noSolution);
                                frame.revalidate();
                                frame.repaint();
                            }else{
                                if(matrix != null){
                                    rightPanel.remove(matrix);
                                }
                                rightPanel.remove(noSolution);
                                char[][] solution = solutions.get(0);
                                System.out.println("Solusi ditemukan !");
                                
                                matrix = new GridMatrix(solutions.get(0));
                                rightPanel.add(matrix);
                                
                            }
                            descriptionLabel.setText(
                                "<html>" +
                                "<b>Deskripsi:</b><br>" +
                                "Ukuran papan: " + grid.n + "x" + grid.m + "<br>" +
                                "Banyak piece: " + allPieces.size()+ "<br>" +
                                "Waktu ekskusi: " + elapsedTime +"ms <br>" +
                                "Jumlah Iterasi: " + Algorithm.counter + "<br>"+
                                "</html>"
                                );
                            frame.revalidate();
                            frame.repaint();
                        }
                    });
                    if (matrix != null){
                        rightPanel.remove(matrix);
                    }

                    solveButton.setVisible(true);
                    descriptionLabel.setText(
                        "<html>" +
                            "<b>Deskripsi:</b><br>" +
                            "Ukuran papan: " + grid.n + "x" + grid.m + "<br>" +
                            "Banyak piece: " + allPieces.size() +
                        "</html>"
                    );                    
                } else {
                    System.out.println("No file selected");
                }
        
                frame.revalidate();
                frame.repaint();
            }
        });

        leftPanel.add(subLeftJPanel3, BorderLayout.SOUTH);


        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(250);
        splitPane.setDividerSize(1);
        splitPane.setResizeWeight(0.3);
        splitPane.setEnabled(false);

        frame.add(splitPane);
        frame.setVisible(true);
    }
}