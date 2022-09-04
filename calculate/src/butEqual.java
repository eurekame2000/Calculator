import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

//等号的操作
class butEqual implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        DecimalFormat df = new DecimalFormat("#0.0000");
        int r = calculator.inFormula.length();
        if (r == 0) {//初始状态按=
            calculator.inFormula = "0.0000=";
        }
        else if(calculator.calculated){//刚进行完一次计算后按=
            calculator.inFormula = "0.0000=";
        }
        else{
            calculator.inFormula += "=";
        }
        // 检查表达式是否合法
        if (!operate.isStandard(calculator.inFormula)) {
            calculator.inFormula = "算术表达式有误！";
            calculator.tf1.setText(calculator.inFormula);
            calculator.inFormula = "";
            calculator.inNum = "";
            calculator.tf2.setText(calculator.inNum);
            calculator.calculated = true;
            return;
        }
        calculator.inNum = df.format(operate.op(calculator.inFormula));
        calculator.tf1.setText(calculator.inFormula);
        calculator.tf2.setText(calculator.inNum);
        calculator.calculated = true;
    }
}

