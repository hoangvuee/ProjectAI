import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Model {
    SwingSudoku sw;
    public JTextField[][] cells = new JTextField[9][9];
    public JButton[] cellFunction = new JButton[9];
    UseFuntion use = new UseFuntion(new ClearALL(this));
    static final int N = 9;

    public Model(SwingSudoku sw) {
        this.sw = sw;
    }

    public void createTableSudoku(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j] = new JTextField();
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                cells[i][j].setBorder(new RoundedBorder(10));
                cells[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        sw.setSelectedCell((JTextField) e.getSource());
                    }
                });

                sw.leftPanel.add(cells[i][j]);

                if ((i / 3 + j / 3) % 2 == 0) {
                    cells[i][j].setBackground(new Color(220, 220, 220));
                }
            }
        }
    }

    public JTextField[][] getCells() {
        return cells;
    }

    public void setCells(JTextField[][] cells) {
        this.cells = cells;
    }

    public void createButtonSudoku(){
        for (int i = 1; i <= 9; i++) {
            JButton numberButton = new JButton(String.valueOf(i));
            numberButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            numberButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (sw.selectedCell != null) {
                        sw.selectedCell.setText(((JButton) e.getSource()).getText());
                    }
                }
            });
            sw.secondPanel.add(numberButton);
        }


    }


    public UseFuntion getUse() {
        return use;
    }

    public void setUse(UseFuntion use) {
        this.use = use;
    }

    public void clear(){
        use.setFunctionSudoku(new ClearALL(this));
        use.function();
    }
    public void undo(){
        use.setFunctionSudoku(new Undo(this));
        use.function();
    }
    static void print(int[][] grid) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Checks whether it will be legal to assign num to the given row, col
    static boolean isSafe(int[][] grid, int row, int col, int num) {
        // Check if we find the same num in the same row, return false
        for (int x = 0; x < N; x++) {
            if (grid[row][x] == num) {
                return false;
            }
        }

        // Check if we find the same num in the same column, return false
        for (int x = 0; x < N; x++) {
            if (grid[x][col] == num) {
                return false;
            }
        }


        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }


    static boolean solveSudoku(int[][] grid, int row, int col) {

        if (row == N - 1 && col == N) {
            return true;
        }


        if (col == N) {
            row++;
            col = 0;
        }


        if (grid[row][col] > 0) {
            return solveSudoku(grid, row, col + 1);
        }


        for (int num = 1; num <= N; num++) {
            if (isSafe(grid, row, col, num)) {

                grid[row][col] = num;


                if (solveSudoku(grid, row, col + 1)) {
                    return true;
                }
            }


            grid[row][col] = 0;
        }
        return false;
    }

    public int[][] getGrid() {
        int[][] grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                String text = cells[i][j].getText();
                if (text.isEmpty()) {
                    grid[i][j] = 0;
                } else {
                    try {
                        grid[i][j] = Integer.parseInt(text);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(sw.frame, "Invalid input in cell [" + i + "][" + j + "]", "Error", JOptionPane.ERROR_MESSAGE);
                        return null;
                    }
                }
            }
        }
        return grid;
    }

    public void updateCells(int[][] grid) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cells[i][j].setText(String.valueOf(grid[i][j]));
            }
        }
    }
    public static void main(String[] args) {
        int[][] grid = new  int[N][N];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j <9 ; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

}
