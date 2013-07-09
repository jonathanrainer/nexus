/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;


/**
 * A class to deal with the fact that a lot of the screens are going to be, to
 * some degree, based on a template so this removes a lot of code repetition.
 *
 * @author jonathanrainer
 */
public class Template {

    /**
     * Code to load in the logos necessary for the template from external
     * sources.
     */
    ClassLoader cldr = this.getClass().getClassLoader();
    java.net.URL nwlogo = cldr.getResource("gui/nwlogo.png");
    ImageIcon newWineLogo = new ImageIcon(nwlogo);

    /**
     * Empty constructor, the object is only instantiated for the methods it
     * contains as opposed to being useful as an object in its own right.
     */
    public Template() {
    }

    /**
     * Creates a new JFrame based on the title provided, applies a layout, sets
     * size and adds the logo to it. This frame is then returned.
     *
     * @param title The title that you wish the JFrame to have.
     * @return A JFrame with the template applied to it, so other components can
     * be added on top.
     */
    public JFrame giveGridBagTemplatedJFrame(String title) {
        JFrame templatedFrame = new JFrame(title);
        
        GridBagLayout layout;
        layout = new GridBagLayout();
        templatedFrame.setLayout(layout);
        
        templatedFrame.setSize(900, 700);
        
        JLabel logo = new JLabel(newWineLogo);
        GridBagConstraints logoConstraints = createGridBagConstraints(
                0, 0, GridBagConstraints.BOTH, 0, 0, new Insets(0, 0, 0, 0),
                GridBagConstraints.CENTER, 0.0, 0.0, 1, 5);
        templatedFrame.add(logo, logoConstraints);
        
        // Create the menu to go across the top of the frame.
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        templatedFrame.setJMenuBar(menuBar);
        
        
        templatedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return templatedFrame;
    }
    

    /**
     * This function removes the tedious process of setting each part of the
     * constraints on an objects placement in a GridBayLayout, this could be a
     * future point of weakness too if the implementation of GridBagLayout
     * changes but at least the changes are then confined to one function rather
     * than littered across the code. Refer to the documentation for
     * GridBagLayout to understand what each parameter does.
     *
     * @param gridx Omitted.
     * @param gridy Omitted.
     * @param fill Omitted.
     * @param ipadx Omitted.
     * @param ipady Omitted.
     * @param insets Omitted.
     * @param anchor Omitted.
     * @param weightx Omitted.
     * @param weighty Omitted.
     * @param gridheight Omitted.
     * @param gridwidth Omitted.
     * @return
     */
    public GridBagConstraints createGridBagConstraints(int gridx, int gridy,
            int fill, int ipadx, int ipady, Insets insets, int anchor,
            double weightx, double weighty, int gridheight, int gridwidth) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.fill = fill;
        constraints.ipadx = ipadx;
        constraints.ipady = ipady;
        constraints.insets = insets;
        constraints.anchor = anchor;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.gridheight = gridheight;
        constraints.gridwidth = gridwidth;
        return constraints;
    }

    /**
     * This takes in strings and a size and outputs the correctly formatted
     * string so that when it's used in a GUI it displays as a heading, akin to
     * HTML.
     *
     * @param input The string that will appear as a heading
     * @param size The size of the heading you want to appear. As always in HTML
     * checking is employed to make sure the number is between 1 and 6.
     * @return The input string, correctly formatted for display. If the number
     * entered for size was invalid then the original string is returned.
     */
    public String headingString(String input, int size) {
        // Check if the input size is between 1 and 6
        if (size <= 0 || size > 6) {
            return input;
        } else {
            return "<html><h" + size + "><b>" + input + "</b></h" + size
                    + "</html>";
        }

    }
}
