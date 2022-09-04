import java.awt.Color;
import java.awt.Font;//提供与字体相关的类和接口
import java.text.DecimalFormat;//用于设置浮点数格式
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JButton;//按键
import javax.swing.JFrame;//显示窗口
import javax.swing.JTextField;//文本框
import javax.swing.WindowConstants;//用于控制窗口关闭操作


public class calculator extends JFrame{//主类calculator继承父类JFrame
    final String[] butStr= {
            "(",")","c","Backspace",
            "7","8","9","－",
            "4","5","6","+",
            "1","2","3","*",
            ".","0","=","÷",
            "M+","M-","MR","MS","MC"};
    final JButton []But=new JButton[butStr.length];//按键数组0-25
    public static JTextField tf1=new JTextField();//公式框
    public static JTextField tf2=new JTextField();//当前输入数字框
    public static String inFormula=""; //存储所有点击按键的结果
    public static String inNum="";//存储当前输入的数字
    public static Double M=0.0;//计算器中保存的数据
    public static Boolean calculated=false;//标志当前是否刚经过计算
    //数字栈：用于存储表达式中的各个数字
    public static Stack<Double> numberStack = null;
    // 符号栈：用于存储运算符和括号
    public static Stack<Character> symbolStack = null;
    public void frame() {
        this.setTitle("计算器");//标题
        this.setVisible(true);//表示可显示
        this.setLayout(null);//清空默认布局
        this.setBounds(10, 10,600,795);//设置弹窗的大小，位置
        for(int i=0;i<butStr.length;i++) {
            But[i]=new JButton(butStr[i]);
        }
        //建立按键
        int begin=0;
        int bend=200;
        for(int i=0;i<butStr.length;){//设置按键位置和界面显示效果
            int j=0;
            while(j<4){//四个按钮为一行
                But[i].setBounds(begin, bend, 147,80);//按键的大小
                //设置字体为宋体，加粗，45号字
                But[i].setFont(new java.awt.Font("宋体", Font.BOLD, 45));//加粗45号字
                this.add(But[i]);//加入窗口
                i++;
                j++;
                begin+=147;
                if(i==butStr.length){
                    break;
                }
            }
            if(i==butStr.length){
                break;
            }
            begin=0;
            bend+=80;
        }
        for(int i=4;i<15;i++){//将数字按键的颜色单独设为白色
            if(i==7||i==11)//跳过符号键
            {
                i++;
            }
            But[i].setBackground(Color.white);
        }
        //设置backspace字体使其显示完全
        But[3].setFont(new Font("Times New Roman", Font.BOLD, 25));
        But[17].setBackground(Color.white);//数字0也设置成白色
        //上下两个文本框的属性设置
        tf1.setFont(new Font("宋体", Font.BOLD, 60));
        this.add(tf1);
        tf1.setBounds(0,0,600,100);
        tf1.setEnabled(true); ///文本框设置可点击
        tf1.setEditable(false); ///文本框设置不可编辑，以免干扰输入
        tf1.setText("公式");//显示提示内容
        tf1.addKeyListener(new key());//监听键盘输入
        tf2.setFont(new Font("宋体", Font.BOLD, 60));
        this.add(tf2);
        tf2.setBounds(0,100,600,100);
        tf2.setEnabled(false); //文本框设置为不可点击
        tf2.setText("当前输入数字");//显示提示内容

        numBut nb0=new numBut();//0
        numBut nb1=new numBut();//1
        numBut nb2=new numBut();//2
        numBut nb3=new numBut();//3
        numBut nb4=new numBut();//4
        numBut nb5=new numBut();//5
        numBut nb6=new numBut();//6
        numBut nb7=new numBut();//7
        numBut nb8=new numBut();//8
        numBut nb9=new numBut();//9
        nb0.setN('0');
        nb1.setN('1');
        nb2.setN('2');
        nb3.setN('3');
        nb4.setN('4');
        nb5.setN('5');
        nb6.setN('6');
        nb7.setN('7');
        nb8.setN('8');
        nb9.setN('9');
        symbolBut butAdd=new symbolBut();//+
        symbolBut butSub=new symbolBut();//-
        symbolBut butMul=new symbolBut();//*
        symbolBut butDiv=new symbolBut();///
        butAdd.symbolS('+');
        butSub.symbolS('-');
        butMul.symbolS('*');
        butDiv.symbolS('/');
        brackBut brackl=new brackBut();//(
        brackBut brackr=new brackBut();//)
        brackl.brackS('(');
        brackr.brackS(')');
        mBut mplus=new mBut();//m+
        mBut mminus=new mBut();//m-
        mBut ms=new mBut();
        mBut mc=new mBut();
        mBut mr=new mBut();
        mplus.setmType('+');
        mminus.setmType('-');
        mr.setmType('R');
        ms.setmType('S');
        mc.setmType('C');

        ///建立监视器，表示当你按下这个按键将会执行什么
        But[0].addActionListener(brackl);//(
        But[1].addActionListener(brackr);//)
        But[2].addActionListener(new butC());//c
        But[3].addActionListener(new butBack());//backspace
        But[4].addActionListener(nb7);//7
        But[5].addActionListener(nb8);//8
        But[6].addActionListener(nb9);//9
        But[7].addActionListener(butSub);//-
        But[8].addActionListener(nb4);//4
        But[9].addActionListener(nb5);//5
        But[10].addActionListener(nb6);//6
        But[11].addActionListener(butAdd);//+
        But[12].addActionListener(nb1);//1
        But[13].addActionListener(nb2);//2
        But[14].addActionListener(nb3);//3
        But[15].addActionListener(butMul);//*
        But[16].addActionListener(new butDot());//小数点
        But[17].addActionListener(nb0);//0
        But[18].addActionListener(new butEqual());//=
        But[19].addActionListener(butDiv);///
        But[20].addActionListener(mplus);
        But[21].addActionListener(mminus);
        But[22].addActionListener(mr);
        But[23].addActionListener(ms);
        But[24].addActionListener(mc);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭方式，直接关闭应用程序
    }
    public static void main(String[] args) {
        calculator s1=new calculator();//创建计算器对象
        s1.frame();//显示计算器界面
        Scanner sc=new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#0.0000");
        double num;
        //消除循环的报警
        //noinspection InfiniteLoopStatement
        while(true){//循环接受用户的计算请求
            calculator.inFormula= sc.nextLine();
            calculator.inFormula=calculator.inFormula+'=';
            if(operate.isStandard(calculator.inFormula)){
                num= operate.op(calculator.inFormula);
                System.out.println(df.format(num));
            }
            else{
                System.out.println("算式出错！请重新输入！");
            }
            calculator.inFormula="";
            calculator.inNum="";
        }
    }
}
