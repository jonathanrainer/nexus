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
import javax.swing.JScrollPane;
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
    private JScrollPane resultsScroll;
    private JList resultsArea;
    private JList duplicateResultsArea;
    private JPanel buttonsPane;
    private JButton submitButton;
    private JButton exitButton;
    private Template template;
    
    public ResultsBox(Ticket[] results, String type)
    {
        template = new Template();
        
        mainFrame = new JFrame("Results of Query - Find " + type + " Tickets");
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
        
        buttonsPane.add(exitButton);
        
        mainFrame.add(new JLabel(template.headingString("Results", 2), JLabel.CENTER));
        mainFrame.add(resultsPane);
        mainFrame.add(buttonsPane);
        switch(type)
        {
            case "Duplicate":
                mainFrame.setPreferredSize(new Dimension(450, 525));
                resultsPane.setLayout(new GridLayout(2,2));
                JLabel potentialDuplicates = new JLabel(template.headingString("Potential Duplicates", 3));
                potentialDuplicates.setHorizontalAlignment(JLabel.CENTER);
                JLabel entriesInDatabase = new JLabel(template.headingString("Entries in Database", 3));
                entriesInDatabase.setHorizontalAlignment(JLabel.CENTER);
                duplicateResultsArea = new JList();
                resultsPane.add(potentialDuplicates);
                resultsPane.add(entriesInDatabase);
                resultsPane.add(resultsArea);
                resultsPane.add(duplicateResultsArea);
                buttonsPane.add(submitButton);
                resultsArea.setListData(results);
                resultsArea.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                
                break;
            case "Unprinted":
                mainFrame.setPreferredSize(new Dimension(375, 475));
                resultsPane.add(resultsArea);
                resultsArea.setListData(results);
                resultsArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                break;
            default:
                mainFrame.setPreferredSize(new Dimension(525, 800));
                resultsArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                resultsArea.setListData(results);
                resultsScroll = new JScrollPane(resultsArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                resultsPane.add(resultsScroll);
                break;
        }
        mainFrame.pack();
        mainFrame.setVisible(true);
        
        
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

    public JList getDuplicateResultsArea()
    {
        return duplicateResultsArea;
    }

    public JFrame getMainFrame()
    {
        return mainFrame;
    }
    
    
    
}
