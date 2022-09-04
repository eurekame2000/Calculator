import java.util.Stack;

public class operate{
    public static double op(String inFormula) {
        // 初始化栈
        calculator.numberStack = new Stack<>();
        calculator.symbolStack = new Stack<>();
        // 用于缓存数字
        StringBuffer temp = new StringBuffer();
        int addz=0;//用于标记算式的合法性
        // 从表达式的第一个字符开始处理
        if ( inFormula.isEmpty()) // 表达式为空时
            return 0;
        for (int i = 0; i < inFormula.length(); i++) {
            char ch = inFormula.charAt(i); // 获取一个字符
            while(ch==' '){//跳过空格
                i++;
                ch=inFormula.charAt(i);
            }
            if(ch=='('){
                addz=1;
            }
            else if(ch=='+'||ch=='-'){
                addz++;
            }
            else {
                addz=0;
            }
            if (operate.isNumber(String.valueOf(ch))) { // 若当前字符是数字
                temp.append(ch); // 加入到数字缓存中
            }
            else { // 非数字的情况
                if(addz==2){//左括号后直接是加减运算符时，被认为是加减号前存在0的运算
                    calculator.numberStack.push(Double.parseDouble(0+""));
                }
                String tempStr = temp.toString(); // 将数字缓存转为字符串
                if (!tempStr.isEmpty()) {
                    double num = Double.parseDouble(tempStr); // 将数字字符串转为双精度浮点数
                    calculator.numberStack.push(num); // 将数字压栈
                    temp = new StringBuffer(); // 重置数字缓存
                }
                // 判断运算符的优先级，若当前优先级低于栈顶的优先级，则先把计算前面计算出来
                while (!comparePri(ch) && !calculator.symbolStack.empty()) {
                    double b = calculator.numberStack.pop(); // 出栈，取出数字，后进先出
                    double a;
                    if(!calculator.numberStack.isEmpty()){//针对以符号开头的算式的计算
                        a = calculator.numberStack.pop();
                    }
                    else{
                        a=0;
                    }
                    // 取出运算符进行相应运算，并把结果压栈进行下一次运算
                    switch (calculator.symbolStack.pop()) {
                        case '+':
                            calculator.numberStack.push(a + b);
                            break;
                        case '-':
                            calculator.numberStack.push(a - b);
                            break;
                        case '*':
                            calculator.numberStack.push(a * b);
                            break;
                        case '/':
                            calculator.numberStack.push(a / b);
                            break;
                        default:
                            break;
                    }
                } // while循环结束
                if (ch != '=') {
                    calculator.symbolStack.push(ch); // 符号入栈
                    if (ch == ')') { // 去括号
                        calculator.symbolStack.pop();
                        calculator.symbolStack.pop();
                    }
                }
            }
        } // for循环结束
        if (calculator.numberStack.isEmpty()){//输入为空的情况
            return 0;
        }
        else{
            return calculator.numberStack.pop(); // 返回计算结果
        }
    }

