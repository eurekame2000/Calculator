import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class brackBut implements ActionListener {
    char brack;
    public void brackS(char brack){
        this.brack=brack;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        brackSet.brackS(brack);
    }
}
