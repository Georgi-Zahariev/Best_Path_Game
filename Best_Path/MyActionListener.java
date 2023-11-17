import java.awt.event.*;
import javax.swing.*;

class MyActionListener implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
        JOptionPane.showMessageDialog(null, "I got clicked");
    }
}