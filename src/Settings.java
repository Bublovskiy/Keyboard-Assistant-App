/**
 * Created by Bublovskiy Maxim on 2016-05-19.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;
import javax.swing.*;
import java.awt.*;
import java.io.File;


class Settings {

    //main frame for settings window
    static  JFrame settingsFrame;
    //size slider for setting window
    static JSlider keyboardSizeSlider;

    //*********************GET MAXIMUM SCREEN SIZE *************************************
    //getting the current screen size to make sure that we cannot construct the mainKeyboardFrame wider than the
    //physical size of the user's screen.
    static Dimension currentScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static int currentScreenMaxWidth = (int) currentScreenSize.getWidth();
    //14 - amount of keys in the first row
    //calculating the maximum size based on current screen size
    //avoid numbers like 102, 104...round them up to 100, 110, etc.
    static int maxFirstRowWidthWithCurrentScreen = currentScreenMaxWidth/KeyboardLayout.numberOfKeysRow1 - (currentScreenMaxWidth/KeyboardLayout.numberOfKeysRow1)%10;
    //*********************GET MAXIMUM SCREEN SIZE *************************************


    //*********************PREPARE PROPERTY FOR RETRIEVING DATA************************
    //language chooser
    TreeMap<String, Language> languages = new TreeMap();
    //font chooser
    String[] fonts;
    //*********************PREPARE PROPERTY FOR RETRIEVING DATA************************


    //*********************PREPARE INITIAL PARAMETERS *********************************
    //set up the first and the second language
    static String[][] firstLanguage;
    static String[][] secondLanguage;

    //60 - 150 first row key width
    static int keyboardSize;
    static int absoluteMaxFirstRowWidth = 150;
    static int absoluteMinFirstRowWidth = 60;

    //keys setups
    static String fontName;
    //0 - Plain, Bold - 1, Italic - 2
    static int fontType;

    //font size for the keys
    //user cannot change it
    static int fontSize = 17;

    //blue key light
    //0 - none
    //1 - enabled
    static int blueLight;

    //formed automatically First Language - Second Language
    static String windowHeader = "Virtual Multilingual Keyboard Sticker";

    //settings directory and file name
    //very important to save file directory any where (in our case two folder up) but not inside folder Contents (OS X )
    //If saved in Contents folder of the app - the permission to run this computer will be needed to be granted every time
    //when any files of that folder have been changed
    static String settingFileDir = "../../Documents";
    static String settingsFileName = "settings.txt";

    //*********************PREPARE INITIAL PARAMETERS *********************************

    //*********************KEY TO TRACK COMMAND KEY PRESSED *********************************
    //fasle by default
    static boolean commandButtonPressed = false;
    //*********************KEY TO TRACK HOT KEY PRESSED *********************************


    //Constructor for the class
    Settings () {
        //init the list with languages
        initLanguageChooser();
        //init the list of fonts
        initFontChooser();
        //read initial parameters from the setting File
        //if unsuccessful -  create new file with default settings
        checkSettingsFile();
    }

    //create the list with languages
    //needs to be done manually
    void initLanguageChooser() {

/*
        Class classDef = this.getClass().forName("LatinKeysFullSet");
        Language lang = classDef.newInstance();
*/

        languages.put("-------", new EmptyKeys());
        languages.put("English (full set)", new LatinKeysFullSet());
        languages.put("English", new LatinKeys());
        languages.put("System keys only", new SystemKeysOnly());
        languages.put("Russian", new RussianKeys());
        languages.put("Arabic", new ArabicKeys());
        languages.put("Czech QWERTY", new Czech_QWERTYKeys());
        languages.put("Greek", new GreekKeys());
        languages.put("Serbian", new SerbianKeys());
        languages.put("Turkish", new TurkishKeys());
        languages.put("Ukrainian", new UkrainianKeys());
        languages.put("Belarusian", new BelarusianKeys());
        languages.put("Devanagari", new DevanagariKeys());
        languages.put("Gurmukhi QWERTY", new Gurmukhi_QWERTYKeys());
        languages.put("Hebrew QWERTY", new Hebrew_QWERTYKeys());
        languages.put("Hungarian", new HungarianKeys());
        languages.put("Italian", new ItalianKeys());
        languages.put("Macedonian", new MacedonianKeys());
        languages.put("Persian QWERTY ", new Persian_QWERTYKeys());
        languages.put("Polish", new PolishKeys());
        languages.put("Slovac QWERTY", new Slovac_QWERTYKeys());
        languages.put("Slovenian", new SlovenianKeys());
        languages.put("Thai", new ThaiKeys());
        languages.put("Vietnamese", new VietnameseKeys());
        languages.put("System keys only", new SystemKeysOnly());
        languages.put("Koren 2-set", new Korean_2set());
    }

    //create list of all available phonts on a current platform
    void initFontChooser() {
         fonts =  GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    }

    //construct a settings dialog with all main options
    void settingWindow() {

        //*********************INITIALIZE TH SETTING FRAME (WILL BE DONE ONLY ONCE)********************
        settingsFrame = new JFrame("Settings");
        //set property to the setting frame
        settingsFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        settingsFrame.setResizable(false);
        //*********************INITIALIZE TH SETTING FRAME (WILL BE DONE ONLY ONCE)********************

        //prepare layout, constraints and panels
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        JPanel settingsFramePanel = new JPanel(layout);
        
        JLabel languageLabel = new JLabel("Languages");
        final JComboBox firstLanguageBox = new JComboBox();
        final JComboBox secondLanguageBox = new JComboBox();
        //populate languages pickers
        for (String key : languages.keySet()) {
            firstLanguageBox.addItem(key);
            secondLanguageBox.addItem(key);
            //set selected items in the language lists to the current languages
            if (Arrays.deepEquals(languages.get(key).getKeys(),firstLanguage)) { firstLanguageBox.setSelectedItem(key);}
            if (Arrays.deepEquals(languages.get(key).getKeys(),secondLanguage)){ secondLanguageBox.setSelectedItem(key);}
        }

        JLabel keySizeLabel = new JLabel("Keyboard size (⌘+- quick change)");
        keyboardSizeSlider = new JSlider();
        //set value to the current keyboard size value
        keyboardSizeSlider.setValue(keyboardSize);
        //size not more than 150 or if maxFirstRowWidth < 150 then maxFirstRowWidth and not less than 60
        keyboardSizeSlider.setMaximum(Math.min(absoluteMaxFirstRowWidth,maxFirstRowWidthWithCurrentScreen));
        keyboardSizeSlider.setMinimum(absoluteMinFirstRowWidth);

        JLabel fontLabel = new JLabel("Font Settings");

        final JComboBox fontNameBox = new JComboBox();
        //create list with fonts
        for (int i=0;i<fonts.length;i++) {
            fontNameBox.addItem(fonts[i]);
            //set selected items in the language lists to the current font name
            if (fonts[i].equals(fontName)) {fontNameBox.setSelectedItem(fonts[i]);}
        }

        final JComboBox fontStyleBox = new JComboBox();
        //style of the font
        fontStyleBox.addItem("Plain");
        fontStyleBox.addItem("Bold");
        fontStyleBox.addItem("Italic");
        //set selected item to the current font style
        switch (fontType) {
            //1
            case Font.BOLD:
                fontStyleBox.setSelectedItem("Bold");
                break;
            //2
            case Font.ITALIC:
                fontStyleBox.setSelectedItem("Italic");
                break;
            //0
            case Font.PLAIN:
                fontStyleBox.setSelectedItem("Plain");
        }

        //Blue key light
        JLabel blueLightLabel = new JLabel("Blue light");
        final JComboBox blueLightBox = new JComboBox();
        blueLightBox.addItem("None"); //0
        blueLightBox.addItem("Enabled"); //1
        //set selected item to the current blue light value
        blueLightBox.setSelectedItem(blueLight == 1 ? "Enabled" : "None");

        JButton setDefaultButton = new JButton("Default");
        JButton applyButton = new JButton("Save changes");

        //*****************ADD ELEMENTS INTO A SETTING WINDOW*************************
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        //setting left margin to the label
        c.insets = new Insets(0,5,0,0);
        settingsFramePanel.add(languageLabel,c);
        c.gridy = 1;
        c.insets = new Insets(0,0,0,0);
        settingsFramePanel.add(firstLanguageBox,c);
        c.gridy = 2;
        settingsFramePanel.add(secondLanguageBox,c);
        c.gridy = 3;
        //setting left margin to the label
        c.insets = new Insets(0,5,0,0);
        settingsFramePanel.add(keySizeLabel,c);
        c.gridy = 4;
        c.insets = new Insets(0,0,0,0);
        settingsFramePanel.add(keyboardSizeSlider,c);
        c.gridy = 5;
        //setting left margin to the label
        c.insets = new Insets(0,5,0,0);
        settingsFramePanel.add(fontLabel,c);
        c.gridy = 6;
        c.insets = new Insets(0,0,0,0);
        settingsFramePanel.add(fontNameBox,c);
        c.gridy = 7;
        settingsFramePanel.add(fontStyleBox,c);
        c.gridy = 8;
        //setting left margin to the label
        c.insets = new Insets(0,5,0,0);
        settingsFramePanel.add(blueLightLabel,c);
        c.gridy = 9;
        c.insets = new Insets(0,0,0,0);
        settingsFramePanel.add(blueLightBox,c);
        c.gridy = 10;
        c.gridwidth = 1;
        settingsFramePanel.add(setDefaultButton,c);
        c.gridx = 1;
        c.gridy = 10;
        settingsFramePanel.add(applyButton,c);
        //******************************************************

        settingsFrame.add(settingsFramePanel);
        settingsFrame.pack();
        settingsFrame.setVisible(true);

        //assign Apply button listener
        applyButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //set up the languages
                firstLanguage = languages.get(firstLanguageBox.getSelectedItem()).getKeys();
                secondLanguage = languages.get(secondLanguageBox.getSelectedItem()).getKeys();

                //set keyboard size as
                keyboardSize = keyboardSizeSlider.getValue() - keyboardSizeSlider.getValue()%10;

                //set font
                fontName = (String) fontNameBox.getSelectedItem();
                //set font style
                switch ((String)fontStyleBox.getSelectedItem()) {
                    case "Italic":
                        fontType = Font.ITALIC;
                        break;
                    case "Bold":
                        fontType = Font.BOLD;
                        break;
                    default:
                        fontType = Font.PLAIN;
                }
                //set blue light
                blueLight = blueLightBox.getSelectedItem() == "None" ? 0 : 1;

                //dispose the current keyboard window
                KeyboardLayout.mainKeyboardFrame.dispose();
                //draw new keyboard using the same instance
                KeyboardSticker.keyboard = new KeyboardLayout();

                //write changes to the settings file
                try {

                    PrintWriter writeFile = new PrintWriter(settingFileDir+"/"+settingsFileName);
                    //keyboard first language
                    writeFile.println(firstLanguageBox.getSelectedItem());
                    //keyboard second language
                    writeFile.println(secondLanguageBox.getSelectedItem());
                    //keyboard size
                    writeFile.println(keyboardSize);
                    //keyboard font
                    writeFile.println(fontName);
                    //keyboard font style
                    writeFile.println(fontType);
                    //blue light
                    writeFile.println(blueLight);
                    //close the stream to finalize changes
                    writeFile.close();

                }
                catch (Exception ex) {
                    message("Something went wrong:\n" + ex.getMessage() + "\n" + ex.getCause());
                }
            }//end of actionPerformed

        });

        //assign Default button listener
        setDefaultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //set first and second language
                firstLanguage =  languages.get("English").getKeys();
                secondLanguage =  languages.get("-------").getKeys();
                //60 - 150 first row key width
                keyboardSize = 80;
                //keys setups
                fontName = "DIN Alternate";
                //0 - Plain, Bold - 1, Italic - 2
                fontType = Font.PLAIN;
                //blue light
                blueLight = 1;

                //refresh visual elements
                firstLanguageBox.setSelectedItem("English");
                secondLanguageBox.setSelectedItem("-------");
                keyboardSizeSlider.setValue(keyboardSize);
                fontNameBox.setSelectedItem(fontName);
                fontStyleBox.setSelectedItem(Font.PLAIN);
                blueLightBox.setSelectedItem("Enabled");
            }
        });
    } //end of settingWindow

    //read data form settings file
    void checkSettingsFile() {
        //open the directory "Documents"
        File settingFileDirectory = new File(settingFileDir);
        //open the settings file in the directory
        File settingFile = new File(settingFileDir+"/"+settingsFileName);
        //if the file doesn't exist - create it and put the data to be used by default
        if (settingFile.exists() && settingFile.length() != 0)
        {
            //read initial data from file
            try
            {
            Scanner readFile = new Scanner(settingFile);
                //read line by line. 6 lines. The order is important
                firstLanguage = languages.get(readFile.nextLine()).getKeys();
                secondLanguage = languages.get(readFile.nextLine()).getKeys();
                keyboardSize = Integer.parseInt(readFile.nextLine());
                fontName = readFile.nextLine();
                fontType = Integer.parseInt(readFile.nextLine());
                blueLight = Integer.parseInt(readFile.nextLine());
            }
            catch (Exception e)
            {
                //show message to the user
                message("Something went wrong:\n" + e.getMessage() + "\n" + e.getCause());
            }
        }
        else
        {
            //create file and write data
            try {
                //create directory and continue if successful
                if (settingFileDirectory.mkdir()) {

                    //create a settings file inside the settings directory
                    settingFile.createNewFile();
                    settingFile.setWritable(true);

                    //write default parameters for the keyboard properties
                    //and setting the default parameter as we write them
                    //open file stream and write data
                    PrintWriter writeFile = new PrintWriter(settingFileDir+"/"+settingsFileName);
                    //keyboard first language
                    writeFile.println("English");
                    firstLanguage = languages.get("English").getKeys();
                    //keyboard second language
                    writeFile.println("-------");
                    secondLanguage = languages.get("-------").getKeys();
                    //keyboard size
                    writeFile.println("80");
                    keyboardSize = 80;
                    //keyboard font
                    writeFile.println("DIN Alternate");
                    fontName = "DIN Alternate";
                    //keyboard font style
                    writeFile.println("0");
                    fontType = 0;
                    //blue light kye
                    writeFile.println("1");
                    blueLight = 1;
                    //close the stream to finalize changes
                    writeFile.close();
                }
                else {
                    //show message saying that settings directiry has not been created
                    message("");
                }
            }
            catch (Exception e) {
                //show message to the user
                message("Something went wrong:\n" + e.getMessage() + "\n" + e.getCause());
            }
        }
    }//end of checkSettingsFile

    //show system message to the user with a custom icon
    void message(String message) {
         //set the icon for the message
         ImageIcon icon = new ImageIcon("messageIcon.png");
         //show the dialog to the user with only OK button
         JOptionPane.showMessageDialog(null,message,"Information",JOptionPane.OK_OPTION,icon);
    }//end of message

}//end of Settings class