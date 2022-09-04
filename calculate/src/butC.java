import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//c 清空当前操作数
class butC implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        int r1,r2;
        r1= calculator.inFormula.length();
        r2= calculator.inNum.length();
        char []s1= calculator.inFormula.toCharArray();//将inFormula转化为数组进行操作
        char []s2= calculator.inNum.toCharArray();//将inNum转化为数组进行操作
        while((r1>0)&&(r2>0)&&(s1[r1-1]!='=')) {
            s1[r1-1]=' ';//将要删除的数字变成空格
            s2[r2-1]=' ';
            calculator.inFormula= String.valueOf(s1);//重新将数组变成字符串
            calculator.inNum= String.valueOf(s2);//重新将数组变成字符串
            calculator.inFormula= calculator.inFormula.trim();//删除空格
            calculator.inNum= calculator.inNum.trim();//删除空格
            r1= calculator.inFormula.length();
            r2= calculator.inNum.length();
            s1= calculator.inFormula.toCharArray();//将inFormula转化为数组进行操作
            s2= calculator.inNum.toCharArray();//将inNum转化为数组进行操作
        }
        calculator.tf1.setText(calculator.inFormula);
        calculator.tf2.setText(calculator.inNum);
    }
}

