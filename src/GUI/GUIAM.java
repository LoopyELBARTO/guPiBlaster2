package GUI;

import main.ServoBlaster3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by NkemOhanenye on 3/24/17.
 */
public class GUIAM {
    /**
     * creates the auto and the manual buttons
     */
    private JButton autobutton;
    private JButton manbutton;
    /**
     * creates the ampanel
     */
    private JPanel ampanel;

    public GUIAM(){
        /**
         * the methods created for the file
         */
        AutoButton();
        ManButton();

        /**
         * creates a panel for the auto and manual and creates the layout
         */
        ampanel = new JPanel();
        ampanel.setLayout(new GridLayout(1, 2,10,10));
        /**
         * adds the buttons to the panel
         */
        ampanel.add(autobutton);
        ampanel.add(manbutton);

        ampanel.setBackground(null);

    }
    public void AutoButton(){
        Font myFont = new Font("Dialog", Font.BOLD, 28);
        autobutton = new JButton("AUTO");
        autobutton.setFont(myFont);
        autobutton.setForeground(Color.black);

        autobutton.addActionListener(new AutoButtonListener());
        /**
         * input code here
         */
    }
    public void ManButton(){
        Font myFont = new Font("Dialog", Font.BOLD, 28);
        manbutton = new JButton("MANUAL");
        manbutton.setFont(myFont);
        manbutton.setForeground(Color.black);
        /**
         * input code here
         */
    }
    /**returns the JButton
     *@return creates the autobutton
     */
    public JButton getAutobutton(){return autobutton;}
    /**returns the JButton
     *@return creates the downbutton
     */
    public JButton getManbutton(){return manbutton;}
    /**returns the ampanel
     * @return Ampanel the ampanel. It contains these JButtons
     */
    public JPanel getAmpanel(){return ampanel;}

    private class AutoButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            ServoBlaster3 auto = new ServoBlaster3();
            try {
                auto.automatic();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
