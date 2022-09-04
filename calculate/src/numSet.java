public class numSet {
    public static void setNum(String num){
        if(calculator.calculated) {//刚刚结束一次计算以后
            calculator.inNum=num;
            calculator.inFormula=num;
            calculator.calculated=false;
        }
        else{
            calculator.inNum+=num;
            calculator.inFormula+=num;
        }
        calculator.tf1.setText(calculator.inFormula);
        calculator.tf2.setText(calculator.inNum);
    }
}
