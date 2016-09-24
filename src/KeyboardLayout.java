/**
 * Created by Bublovskiy Maxim on 2016-05-19.
 */

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.util.*;

class KeyboardLayout {
    //main frame for the keyboard
    static JFrame mainKeyboardFrame;
    //main panel
    JPanel mainPanelOfKeyboardFrame;
    //sizes of the first row keys
    //construct the entire keyboard dimensions based on first row keys width and height
    static int firstRowKeysWidth, firstRowKeysHeight;
    //proportion first row width to first row height
    //height - 50% of first row keys width
    double heightPercentage = 0.50;
    //height for the rest of the keys. +30 . Calculate in the constructor of the class
    int heightForAll;
    //set up the amount of key rows on a keyboard
    int numberOfKeysRows = 6;
    //set up the amount of keys in every row
    static int numberOfKeysRow1 = 14;
    int numberOfKeysRow2 = 14;
    int numberOfKeysRow3 = 14;
    int numberOfKeysRow4 = 13;
    int numberOfKeysRow5 = 12;
    int numberOfKeysRow6 = 10;
    //getting key codes for the keyboard

    //********************PREPARE LIBRARIES*****************************
    //read the collection with the native java key codes
    //------int[][] keyCodes = KeyCodes.getKeyCodes();
    //read the collection with the native JNativeHook key codes
    int[][] keyCodes = KeyCodesJNativeHook.getKeyCodes();
    //define a collection with the all keys
    static HashMap keys = new HashMap();
    //prepare for the latin language letters
    String[][] firstKeys;
    //prepare for the second language letters
    String[][] secondKeys;
    //**************************************************************

    //define by how much the keys from rows 2....N higher than the first row keys
    //the other keys height = firstRowKeyHeight + otherKeysHeightIndex
    //int otherKeysHeightIndex = 30;
    //delete key is 60% wider than a first row key
    double deleteKeyWidthIndex = 1.6;
    //tab key is 60% wider than a first row key
    double tabKeyWidthIndex = 1.6;
    //tab key is 100% wider than a first row key
    double capslockKeyWidthIndex = 2;
    //tab key is 180% wider than a first row key
    double returnKeyWidthIndex = 1.8;
    //left shift key is 220% wider than a first row key
    double shiftLeftKeyWidthIndex = 2.2;
    //right shift key is 225% wider than a first row key
    double shiftRightKeyWidthIndex = 2.2;
    //space bar is 500% wider than a first row key
    double spaceBarKeyWidthIndex = 5;
    //set up language letters shifts coefficients depending on the size of the keyboard
    static int upperX, upperY, lowerX, lowerY;

    //constructor of the class
    KeyboardLayout() {

        //***************************SET UP KEYBOARD SIZE AND HEADER*************
        firstRowKeysWidth = Settings.keyboardSize;
        //the height of the first row key is X% of the firstRowKeyWidth width;
        firstRowKeysHeight = (int) (firstRowKeysWidth * heightPercentage);
        String KeyboardHeadline = Settings.windowHeader;
        //set up height for the rest of the keys + 30
        heightForAll = firstRowKeysHeight+30;
        //***************************SET UP KEYBOARD SIZE AND HEADER*************

        //***************************SET UP LANGUAGES****************************
        //getting the latin keys (limited set or full set)
        firstKeys = Settings.firstLanguage;
        //getting the second language
        secondKeys = Settings.secondLanguage;
        //***************************SET UP LANGUAGES****************************

        //check keyboard size to calculate appropriate position for the upper and lower keys' letter
        checkSizeKeyboard();
        //set up main frame and panel
        setupMainFrameAndPanel(KeyboardHeadline);
        //drawing the top keyboard row 1 of 6
        setUpFirstRow();
        //drawing the top keyboard row 2 of 6
        setUpSecondRow();
        //drawing the top keyboard row 3 of 6
        setUpThirdRow();
        //drawing the top keyboard row 4 of 6
        setUpForthRow();
        //drawing the top keyboard row 5 of 6
        setUpFifthRow();
        //drawing the top keyboard row 6 of 6
        setUpSixthRow();

        //add the main panel into the main frame
        mainKeyboardFrame.add(mainPanelOfKeyboardFrame);
        //pack the main frame
        mainKeyboardFrame.pack();

        //**************************SET POSITION AT THE BOTTOM CENTRE**********************
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //get the default out port
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        //get default out port size
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        //calculate Y position on the screen (screen width - app frame) / 2
        int x = ((int) rect.getMaxX() - mainKeyboardFrame.getWidth()) /2;
        //calculate Y position on the screen
        int y = (int) rect.getMaxY() - mainKeyboardFrame.getHeight();
        //set location to the main app frame
        mainKeyboardFrame.setLocation(x, y);
        //**************************SET POSITION AT THE BOTTOM CENTRE**********************

        //make main app frame visible visible
        mainKeyboardFrame.setVisible(true);

    }//end of KeyboardLayout constructor

