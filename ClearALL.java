public class ClearALL implements FunctionSudoku{
    Model mode;

    public ClearALL(Model mode) {
        this.mode = mode;
    }

    @Override
    public void mode() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                mode.cells[i][j].setText("");
            }
        }
    }
}
