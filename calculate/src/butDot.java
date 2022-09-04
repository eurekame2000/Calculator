import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//小数点
class butDot implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        if(!calculator.calculated){
            calculator.inFormula=calculator.inFormula+'.';
            calculator.inNum=calculator.inNum+'.';
            calculator.tf1.setText(calculator.inFormula);
            calculator.tf2.setText(calculator.inNum);
        }
        else{
            calculator.inFormula=".";
            calculator.inNum=".";
            calculator.tf1.setText(calculator.inFormula);
            calculator.tf2.setText(calculator.inNum);
            calculator.calculated=false;
        }
    }
}