    //defining different distance of key letters from the keys frame inside
    //maximum key width 60 -150
    //adjust upperX and upperY we can move an upper letter:
    // + upperX - will move to the right
    // - upperX - will move to the left
    //adjust lowerX and lowerY we can move a lower letter:
    // + lowerX - will move to the left (it is a coefficient we subtract)
    // - lowerX - will move to the right (it is a coefficient we subtract)

    void checkSizeKeyboard() {
        switch (firstRowKeysWidth) {
            case 60:
            case 70:
                upperX = 10; upperY = 20; lowerX = 25; lowerY = -20;
                break;
            case 80:
            case 90:
            case 100:
            case 110:
                upperX = 13; upperY = 26; lowerX = 30; lowerY = -17;
                break;
            default:
                upperX = 16; upperY = 29; lowerX = 32; lowerY = -14;
                Settings.fontSize = 18;
                break;
        }
    }

    //set up main Frame
    void setupMainFrameAndPanel(String headline) {
        //initialize keyboard frame
        mainKeyboardFrame = new JFrame(headline);
        //Make sure we cannot resize the main frame
        mainKeyboardFrame.setResizable(false);

        //add a key listener to the keyboard frame
        //leave it out due to using JNativeHook keylistener
        //mainKeyboardFrame.addKeyListener(new PrivateKeyListener());

        //based on first row keys width define the width of the mainKeyboardFrame
        //general width cannot excide the physical screen size
        //checking of firstRowKeysWidth has been done in the constructor
        int mainKeyboardFrameWidth = numberOfKeysRow1*firstRowKeysWidth;
        //the first row is always narrower than the rest of the keys (Apple keyboard)
        int mainKeyboardFrameHeight = (int) (firstRowKeysWidth * heightPercentage + (numberOfKeysRows-1)*heightForAll);  //(firstRowKeysHeight+otherKeysHeightIndex)));
        mainKeyboardFrame.setSize(new Dimension(mainKeyboardFrameWidth,mainKeyboardFrameHeight));

        //GridBagLayout works nicer than just GridLayout
        GridBagLayout gridLayout = new GridBagLayout();
        //define the main frame layout as 6 rows and 1 column
        mainPanelOfKeyboardFrame = new JPanel(gridLayout);

        //*******************SAVE DATA ON EN EXIT***********************
        //arrange for closing the app when click main window cross

        mainKeyboardFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //*******************SAVE DATA ON EN EXIT***********************

    }//end of setupMainFrameAndPanel

    //set up 1st top row
    void setUpFirstRow() {
        //create temp panel with FlowLaout
        JPanel temPanel = new JPanel();
        //create temp layout
        FlowLayout tempLayout = new FlowLayout();
        //set horizontal and vertical gap between its components
        tempLayout.setHgap(0);
        tempLayout.setVgap(0);
        tempLayout.setAlignment(FlowLayout.LEFT);

        temPanel.setLayout(tempLayout);

        //drawing the first top row of the keyboard
        //14 - amount of blank buttons in the first top row of keys
        for (int i=0;i<numberOfKeysRow1;++i) {

            //create new temp button
            //no any letters for this row
            CustomJButton tempButton = new CustomJButton();

            //all buttons are not painted on focus by default
            tempButton.setEnabled(false);
            //set size of a button
            //the bigger size - te more rounded buttons corners are, the better layout works
            tempButton.setPreferredSize(new Dimension(firstRowKeysWidth,firstRowKeysHeight));
            //add a button to a temp panel
            temPanel.add(tempButton);

            //put the button into the collection
            keys.put(keyCodes[0][i],tempButton);
        }
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        //add temp panel into the main panel
        mainPanelOfKeyboardFrame.add(temPanel,c);
    }//end of setUpFirstRow

