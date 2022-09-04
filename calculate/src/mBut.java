import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mBut implements ActionListener {
    char mType;
    public void setmType(char mType){
        this.mType=mType;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        mSet.setM(mType);
    }
}
