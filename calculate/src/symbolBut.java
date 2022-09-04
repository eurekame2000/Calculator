import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class symbolBut implements ActionListener {
    char symbol;
    public void symbolS(char symbol){
        this.symbol=symbol;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        symbolSet.setS(symbol);
    }
}