    //set up 2st top row
    void setUpSecondRow() {
        //create temp panel with FlowLaout
        JPanel temPanel = new JPanel();
        //create temp layout
        FlowLayout tempLayout = new FlowLayout();
        //set horizontal and vertical gap between its components
        tempLayout.setHgap(0);
        tempLayout.setVgap(0);
        tempLayout.setAlignment(FlowLayout.LEFT);

        temPanel.setLayout(tempLayout);

        //calculate the sizes for keys based on the first row keys
        //int heightForAll = firstRowKeysHeight+otherKeysHeightIndex;
        //customize the delete button row 2
        int widthOfDeleteKey = (int) (firstRowKeysWidth*deleteKeyWidthIndex);
        //calculate the width for keys row2 considering proportions of the delete button
        int widthOfOtherKeys = (firstRowKeysWidth*numberOfKeysRow1 - widthOfDeleteKey)/(numberOfKeysRow2-1);

        //drawing the first top row of the keyboard
        //14 - amount of blank buttons in the row 2 of keys
        for (int i=0;i<numberOfKeysRow2;++i) {
            //create new temp button
            CustomJButton tempButton = new CustomJButton(firstKeys[1][i],secondKeys[1][i]);

            //all buttons are not painted on focus by default
            tempButton.setEnabled(false);

            //catching the size of Delete key
            tempButton.setPreferredSize(i != (numberOfKeysRow2-1) ? new Dimension(widthOfOtherKeys,heightForAll):new Dimension(widthOfDeleteKey,heightForAll));

            //add a button to a temp panel
            temPanel.add(tempButton);

            //add button inti a collection
            keys.put(keyCodes[1][i],tempButton);

        }

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy =1;

        //add temp panel into the main panel
        mainPanelOfKeyboardFrame.add(temPanel,c);
    }//end of setUpSecondRow

    //set up 3st top row
    void setUpThirdRow() {
        //create temp panel with FlowLaout
        JPanel temPanel = new JPanel();
        //create temp layout
        FlowLayout tempLayout = new FlowLayout();
        //set horizontal and vertical gap between its components
        tempLayout.setHgap(0);
        tempLayout.setVgap(0);
        tempLayout.setAlignment(FlowLayout.LEFT);

        temPanel.setLayout(tempLayout);

        //calculate the sizes for keys based on the first row keys
        //int heightForAll = firstRowKeysHeight+otherKeysHeightIndex;
        //customize the tab key row 3
        int widthOfTabKey = (int) (firstRowKeysWidth*tabKeyWidthIndex);
        //calculate the width for keys row3 considering proportions of the Tab key
        int widthOfOtherKeys = (firstRowKeysWidth*numberOfKeysRow1 - widthOfTabKey)/(numberOfKeysRow3-1);


        //drawing the first top row of the keyboard
        //14 - amount of blank buttons in the row 3 of keys
        for (int i=0;i<numberOfKeysRow3;++i) {
            //create new temp button
            CustomJButton tempButton = new CustomJButton(firstKeys[2][i],secondKeys[2][i]);
            //all buttons are not painted on focus by default
            tempButton.setEnabled(false);
            //catching the size of Tab key
            tempButton.setPreferredSize(i != 0 ? new Dimension(widthOfOtherKeys,heightForAll):new Dimension(widthOfTabKey,heightForAll));
            //add a button to a temp panel
            temPanel.add(tempButton);

            //add a button into a collection
            keys.put(keyCodes[2][i],tempButton);
        }

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy =2;

        //add temp panel into the main panel
        mainPanelOfKeyboardFrame.add(temPanel,c);
    }//end of setUpThirdRow

