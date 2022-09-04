import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//退格键，对公式进行删除操作，点一次删除一个字符
class butBack implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        int r1,r2;
        r1=calculator.inFormula.length();//公式长度
        r2=calculator.inNum.length();//当前操作数长度
        char []s1=calculator.inFormula.toCharArray();//公式先转换为字符数组
        char []s2= calculator.inNum.toCharArray();//将inNum转化为数组进行操作
        if((r1>0)&&(r2>0)&&(s1[r1-1]!='=')) {//当前存在操作数的情况
            s1[r1-1]=' ';//将要删除的数字变成空格
            s2[r2-1]=' ';
            calculator.inFormula= String.valueOf(s1);//重新将数组变成字符串
            calculator.inNum= String.valueOf(s2);//重新将数组变成字符串
            calculator.inFormula= calculator.inFormula.trim();//删除空格
            calculator.inNum= calculator.inNum.trim();//删除空格
        }
        else if(r1>0) {//公式尚存在的情况
            if((s1[r1-1]=='+')||(s1[r1-1]=='-')||(s1[r1-1]=='*')||(s1[r1-1]=='/')||(s1[r1-1]=='(')||(s1[r1-1]==')')||(s1[r1-1]=='.')){
                s1[r1-1]=' ';
                calculator.inFormula=String.valueOf(s1);
                calculator.inFormula=calculator.inFormula.trim();//删除当前符号
                int ifl=calculator.inFormula.length();
                s2=calculator.inFormula.toCharArray();//删除符号之后的字符串
                while((ifl>0)&&(operate.isNumber(String.valueOf(s2[ifl-1])))) {//计算公式当前的操作数
                    calculator.inNum = s2[ifl - 1] + calculator.inNum;
                    ifl--;
                }
            }
            else if(s1[r1-1]=='='){//计算后的式子要删除就直接重设
                calculator.inFormula="";
                calculator.inNum="";
            }
        }
        calculator.tf1.setText(calculator.inFormula);
        calculator.tf2.setText(calculator.inNum);
    }
}
