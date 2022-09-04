import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class key implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
        char c=e.getKeyChar();
        if(!calculator.calculated){
            if((c>='0'&&c<='9')||(c=='.')){
                calculator.inNum=calculator.inNum+c;
            }
            else if(c=='+'||c=='-'||c=='*'||c=='/'||c=='('||c==')'){
                calculator.inNum="";
            }
            if(operate.isNumber(c+"")||c=='+'||c=='-'||c=='*'||c=='/'||c=='('||c==')'){
                calculator.inFormula=calculator.inFormula+c;
            }
        }
        else{
            if(operate.isNumber(c+"")||c=='+'||c=='-'||c=='*'||c=='/'||c=='('||c==')'){
                calculator.inFormula=c+"";
                if(operate.isNumber(c+"")){
                    calculator.inNum=c+"";
                }
                else{
                    calculator.inNum="";
                }
            }
            calculator.calculated=false;
        }
        calculator.tf1.setText(calculator.inFormula);
        calculator.tf2.setText(calculator.inNum);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
