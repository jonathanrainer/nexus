/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import system.Ticket;

/**
 *
 * @author jonathanrainer
 */
public class ResultsBox
{
    private JFrame mainFrame;
    private JPanel resultsPane;
    private JList resultsArea;
    private JPanel buttonsPane;
    private JButton submitButton;
    private JButton exitButton;
    private Template template;
    
    public ResultsBox(Ticket[] results, String type)
    {
        template = new Template();
        
        mainFrame = new JFrame("Results of Query - Find " + type + " Tickets");
        mainFrame.setPreferredSize(new Dimension(375, 475));
        mainFrame.setLayout(new GridLayout(3,1));
        
        resultsPane = new JPanel();
        buttonsPane = new JPanel();
        
        resultsArea = new JList();
        resultsArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        submitButton = new JButton(template.headingString("Mark Selected as Duplicates", 3));
        exitButton = new JButton(template.headingString("Exit", 3));
        exitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mainFrame.dispose();
            }
        });
        
        resultsPane.add(resultsArea);
        buttonsPane.add(exitButton);
        
        mainFrame.add(new JLabel(template.headingString("Results", 2), JLabel.CENTER));
        mainFrame.add(resultsPane);
        mainFrame.add(buttonsPane);
        mainFrame.pack();
        mainFrame.setVisible(true);
        switch(type)
        {
            case "Duplicate":
                buttonsPane.add(submitButton);
                resultsArea.setListData(results);
                resultsArea.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                
                break;
            case "Unprinted":
                resultsArea.setListData(results);
                resultsArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                break;
        }
        
        
        
    }

    public JList getResultsArea()
    {
        return resultsArea;
    }

    public JButton getSubmitButton()
    {
        return submitButton;
    }

    public JButton getExitButton()
    {
        return exitButton;
    }
    
    
    
}
