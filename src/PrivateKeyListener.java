import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by BMW on 2016-05-20.
 */
//**CURRENTLY UNUSED !!!!!
//class might be used when there is a need to use native java key listener only with the app focus
class PrivateKeyListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        JButton buttonPressed = (JButton) KeyboardLayout.keys.get(e.getKeyCode());
        //if a key code has not been recognised - do nothing
        if (buttonPressed != null) {
            buttonPressed.setEnabled(true);
        }
     }

    @Override
    public void keyReleased(KeyEvent e) {
        JButton buttonPressed = (JButton) KeyboardLayout.keys.get(e.getKeyCode());
        //if a key code has not been recognised - do nothing
        if (buttonPressed != null) {
            buttonPressed.setEnabled(false);
        }
    }
}
