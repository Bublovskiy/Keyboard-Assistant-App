import com.apple.eawt.*;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import java.awt.*;

/**
 * Created by Bublovskiy Maxim on 2016-05-19.
 */

public class KeyboardSticker {

    static KeyboardLayout keyboard;

    //main function of the App
    public static void main(String[] args) {

        //run settings first to prepare the initial data
        final Settings settings = new Settings();

        //****************ARRANGE FOR GLOBAL LISTENING*******************
        //initialize global listener
        GlobalKeyListener globalKeyListener = new GlobalKeyListener();
        //register this global listener
        //if something went wrong put a message
        try
        {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e)
        {
            String advice = "Please before first use of Multilingual Keyboard Sticker\ngrant the access to this application in Security and Privacy,\nlocated in System Preferences.\n\nOtherwise the keyboard will be unresponsive. Thank you!";
            settings.message(advice);
        }

        //add global listener
        GlobalScreen.addNativeKeyListener(globalKeyListener);
        //****************ARRANGE FOR GLOBAL LISTENING*******************


        //create an instance of the KeyboardLayout to draw the keyboard
        keyboard = new KeyboardLayout();

        //set up action on Preferences in the main menu
        Application.getApplication().setPreferencesHandler(new PreferencesHandler() {
            @Override
            public void handlePreferences(AppEvent.PreferencesEvent preferencesEvent) {
                //open settings window only if no other settings were open before
                //if the settings window was previously open - bring it ontop all windows
                if (Settings.settingsFrame != null) {
                    if (!Settings.settingsFrame.isVisible()) {
                        Settings.settingsFrame.setVisible(true);
                    } else {
                        Settings.settingsFrame.toFront();
                    }
                }
                else {settings.settingWindow();}
            }
        });

        //set up About message
        Application.getApplication().setAboutHandler(new AboutHandler() {
            @Override
            public void handleAbout(AppEvent.AboutEvent aboutEvent) {
                settings.message("Virtual Multilingual Keyboard Sticker\n\nVersion 1.0\n\nCopyright © 2016 Maxim Bublovskiy\nbublowskiy@gmail.com");
            }
        });
        
        //set icon for the app
        Image appImage = Toolkit.getDefaultToolkit().getImage("appicon.png");
        Application.getApplication().setDockIconImage(appImage);

    }//end of main
}
