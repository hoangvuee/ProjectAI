import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import static java.awt.Color.*;

public class SwingSudoku  {

    public JFrame frame;
    JPanel leftPanel;
    JPanel rightPanel;
    Model model = new Model(this);
    JButton buttonUndo, buttonErase, buttonClearALL,buttonSubmit, buttonClear;
    JPanel secondPanel,thirdPanel,functionButton;
    JTextField selectedCell;
    Controller ct= new Controller(this);


    public JTextField getSelectedCell() {
        return selectedCell;
    }

    public JPanel getSecondPanel() {
        return secondPanel;
    }

    public SwingSudoku() {

        frame = new JFrame();
        frame.setTitle("Sudoku AI");
        frame.setSize(900,620);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        leftPanel = new JPanel();
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.setPreferredSize(new Dimension(500, 500));
        leftPanel.setLayout(new GridLayout(9,9));

        rightPanel = new JPanel();
        rightPanel.setBackground(Color.DARK_GRAY);
        rightPanel.setPreferredSize(new Dimension(400, 500));
        rightPanel.setLayout(new GridBagLayout());
         functionButton = new JPanel();
        functionButton.setLayout(new GridLayout(0,3));
        buttonUndo = new JButton(new ImageIcon("imgs/Visualpharm-Must-Have-Undo.48.png"));
        buttonUndo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        functionButton.add(buttonUndo);
        buttonErase = new JButton(new ImageIcon("imgs/Shaunkleyn-Phlat-Blue-Folders-Trash-Empty.48.png"));
        buttonErase.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        functionButton.add(buttonErase);
        buttonClearALL = new JButton(new ImageIcon("imgs/Icojam-Blue-Bits-Symbol-delete.48.png"));
        buttonClearALL.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonClearALL.addActionListener(ct.clearAll());
        functionButton.add(buttonClearALL);
        secondPanel = new JPanel();
        secondPanel.setLayout(new GridLayout(3, 3, 5, 5));
        secondPanel.setPreferredSize(new Dimension(150, 150)); // Đặ


       thirdPanel = new JPanel();
        thirdPanel.setBackground(new Color(150, 150, 150));
        thirdPanel.setLayout(new GridLayout(0,1));
        buttonSubmit = new JButton("Solve Sudoku");
        buttonSubmit.setBackground(new Color(1,255,255));
        buttonSubmit.setSize(100,100);
        buttonSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        thirdPanel.add(buttonSubmit);
        buttonSubmit.addActionListener(ct.t());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;


        gbc.weighty = 0;
        gbc.ipady = 25;
        rightPanel.add(functionButton, gbc);


        gbc.gridy = 1;
        gbc.ipady = 300;
        rightPanel.add(secondPanel, gbc);


        gbc.gridy = 2;
        gbc.ipady = 25;
        rightPanel.add(thirdPanel, gbc);

        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.CENTER);

        model = new Model(this);
    }

    public JButton getButtonClearALL() {
        return buttonClearALL;
    }

    public void setButtonClearALL(JButton buttonClearALL) {
        this.buttonClearALL = buttonClearALL;
    }

    public JButton getButtonSubmit() {
        return buttonSubmit;
    }

    public void demoSwingAI(){
        model.createTableSudoku();
       model.createButtonSudoku();
        frame.setVisible(true);
    }
    public void setSelectedCell(JTextField selectedCell) {
        this.selectedCell = selectedCell;
    }
    public static void main(String[] args) {
        SwingSudoku demo = new SwingSudoku();
        demo.demoSwingAI();

       // System.out.println(Arrays.deepToString(demo.model.getGrid()));
    }
}
