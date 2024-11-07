import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
SwingSudoku sw;

    public Controller(SwingSudoku sw) {
        this.sw = sw;
    }

    public ActionListener clearAll(){
        sw.getButtonClearALL().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sw.model.clear();

            }
        });


        return null;
    }

    public ActionListener t(){
        sw.buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] grid = sw.model.getGrid(); // Lấy dữ liệu từ bảng cells
                if (sw.model.solveSudoku(grid,0,0)) {
                    sw.model.updateCells(grid); // Cập nhật lại các ô trong cells
                } else {
                    JOptionPane.showMessageDialog(sw.frame, "No solution exists!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return null;
    }
}
