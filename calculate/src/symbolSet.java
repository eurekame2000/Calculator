public class symbolSet {
    public static void setS(char symbol){
        if (calculator.calculated){
            calculator.inFormula="0.0000"+symbol;
            calculator.calculated=false;
        }
        else{
            calculator.inFormula=calculator.inFormula+symbol;
        }
        calculator.inNum="";
        calculator.tf1.setText(calculator.inFormula);
        calculator.tf2.setText(calculator.inNum);
    }
}
