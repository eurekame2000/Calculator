import java.text.DecimalFormat;

public class mSet {
    public static void setM(char mType){
        if(mType=='+'){
            if(!calculator.inNum.equals("")){
                try{
                    calculator.M=calculator.M+Double.parseDouble(calculator.inNum);
                }catch(Exception e){
                    calculator.tf2.setText("操作数设置错误！");
                }
            }
        }
        else if(mType=='-'){
            if(!calculator.inNum.equals("")){
                try{
                    calculator.M=calculator.M-Double.parseDouble(calculator.inNum);
                }catch(Exception e){
                    calculator.tf2.setText("操作数设置错误！");
                }
            }
        }
        else if(mType=='S'){
            if(!calculator.inNum.equals("")){
                try {
                    calculator.M=Double.parseDouble(calculator.inNum);
                }catch(Exception e){
                    calculator.tf2.setText("操作数设置错误！");
                }
            }
            else {
                calculator.M=0.0;
            }
        }
        else if(mType=='R'){
            DecimalFormat df = new DecimalFormat("#0.0000");
            calculator.tf2.setText(df.format(calculator.M));
        }
        else if(mType=='C'){
            calculator.M=0.0;
        }
        else{
            System.out.println("存储键设置错误！");
        }
    }
}