    //set up 4st top row
    void setUpForthRow() {
        //create temp panel with FlowLaout
        JPanel temPanel = new JPanel();
        //create temp layout
        FlowLayout tempLayout = new FlowLayout();
        //set horizontal and vertical gap between its components
        tempLayout.setHgap(0);
        tempLayout.setVgap(0);
        tempLayout.setAlignment(FlowLayout.LEFT);

        temPanel.setLayout(tempLayout);

        //calculate the sizes for keys based on the first row keys
        //int heightForAll = firstRowKeysHeight+otherKeysHeightIndex;
        //customize the Caps lock key row 4
        int widthOfCapsLockKey = (int) (firstRowKeysWidth * capslockKeyWidthIndex);
        //customize the Return key row 4
        int widthOfReturnKey = (int) (firstRowKeysWidth * returnKeyWidthIndex);
        //calculate the width for keys row4 considering proportions of the Return and Caps Lock keys
        int widthOfOtherKeys = (firstRowKeysWidth * numberOfKeysRow1 - widthOfCapsLockKey - widthOfReturnKey)/(numberOfKeysRow4-2);


        //drawing the 4th row of the keyboard
        //13 - amount of blank buttons in the row 4 of keys
        for (int i=0;i<numberOfKeysRow4;++i) {
            //create new temp button
            CustomJButton tempButton = new CustomJButton(firstKeys[3][i],secondKeys[3][i]);

            //all buttons are not painted on focus by default
            tempButton.setEnabled(false);

            //catching the size of the Caps Lock and Return keys
            switch (i) {
                //caps lock key
                case 0:
                    tempButton.setPreferredSize(new Dimension(widthOfCapsLockKey,heightForAll));
                    break;
                //return key
                case 12:
                    tempButton.setPreferredSize(new Dimension(widthOfReturnKey,heightForAll));
                    break;
                //any other
                default:
                    tempButton.setPreferredSize(new Dimension(widthOfOtherKeys,heightForAll));
                    break;
            }

            //add a button to a temp panel
            temPanel.add(tempButton);
            //add button into a collection
            keys.put(keyCodes[3][i],tempButton);

        }

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy =3;

        //add temp panel into the main panel
        mainPanelOfKeyboardFrame.add(temPanel,c);
    }//end of setUpForthRow

    //set up 5st top row
    void setUpFifthRow() {
        //create temp panel with FlowLaout
        JPanel temPanel = new JPanel();
        //create temp layout
        FlowLayout tempLayout = new FlowLayout();
        //set horizontal and vertical gap between its components
        tempLayout.setHgap(0);
        tempLayout.setVgap(0);
        tempLayout.setAlignment(FlowLayout.LEFT);

        temPanel.setLayout(tempLayout);

        //calculate the sizes for keys based on the first row keys
        //int heightForAll = firstRowKeysHeight+otherKeysHeightIndex;
        //customize the Left Shift lock key row 5
        int widthOfLeftShiftKey = (int) (firstRowKeysWidth * shiftLeftKeyWidthIndex);
        //customize the Right Shift key row 5
        int widthOfRightShiftKey = (int) (firstRowKeysWidth * shiftRightKeyWidthIndex);
        //calculate the width for keys row4 considering proportions of the Return and Caps Lock keys
        int widthOfOtherKeys = (firstRowKeysWidth * numberOfKeysRow1 - widthOfLeftShiftKey - widthOfRightShiftKey)/(numberOfKeysRow5-2);


        //drawing the 5th row of the keyboard
        //12 - amount of blank buttons in the row 4 of keys
        for (int i=0;i<numberOfKeysRow5;++i) {
            //create new temp button
            CustomJButton tempButton = new CustomJButton(firstKeys[4][i],secondKeys[4][i]);

            //all buttons are not painted on focus by default
            tempButton.setEnabled(false);

            //catching the size of the Left and Right Shift keys
            switch (i) {
                //caps lock key
                case 0:
                    tempButton.setPreferredSize(new Dimension(widthOfLeftShiftKey,heightForAll));
                    break;
                //return key
                case 11:
                    tempButton.setPreferredSize(new Dimension(widthOfRightShiftKey,heightForAll));
                    break;
                //any other
                default:
                    tempButton.setPreferredSize(new Dimension(widthOfOtherKeys,heightForAll));
                    break;
            }

            //add a button to a temp panel
            temPanel.add( tempButton);
            //add a button into a collection
            keys.put(keyCodes[4][i],tempButton);

        }

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy =4;

        //add temp panel into the main panel
        mainPanelOfKeyboardFrame.add(temPanel,c);
    }//end of setUpFifthRow

