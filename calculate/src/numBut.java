import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class numBut implements ActionListener {
    char num;
    public void setN(char num){
        this.num=num;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        numSet.setNum(num+"");
    }
}
