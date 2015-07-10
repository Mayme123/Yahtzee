// This is the GUI for the Yahtzee game
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GameBoard
   extends JFrame
{
   //create the menu bar
   JMenuBar menuBar;
   
   //file menu
   JMenu fileMenu;
      JMenuItem newGameMenuItem;
      JMenuItem saveGameMenuItem;
      JMenuItem loadGameMenuItem;
      JMenuItem exitGameMenuItem;
   
   //Round menu
   JMenu roundMenu;
      JMenuItem lastRoundMenuItem;
      JMenu roundSelectMenu;
         JMenuItem round1MenuItem;
         JMenuItem round2MenuItem;
         JMenuItem round3MenuItem;
         JMenuItem round4MenuItem;
         JMenuItem round5MenuItem;
         JMenuItem round6MenuItem;
         JMenuItem round7MenuItem;  
         JMenuItem round8MenuItem;
         JMenuItem round9MenuItem;
         JMenuItem round10MenuItem;
         JMenuItem round11MenuItem;
         JMenuItem round12MenuItem;
         JMenuItem round13MenuItem;
   //Array of the round menu items for convenience later
   JMenuItem[] roundMenuItems = {round1MenuItem,round2MenuItem,round3MenuItem,round4MenuItem,round5MenuItem,round6MenuItem,round7MenuItem,round8MenuItem,
      round9MenuItem,round10MenuItem,round11MenuItem,round12MenuItem,round13MenuItem,};
         
   // Panels for all the components
   JPanel northPanel;
      JPanel diePanel1;
      JPanel diePanel2;
   
   JPanel centerPanel;
      JPanel upperScorePanel;
      JPanel lowerScorePanel;
      
   JPanel southPanel;
      JPanel yahtzeePanel;
      JPanel scoreLabelPanel;
      JPanel rollNextPanel;
      
   // Die buttons for the die panels and array to manipulate them all
   JButton dieButton1;
   JButton dieButton2;
   JButton dieButton3;
   JButton dieButton4;
   JButton dieButton5;
   
   //labels, buttons, and text fields for the upper score card
   JLabel upperLabel;
   
   JButton onesButton;
   JButton twosButton;
   JButton threesButton;
   JButton foursButton;
   JButton fivesButton;
   JButton sixesButton;
   
   JTextField onesTextField;
   JTextField twosTextField;
   JTextField threesTextField;
   JTextField foursTextField;
   JTextField fivesTextField;
   JTextField sixesTextField;
   
   //labels, buttons, and text fields for the lower score card
   JLabel lowerLabel;
   
   JButton threeOfAKindButton;
   JButton fourOfAKindButton;
   JButton fullHouseButton;
   JButton smallStraightButton;
   JButton largeStraightButton;
   JButton chanceButton;
   
   JTextField threeOfAKindTextField;
   JTextField fourOfAKindTextField;
   JTextField fullHouseTextField;
   JTextField smallStraightTextField;
   JTextField largeStraightTextField;
   JTextField chanceTextField;
   
   
   //components for yahtee score panel
   JButton yahtzeeButton;
   
   JTextField yahtzeeTextField;
   
   JCheckBox bonusCheckBox1;
   JCheckBox bonusCheckBox2;
   JCheckBox bonusCheckBox3;
   
   JCheckBox[] bonusCheckBoxes;
   
   // Scoring panel components
   JLabel upperScoreLabel;
   JLabel lowerScoreLabel;
   JLabel totalScoreLabel;
   JLabel upperPointsLabel;
   JLabel lowerPointsLabel;
   JLabel totalPointsLabel;
   
   // roll/next panel components
   JButton rollButton;
   JButton nextButton;
   
   public GameBoard()
   {
      // Call methods to add component to the window
      createMenu();
      makeGameBoard();
      
      //set window properties and make visible
      super.setJMenuBar(menuBar);
      super.setTitle("Matt's Yahtzeee game");
      super.setSize(800,850);
      super.setResizable(false);
      super.setVisible(true);
      super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
   private void createMenu()
   {
      // Initialize all  menu components
      menuBar = new JMenuBar();
      
      fileMenu = new JMenu("File");
         newGameMenuItem = new JMenuItem("New Game");
         saveGameMenuItem = new JMenuItem("Save Game");
         loadGameMenuItem = new JMenuItem("Load Game");
         exitGameMenuItem = new JMenuItem("Exit");
      
      roundMenu = new JMenu("Round");
         lastRoundMenuItem = new JMenuItem("Last Round");
         roundSelectMenu = new JMenu("Round Select");
      // Add file menu items to file menu
      fileMenu.add(newGameMenuItem);
      fileMenu.add(saveGameMenuItem);
      fileMenu.add(loadGameMenuItem);
      fileMenu.add(exitGameMenuItem);
      
      //add round menu items
      roundMenu.add(lastRoundMenuItem);
      roundMenu.add(roundSelectMenu);
      
      // Add menus to menu bar
      menuBar.add(fileMenu);
      menuBar.add(roundMenu);
         
   }
   
   public void makeGameBoard()
   {
      super.setLayout(new BorderLayout());
      GridBagConstraints c = new GridBagConstraints();//to be used for the GridBag sections
      
      //initialize panels
      northPanel = new JPanel(new GridLayout(2,1));
      northPanel.setPreferredSize(new Dimension(200,300));
      //northPanel.setBorder(BorderFactory.createLineBorder(Color.black));
         diePanel1 = new JPanel();
         diePanel2 = new JPanel();
         
      centerPanel = new JPanel(new GridLayout(1,2));
      //centerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
      centerPanel.setSize(200,100);
         upperScorePanel  = new JPanel(new GridBagLayout());
         lowerScorePanel = new JPanel(new GridBagLayout());
         
      southPanel = new JPanel(new GridLayout(2,2));
         yahtzeePanel = new JPanel(new GridBagLayout());
         scoreLabelPanel = new JPanel(new GridLayout(3,3));
         rollNextPanel = new JPanel(new GridLayout(2,1));
         
      // This array is to make all of the panel backgrounds the same color later
         JPanel[] panels = {northPanel, diePanel1, diePanel2,centerPanel, upperScorePanel,
            lowerScorePanel, southPanel, yahtzeePanel, scoreLabelPanel, rollNextPanel}; 
       
      // Layout the north panel components
      dieButton1 = new JButton();
      dieButton2 = new JButton();
      dieButton3 = new JButton();
      dieButton4 = new JButton();
      dieButton5 = new JButton();
      
      JButton[] dieButtons = {dieButton1, dieButton2, dieButton3, dieButton4, dieButton5};
      
      for (int i = 0; i < dieButtons.length; i++)
      {
       //hide default JButton background
         dieButtons[i].setBorderPainted(false); 
         dieButtons[i].setContentAreaFilled(false); 
         dieButtons[i].setFocusPainted(false); 
         dieButtons[i].setOpaque(false);
      }
      
      //add dieButtons to die panels
      diePanel1.add(dieButton1);
      diePanel1.add(dieButton2);
      diePanel1.add(dieButton3);
      
      diePanel2.add(dieButton4);
      diePanel2.add(dieButton5);
      
      //layout all center panel components
      // Upper
      upperLabel = new JLabel("Upper");
      
      onesButton = new JButton("Ones");
      twosButton = new JButton("Twos");
      threesButton = new JButton("Threes");
      foursButton = new JButton("Fours");
      fivesButton = new JButton("Fives");
      sixesButton = new JButton("Sixes");
      
      JButton[] upperButtons = {onesButton, twosButton, threesButton, foursButton, fivesButton, sixesButton};
      
      onesTextField = new JTextField(5);
      twosTextField = new JTextField(5);
      threesTextField = new JTextField(5);
      foursTextField = new JTextField(5);
      fivesTextField = new JTextField(5);
      sixesTextField = new JTextField(5);
      
      JTextField[] upperTextFields = {onesTextField, twosTextField, threesTextField, foursTextField, fivesTextField, sixesTextField};
      //set alignment and color of all text field and disable them;
      for(int i = 0; i < upperTextFields.length; ++i)
      {
         upperTextFields[i].setHorizontalAlignment(JTextField.CENTER);
         upperTextFields[i].setBackground(Color.gray);
         upperTextFields[i].setEditable(false);;
      }
      
      // Add Components using grid bag constraints
      c.gridx = 1;
      upperScorePanel.add(upperLabel, c);
      
      for(int i = 0; i < upperButtons.length; i++)
      {
         c.gridx = 0;
         c.gridy = i+1;
         upperScorePanel.add(upperButtons[i], c);
         
         c.gridx = 2;
         upperScorePanel.add(upperTextFields[i],c);
         
      }
      
      // Lower
      lowerLabel = new JLabel("Lower");
      
      threeOfAKindButton = new JButton("Three of a Kind");
      fourOfAKindButton = new JButton("Four of a Kind");
      fullHouseButton = new JButton("Full House");
      smallStraightButton = new JButton("Small Straight");
      largeStraightButton = new JButton("Large Straight");
      chanceButton = new JButton("Chance");
      
      JButton[] lowerButtons = {threeOfAKindButton, fourOfAKindButton, fullHouseButton, smallStraightButton, largeStraightButton,chanceButton};
      
      threeOfAKindTextField = new JTextField(5);
      fourOfAKindTextField = new JTextField(5);
      fullHouseTextField = new JTextField(5);
      smallStraightTextField = new JTextField(5);
      largeStraightTextField = new JTextField(5);
      chanceTextField = new JTextField(5);
      
      JTextField[] lowerTextFields = {threeOfAKindTextField, fourOfAKindTextField, fullHouseTextField, smallStraightTextField, largeStraightTextField,chanceTextField};
      //set alignment and color of all text field and disable them;
      for(int i = 0; i < lowerTextFields.length; ++i)
      {
         lowerTextFields[i].setHorizontalAlignment(JTextField.CENTER);
         lowerTextFields[i].setBackground(Color.gray);
         lowerTextFields[i].setEditable(false);
      }
      
      // Add Components using grid bag constraints
      c = new GridBagConstraints();
      c.gridx = 1;
      lowerScorePanel.add(lowerLabel, c);
      
      for(int i = 0; i < lowerButtons.length; i++)
      {
         c.gridx = 0;
         c.gridy = i+1;
         lowerScorePanel.add(lowerButtons[i], c);
         
         c.gridx = 2;
         lowerScorePanel.add(lowerTextFields[i],c);
         
      }
      
      //layout all south panel components
      // Yahtzee score card
      yahtzeeButton = new JButton("Yahtzee");
      yahtzeeTextField = new JTextField(5);
      yahtzeeTextField.setEditable(false);
      yahtzeeTextField.setBackground(Color.gray);
      
      bonusCheckBox1 = new JCheckBox();
      bonusCheckBox2 = new JCheckBox();
      bonusCheckBox3 = new JCheckBox();
      
      bonusCheckBoxes = new JCheckBox[3];
      bonusCheckBoxes[0] = bonusCheckBox1;
      bonusCheckBoxes[1] = bonusCheckBox2;
      bonusCheckBoxes[2] = bonusCheckBox3;
      for(int i = 0; i < bonusCheckBoxes.length; ++i)
      {
         bonusCheckBoxes[i].setEnabled(false);
      }
      
      c = new GridBagConstraints();
      
      yahtzeePanel.add(yahtzeeButton,c);
      c.gridx = 2;
      yahtzeePanel.add(yahtzeeTextField,c);
      c.gridy = 1;
      c.gridx = 0;
      yahtzeePanel.add(bonusCheckBox1, c);
      c.gridx = 1;
      yahtzeePanel.add(bonusCheckBox2, c);
      c.gridx = 2;
      yahtzeePanel.add(bonusCheckBox3, c);
      
      //score panel
      upperScoreLabel = new JLabel("Upper Score:");
      lowerScoreLabel = new JLabel("Lower Score:");
      totalScoreLabel = new JLabel("Total Score:");
      
      upperPointsLabel = new JLabel("0");
      lowerPointsLabel = new JLabel("0");
      totalPointsLabel = new JLabel("0");
      
      scoreLabelPanel.add(upperScoreLabel);
      scoreLabelPanel.add(upperPointsLabel);
      scoreLabelPanel.add(lowerScoreLabel);
      scoreLabelPanel.add(lowerPointsLabel);
      scoreLabelPanel.add(totalScoreLabel);
      scoreLabelPanel.add(totalPointsLabel);
      
      //roll next panel
      rollButton = new JButton("Roll");
      nextButton = new JButton("Next");
      
      rollNextPanel.add(rollButton);
      rollNextPanel.add(nextButton);
      
      //add all panels
      northPanel.add(diePanel1);
      northPanel.add(diePanel2);
      
      centerPanel.add(upperScorePanel);
      centerPanel.add(lowerScorePanel);
      
      southPanel.add(yahtzeePanel);
      southPanel.add(scoreLabelPanel);
      southPanel.add(rollNextPanel);
      
      add(northPanel,BorderLayout.PAGE_START);
      add(centerPanel, BorderLayout.CENTER);
      add(southPanel, BorderLayout.PAGE_END);
      
      //set background of all panels to be dark green
      this.setBackground(new Color(26,122,34));
      for(JPanel element: panels)
      {
         element.setBackground(new Color(26,122,34));
      }
      
   }
   
   //adds a round to the round menu
   public void addRoundMenuItem(int roundNum, ActionListener listener)
   {
      JMenuItem newRoundMenuItem = new JMenuItem();
      
      roundMenuItems[roundNum - 1] = new JMenuItem("Round "+roundNum);
      newRoundMenuItem = roundMenuItems[roundNum - 1];
      
      roundSelectMenu.add(newRoundMenuItem);
      newRoundMenuItem.addActionListener(listener);
      newRoundMenuItem.setActionCommand(newRoundMenuItem.getText());
      
   }
   
   // redraw the die buttons with new die faces
   public void drawDice(Die[] dice)
   {
      JButton[] dieButtons = {dieButton1, dieButton2, dieButton3, dieButton4, dieButton5};
      
      for(int i = 0; i < dice.length; ++i)
         dieButtons[i].setIcon(dice[i].getDieImage());
   }
   
   //add action listeners to all controls
   void addActionListeners(ActionListener listener)
   {
      rollButton.addActionListener(listener);
      rollButton.setActionCommand("roll");
      
      JButton[] dieButtons = {dieButton1,dieButton2, dieButton3, dieButton4, dieButton5};
      for(int i = 0; i < dieButtons.length; ++i)
      {
         dieButtons[i].addActionListener(listener);
         dieButtons[i].setActionCommand("die"+(i+1));
      }
      
      nextButton.addActionListener(listener);
      nextButton.setActionCommand("next");
      
      onesButton.addActionListener(listener);
      onesButton.setActionCommand("ONES");
      twosButton.addActionListener(listener);
      twosButton.setActionCommand("TWOS");
      threesButton.addActionListener(listener);
      threesButton.setActionCommand("THREES");
      foursButton.addActionListener(listener);
      foursButton.setActionCommand("FOURS");
      fivesButton.addActionListener(listener);
      fivesButton.setActionCommand("FIVES");
      sixesButton.addActionListener(listener);
      sixesButton.setActionCommand("SIXES");
      
      threeOfAKindButton.addActionListener(listener);
      threeOfAKindButton.setActionCommand("THREE_OF_A_KIND");
      fourOfAKindButton.addActionListener(listener);
      fourOfAKindButton.setActionCommand("FOUR_OF_A_KIND");
      fullHouseButton.addActionListener(listener);
      fullHouseButton.setActionCommand("FULL_HOUSE");
      smallStraightButton.addActionListener(listener);
      smallStraightButton.setActionCommand("SMALL_STRAIGHT");
      largeStraightButton.addActionListener(listener);
      largeStraightButton.setActionCommand("LARGE_STRAIGHT");
      yahtzeeButton.addActionListener(listener);
      yahtzeeButton.setActionCommand("YAHTZEE");
      chanceButton.addActionListener(listener);
      chanceButton.setActionCommand("CHANCE");
      
      newGameMenuItem.addActionListener(listener);
      newGameMenuItem.setActionCommand("new game");
      
      saveGameMenuItem.addActionListener(listener);
      saveGameMenuItem.setActionCommand("save game");
      
      loadGameMenuItem.addActionListener(listener);
      loadGameMenuItem.setActionCommand("load game");
      
      exitGameMenuItem.addActionListener(listener);
      exitGameMenuItem.setActionCommand("exit");
      
      lastRoundMenuItem.addActionListener(listener);
      lastRoundMenuItem.setActionCommand("last round");
   }
   
   public void clear()
   {
      onesTextField.setText("");
      twosTextField.setText("");
      threesTextField.setText("");
      foursTextField.setText("");
      fivesTextField.setText("");
      sixesTextField.setText("");
      threeOfAKindTextField.setText("");
      fourOfAKindTextField.setText("");
      fullHouseTextField.setText("");
      smallStraightTextField.setText("");
      largeStraightTextField.setText("");
      chanceTextField.setText("");
      
      yahtzeeTextField.setText("");
      bonusCheckBox1.setSelected(false);
      bonusCheckBox2.setSelected(false);
      bonusCheckBox3.setSelected(false);
      
      upperPointsLabel.setText("0");
      lowerPointsLabel.setText("0");
      totalPointsLabel.setText("0");
   }
}
