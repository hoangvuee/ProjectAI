public class UseFuntion {
    public FunctionSudoku functionSudoku;

    public FunctionSudoku getFunctionSudoku() {
        return functionSudoku;
    }

    public void setFunctionSudoku(FunctionSudoku functionSudoku) {
        this.functionSudoku = functionSudoku;
    }

    public UseFuntion(FunctionSudoku functionSudoku) {
        this.functionSudoku = functionSudoku;
    }

    public void function(){
     functionSudoku.mode();
 }

}