    //检查算术表达式的基本合法性，符合返回true，否则false
    public static boolean isStandard(String inFormula) {
        boolean enabled=false;//允许出现小数点
        boolean enables=false;//允许出现乘除号
        boolean enableas=true;//允许出现加减号
        boolean enablen=true;//允许出现数字
        boolean enablee=true;//允许出现等号
        boolean enablel=true;//允许出现左括号
        boolean enabler=false;//允许出现右括号
        int dotnum=0;//当前操作数中小数点的个数
        Stack<Character> stack = new Stack<>(); // 用来保存括号，检查左右括号是否匹配
        if ( inFormula==null || inFormula.isEmpty()){ //表达式为空时
            return true;
        }
        for (int i = 0; i < inFormula.length(); i++) {
            char n = inFormula.charAt(i);
            if (!(isNumber(n+"") || "(".equals(n + "") || ")".equals(n + "")//出现非法字符时
                    || "+".equals(n + "") || "-".equals(n + "")
                    || "*".equals(n + "") || "/".equals(n + "")
                    || "=".equals(n + "")||" ".equals(n+""))) {
                return false;
            }
            if(".".equals(n+"")){
                if(!enabled){
                    return false;
                }
                dotnum++;
                enabled=false;//后续暂时不允许出现小数点
                enablen=true;//允许出现数字
                enableas=false;//不允许出现加减号
                enables=false;//不允许出现乘除号
                enablee=false;//不允许出现等号
                enablel=false;//不允许出现左括号
                enabler=false;//不允许出现右括号
            }
            if("*".equals(n+"")||"/".equals(n+"")){
                if(!enables){
                    return false;
                }
                dotnum=0;
                enables=false;
                enableas=false;
                enablen=true;
                enablee=false;
                enabled=false;
                enablel=true;
                enabler=false;
            }
            else if(n>='0'&&n<='9'){
                if(!enablen){
                    return false;
                }
                if(dotnum==0){
                    enabled=true;
                }
                enables=true;
                enableas=true;
                enablee=true;
                enablen=true;
                enablel=false;
                enabler=true;
            }
            else if("+".equals(n+"")||"-".equals(n+"")){
                if(!enableas){
                    return false;
                }
                dotnum=0;
                enables=false;
                enableas=false;
                enablee=false;
                enablen=true;
                enabled=false;
                enablel=true;
                enabler=false;
            }
            // 将左括号压栈，用来给后面的右括号进行匹配
            if ("(".equals(n + "")) {
                if(!enablel){
                    return false;
                }
                stack.push(n);
                enableas=true;
                enables=false;
                enablee=false;
                enablen=true;
                enabled=false;
            }
            if (")".equals(n + "")) { // 匹配括号
                if(!enabler){
                    return false;
                }
                enabled=false;
                enablen=false;
                enableas=true;//
                enables=true;
                enablee=true;
                enablel=false;
                if (stack.isEmpty() || !"(".equals((char) stack.pop() + "")) // 括号是否匹配
                    return false;
            }
            if("=".equals(n+"")){
                if(!enablee){
                    return false;
                }
                enableas=false;
                enablee=false;
                enablen=false;
                enabled=false;
                enables=false;
            }
        }
        // 可能会有缺少右括号的情况
        if (!stack.isEmpty()){
            return false;
        }
        // 检查'='号是否不在末尾
        if (!("=".equals(inFormula.charAt(inFormula.length() - 1) + ""))){
            return false;
        }
        return true;
    }

    //判断字符是否是数字
    public static boolean isNumber(String num) {
        for(int i=0;i<num.length();i++) {
            char c=num.charAt(i);
            if ((c <'0' || c > '9')&&(c!='.')) {
                return false;
            }
        }
        return true;
    }

    //比较优先级：如果当前运算符比栈顶元素运算符优先级高则返回true，否则返回false
    public static boolean comparePri(char symbol) {
        if (calculator.symbolStack.empty()) { // 空栈返回ture
            return true;
        }
        // 符号优先级说明（从高到低）:
        // 第1级: (
        // 第2级: * /
        // 第3级: + -
        // 第4级: )
        char top = calculator.symbolStack.peek(); // 查看堆栈顶部的对象,不是出栈
        if (top == '(') {
            return true;
        }
        // 比较优先级
        switch (symbol) {
            case '(': // 优先级最高
                return true;
            case '*': {
                if (top == '+' || top == '-') // 优先级比+和-高
                    return true;
                else
                    return false;
            }
            case '/': {
                if (top == '+' || top == '-') // 优先级比+和-高
                    return true;
                else
                    return false;
            }
            case '+':
                return false;
            case '-':
                return false;
            case ')': // 优先级最低
                return false;
            case '=': // 结束符
                return false;
            default:
                break;
        }
        return true;
    }
}