    //set up 6st top row
    void setUpSixthRow() {
        //create temp panel
        JPanel temPanel = new JPanel();
        //create temp layout
        FlowLayout tempLayout = new FlowLayout();
        //set horizontal and vertical gap between its components
        tempLayout.setHgap(0);
        tempLayout.setVgap(0);
        tempLayout.setAlignment(FlowLayout.LEFT);

        temPanel.setLayout(tempLayout);

        //calculate the sizes for keys based on the first row keys
        //int heightForAll = firstRowKeysHeight+otherKeysHeightIndex;
        //customize the Space bar row 6
        int widthOfSpaceBar = (int) (firstRowKeysWidth * spaceBarKeyWidthIndex);
        //calculate the width for keys row4 considering proportions of the Return and Caps Lock keys
        int widthOfOtherKeys = (firstRowKeysWidth * numberOfKeysRow1 - widthOfSpaceBar)/(numberOfKeysRow6-1);

        //drawing the 6th row of the keyboard
        //10 - amount of keys
        for (int i=0;i<numberOfKeysRow6;++i) {
            //create new temp button
            //no any letters for this row
            CustomJButton tempButton = new CustomJButton(firstKeys[5][i],secondKeys[5][i]);

            //all buttons are not painted on focus by default
            tempButton.setEnabled(false);

            //create another temp panel for arrows keys with Border layout
            //0, 0 horizontal and vertical gaps inside borderLayout
            BorderLayout borderLayout = new BorderLayout(0,0);
            JPanel tempPanelForArrows = new JPanel(borderLayout);

            //catching the size of the Left and Right Shift keys
            switch (i) {
                //space
                case 4:
                    tempButton.setPreferredSize(new Dimension(widthOfSpaceBar,heightForAll));
                    //add a button to a temp panel
                    temPanel.add( tempButton);
                    //add a button into a collection
                    keys.put(keyCodes[5][i],tempButton);
                    break;
                //back arrow
                //same height as first row keys
                case 7:

                    //decrease the height to align elements nicely
                    tempButton.setPreferredSize(new Dimension(widthOfOtherKeys,heightForAll/2));

                    //create empty label to trick the layout
                    JLabel emptyLabel = new JLabel("");
                    //decrease the height to align elements nicely
                    emptyLabel.setPreferredSize(new Dimension(widthOfOtherKeys,heightForAll/2));

                    tempPanelForArrows.add(emptyLabel,BorderLayout.PAGE_START);
                    tempPanelForArrows.add( tempButton,BorderLayout.PAGE_END);
                    temPanel.add(tempPanelForArrows);

                    //add a button into a collection
                    keys.put(keyCodes[5][i],tempButton);

                    break;
                //up and down arrows
                case 8:
                    //decrease the height to align elements nicely
                    tempButton.setPreferredSize(new Dimension(widthOfOtherKeys,heightForAll/2));
                    tempPanelForArrows.add(tempButton,BorderLayout.PAGE_START);

                    //add a button into a collection
                    keys.put(keyCodes[5][i],tempButton);


                    //create new button and put into the array
                    CustomJButton tempButton2 = new CustomJButton();
                    tempButton2.setEnabled(false);

                    tempButton2.setPreferredSize(new Dimension(widthOfOtherKeys,heightForAll/2));
                    tempPanelForArrows.add(tempButton2,BorderLayout.PAGE_END);

                    //add a button into a collection
                    keys.put(keyCodes[5][i+1],tempButton);

                    //add additional panel to the main row panel
                    temPanel.add(tempPanelForArrows);

                    break;
                //forward arrow
                //same height as first row keys
                case 9:
                    //decrease the height to align elements nicely
                    tempButton.setPreferredSize(new Dimension(widthOfOtherKeys,heightForAll/2));

                    //create empty label to trick the layout
                    JLabel emptyLabel1 = new JLabel("");
                    //decrease the height to align elements nicely
                    emptyLabel1.setPreferredSize(new Dimension(widthOfOtherKeys,heightForAll/2));

                    tempPanelForArrows.add(emptyLabel1,BorderLayout.PAGE_START);
                    tempPanelForArrows.add(tempButton,BorderLayout.PAGE_END);
                    temPanel.add(tempPanelForArrows);

                    //add a button into a collection
                    keys.put(keyCodes[5][i+1],tempButton);

                    break;

                //any other
                default:
                    tempButton.setPreferredSize(new Dimension(widthOfOtherKeys,heightForAll));
                    //add a button to the temPanel
                    temPanel.add(tempButton);
                    //add a button into a collection
                    keys.put(keyCodes[5][i],tempButton);

                    break;
            }

        }

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy =5;

        //add temp panel into the main panel
        mainPanelOfKeyboardFrame.add(temPanel,c);
    }//end of setUpSixthRow


}//end of KeyboardLayout
