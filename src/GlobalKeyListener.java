import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import javax.swing.*;

/**
 * Created by BMW on 2016-05-27.
 */
public class GlobalKeyListener implements NativeKeyListener {

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        //listening for combination cmd + "+" or cms + "-" to decrease/increase the keyboard size
        //check if cmd (left, right) , + or - have been pressed to increase the side of the keyboard
        switch (nativeKeyEvent.getKeyCode()) {
            // - key
            case 12:
                if (((Settings.keyboardSize -10) >= Settings.absoluteMinFirstRowWidth) && Settings.commandButtonPressed)  {
                    Settings.keyboardSize -=10;
                    //dispose the current keyboard window
                    KeyboardLayout.mainKeyboardFrame.dispose();
                    //draw new keyboard with new size using the same instance
                    KeyboardSticker.keyboard = new KeyboardLayout();
                    //set sign of command key pressed to false
                    Settings.commandButtonPressed = false;
                    //refresh keyboarSize slider position in the settings window if visible
                    Settings.keyboardSizeSlider.setValue(Settings.keyboardSize);
                }
                break;
            //+ key
            case 13:
                if ((Settings.keyboardSize + 10) <= Math.min(Settings.absoluteMaxFirstRowWidth,Settings.maxFirstRowWidthWithCurrentScreen)  && Settings.commandButtonPressed) {
                    Settings.keyboardSize += 10;
                    //dispose the current keyboard window
                    KeyboardLayout.mainKeyboardFrame.dispose();
                    //draw new keyboard with new size using the same instance
                    KeyboardSticker.keyboard = new KeyboardLayout();
                    //set sign of command key pressed to false
                    Settings.commandButtonPressed = false;
                    //refresh keyboarSize slider position in the settings window
                    Settings.keyboardSizeSlider.setValue(Settings.keyboardSize);
                }
                break;
            //command key (left, right)
            case 3675:
            case 3676:
                Settings.commandButtonPressed = true;
                break;
            //any other key
            default:
                Settings.commandButtonPressed = false;
        }

        //continue with determine which button to be lit up
        JButton buttonPressed = (JButton) KeyboardLayout.keys.get(nativeKeyEvent.getKeyCode());
        //if a key code has not been recognised - do nothing
        if (buttonPressed != null) {
            buttonPressed.setEnabled(true);
        }

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        JButton buttonPressed = (JButton) KeyboardLayout.keys.get(nativeKeyEvent.getKeyCode());
        //if a key code has not been recognised - do nothing
        if (buttonPressed != null) {
            buttonPressed.setEnabled(false);
        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }
}
