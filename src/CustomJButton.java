import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by BMW on 2016-05-20.
 */
class CustomJButton extends JButton {

    //prepare two string to be placed on top and the bottom
    String upperText ="", lowerText="";

    //constructor for customized button
    CustomJButton(String upperText, String lowerText) {
        //two text to be place on the top and the bottom of the button
        this.upperText = upperText;
        this.lowerText = lowerText;
    }

    //empty constructor for rows with no letters
    CustomJButton() {}

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //set up a font for the key
        g.setFont(new Font(Settings.fontName,Settings.fontType,Settings.fontSize));
        //placing two texts inside the button
        g.drawString(upperText, KeyboardLayout.upperX,  KeyboardLayout.upperY);
        g.drawString(lowerText, KeyboardLayout.firstRowKeysWidth- KeyboardLayout.lowerX, KeyboardLayout.firstRowKeysHeight- KeyboardLayout.lowerY);

        //if the button enabled - draw nice rounded border
        if ((this.isEnabled()) && (Settings.blueLight == 1)) {
            //get instance of Graphic2D from g (downcast)
            Graphics2D g2 = (Graphics2D) g;
            //determine current dimensions of the pressed button
            int w = this.getWidth();
            int x = this.getHeight();
            //set thickness of the rectangle border around a key
            g2.setStroke(new BasicStroke(3));
            //set desirable color of the border
            g2.setColor(new Color(141,220,250));
            //draw a border for the key
            g2.drawRoundRect(3,3,w-6,x-6,5,5);
            // dispose the instance of the Graphics2D class
            g2.dispose();
        }

        //dispose the instance of the Graphics class
        g.dispose();
    }
}
