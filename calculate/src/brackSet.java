public class brackSet {
    public static void brackS(char brack){
        if (calculator.calculated){//刚进行完一次计算时（刚按下过等于号）
            calculator.inFormula=String.valueOf(brack);
        }
        else{//未进行过计算时
            calculator.inFormula=calculator.inFormula+brack;
        }
        calculator.inNum="";
        calculator.tf1.setText(calculator.inFormula);
        calculator.tf2.setText(calculator.inNum);
    }
}
