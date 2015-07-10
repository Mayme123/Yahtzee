import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Controller
{
   private GameBoard gb;
   private YahtzeeGame yahtzeeGame;
   private GameBoardListener gameBoardListener;
   private File savedGame;
   private JFileChooser fc;
   private LinkedHashMap<Categories, JTextField> categoryTextFieldMap;
   
   public Controller()
   {
      gb = new GameBoard();
      yahtzeeGame = new YahtzeeGame();
      File saveDirectory = new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\YahtzeeSaves");
      if(!(saveDirectory.exists()))
      {
         saveDirectory.mkdirs();
      }
      fc= new JFileChooser("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\YahtzeeSaves");
      
      // Roll the first set of dice
      gb.drawDice(yahtzeeGame.roll());
      
      if(gameBoardListener == null)
      {
         gameBoardListener = new GameBoardListener();
         gb.addActionListeners(gameBoardListener);
      }
      
      //populate hash map to use in the load game case
      categoryTextFieldMap = new LinkedHashMap<Categories, JTextField>();
      categoryTextFieldMap.put(Categories.ONES, gb.onesTextField);
      categoryTextFieldMap.put(Categories.TWOS, gb.twosTextField);
      categoryTextFieldMap.put(Categories.THREES, gb.threesTextField);
      categoryTextFieldMap.put(Categories.FOURS, gb.foursTextField);
      categoryTextFieldMap.put(Categories.FIVES, gb.fivesTextField);
      categoryTextFieldMap.put(Categories.SIXES, gb.sixesTextField);
      categoryTextFieldMap.put(Categories.THREE_OF_A_KIND, gb.threeOfAKindTextField);
      categoryTextFieldMap.put(Categories.FOUR_OF_A_KIND, gb.fourOfAKindTextField);
      categoryTextFieldMap.put(Categories.FULL_HOUSE, gb.fullHouseTextField);
      categoryTextFieldMap.put(Categories.SMALL_STRAIGHT, gb.smallStraightTextField);
      categoryTextFieldMap.put(Categories.LARGE_STRAIGHT, gb.largeStraightTextField);
      categoryTextFieldMap.put(Categories.CHANCE, gb.chanceTextField);
      categoryTextFieldMap.put(Categories.YAHTZEE, gb.yahtzeeTextField);
   }
    

   
   class GameBoardListener implements ActionListener{

      @Override
      public void actionPerformed(ActionEvent action)
      {
         switch(action.getActionCommand())
         {
            case "new game":
               //call the newGame method and clear the category fields
               yahtzeeGame.newGame();
               gb.onesTextField.setText("");
               gb.twosTextField.setText("");
               gb.threesTextField.setText("");
               gb.foursTextField.setText("");
               gb.fivesTextField.setText("");
               gb.sixesTextField.setText("");
               gb.threeOfAKindTextField.setText("");
               gb.fourOfAKindTextField.setText("");
               gb.fullHouseTextField.setText("");
               gb.smallStraightTextField.setText("");
               gb.largeStraightTextField.setText("");
               gb.chanceTextField.setText("");
               
               gb.yahtzeeTextField.setText("");
               gb.bonusCheckBox1.setSelected(false);
               gb.bonusCheckBox2.setSelected(false);
               gb.bonusCheckBox3.setSelected(false);
               
               gb.upperPointsLabel.setText(Integer.toString(yahtzeeGame.scoreCard.getUpperScore()));
               gb.lowerPointsLabel.setText(Integer.toString(yahtzeeGame.scoreCard.getLowerScore()));
               gb.totalPointsLabel.setText(Integer.toString(yahtzeeGame.scoreCard.getTotalScore()));
               
               gb.roundSelectMenu.removeAll();
               
               yahtzeeGame.nextRound();
               gb.drawDice(yahtzeeGame.roll());
               break;
               
            case "save game":
               int retrival = fc.showSaveDialog(gb);
               if (retrival == JFileChooser.APPROVE_OPTION)
               {
                  savedGame =
                     new File(fc.getCurrentDirectory(), fc.getSelectedFile().getName());
                  //System.out.println(savedGame.getAbsolutePath()+ " " + savedGame.getName());
                  try
                  {
                     FileOutputStream fos = new FileOutputStream(savedGame);
                     ObjectOutputStream oos = new ObjectOutputStream(fos);

                     oos.writeObject(yahtzeeGame);

                     oos.close();
                  }
                  catch (FileNotFoundException e)
                  {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                  }
                  catch (IOException e)
                  {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                  }
               }
               break;
               
            case "load game":
               retrival = fc.showOpenDialog(gb);
               if (retrival == JFileChooser.APPROVE_OPTION)
               {
                  gb.clear();
                  yahtzeeGame.loadGame(fc.getSelectedFile());
                  for (int i = 0; i < yahtzeeGame.rounds.size(); ++i)
                  {
                     gb.addRoundMenuItem((i + 1), gameBoardListener);
                  }
                  fillGameBoardFields();
               }
               break;
            case "exit":
               System.exit(0);
               break;
            case "roll":
               gb.drawDice(yahtzeeGame.roll());
               break;
            case "next":
               if (yahtzeeGame.endOfRound)
               {
                  yahtzeeGame.nextRound();
                  gb.drawDice(yahtzeeGame.roll());
                  gb.addRoundMenuItem(yahtzeeGame.rounds.peek().getRoundNum(), gameBoardListener);
               }
               else if(yahtzeeGame.bonus)
               {
                  JOptionPane.showMessageDialog(null , "Choose a category to take a zero in.");
               }
               break;
            case "last round":
               int lastRound = (yahtzeeGame.rounds.peek().getRoundNum())-1;
               yahtzeeGame.changeRound(lastRound);
               gb.roundSelectMenu.remove(gb.roundSelectMenu.getItemCount()-1);
               fillGameBoardFields();
               break;
            //cases for the round select menu (try to shorten this later)
            case "Round 1":
               yahtzeeGame.changeRound(1);
               while(gb.roundSelectMenu.getItemCount() > yahtzeeGame.rounds.size())
               {
                  gb.roundSelectMenu.remove(yahtzeeGame.rounds.size()); 
               }
               fillGameBoardFields();
               break;
            case "Round 2":
               yahtzeeGame.changeRound(2);
               while(gb.roundSelectMenu.getItemCount() > yahtzeeGame.rounds.size())
               {
                  gb.roundSelectMenu.remove(yahtzeeGame.rounds.size()); 
               }
               fillGameBoardFields();
               break;
            case "Round 3":
               yahtzeeGame.changeRound(3);
               while(gb.roundSelectMenu.getItemCount() > yahtzeeGame.rounds.size())
               {
                  gb.roundSelectMenu.remove(yahtzeeGame.rounds.size()); 
               }
               fillGameBoardFields();
               break;
            case "Round 4":
               yahtzeeGame.changeRound(4);
               while(gb.roundSelectMenu.getItemCount() > yahtzeeGame.rounds.size())
               {
                  gb.roundSelectMenu.remove(yahtzeeGame.rounds.size()); 
               }
               fillGameBoardFields();
               break;
            case "Round 5":
               yahtzeeGame.changeRound(5);
               while(gb.roundSelectMenu.getItemCount() > yahtzeeGame.rounds.size())
               {
                  gb.roundSelectMenu.remove(yahtzeeGame.rounds.size()); 
               }
               fillGameBoardFields();
               break;
            case "Round 6":
               yahtzeeGame.changeRound(6);
               while(gb.roundSelectMenu.getItemCount() > yahtzeeGame.rounds.size())
               {
                  gb.roundSelectMenu.remove(yahtzeeGame.rounds.size()); 
               }
               fillGameBoardFields();
               break;
            case "Round 7":
               yahtzeeGame.changeRound(7);
               while(gb.roundSelectMenu.getItemCount() > yahtzeeGame.rounds.size())
               {
                  gb.roundSelectMenu.remove(yahtzeeGame.rounds.size()); 
               }
               fillGameBoardFields();
               break;
            case "Round 8":
               yahtzeeGame.changeRound(8);
               while(gb.roundSelectMenu.getItemCount() > yahtzeeGame.rounds.size())
               {
                  gb.roundSelectMenu.remove(yahtzeeGame.rounds.size()); 
               }
               fillGameBoardFields();
               break;
            case "Round 9":
               yahtzeeGame.changeRound(9);
               while(gb.roundSelectMenu.getItemCount() > yahtzeeGame.rounds.size())
               {
                  gb.roundSelectMenu.remove(yahtzeeGame.rounds.size()); 
               }
               fillGameBoardFields();
               break;
            case "Round 10":
               yahtzeeGame.changeRound(10);
               while(gb.roundSelectMenu.getItemCount() > yahtzeeGame.rounds.size())
               {
                  gb.roundSelectMenu.remove(yahtzeeGame.rounds.size()); 
               }
               fillGameBoardFields();
               break;
            case "Round 11":
               yahtzeeGame.changeRound(11);
               while(gb.roundSelectMenu.getItemCount() > yahtzeeGame.rounds.size())
               {
                  gb.roundSelectMenu.remove(yahtzeeGame.rounds.size()); 
               }
               fillGameBoardFields();
               break;
            case "Round 12":
               yahtzeeGame.changeRound(12);
               while(gb.roundSelectMenu.getItemCount() > yahtzeeGame.rounds.size())
               {
                  gb.roundSelectMenu.remove(yahtzeeGame.rounds.size()); 
               }
               fillGameBoardFields();
               break;
            case "Round 13":
               yahtzeeGame.changeRound(13);
               while(gb.roundSelectMenu.getItemCount() > yahtzeeGame.rounds.size())
               {
                  gb.roundSelectMenu.remove(yahtzeeGame.rounds.size()); 
               }
               fillGameBoardFields();
               break;
            //end of round select menu cases
               
            case "die1":
               yahtzeeGame.holdDie(0);
               gb.drawDice(yahtzeeGame.getDice());
               break;
            case "die2":
               yahtzeeGame.holdDie(1);
               gb.drawDice(yahtzeeGame.getDice());
               break;
            case "die3":
               yahtzeeGame.holdDie(2);
               gb.drawDice(yahtzeeGame.getDice());
               break;
            case "die4":
               yahtzeeGame.holdDie(3);
               gb.drawDice(yahtzeeGame.getDice());
               break;
            case "die5":
               yahtzeeGame.holdDie(4);
               gb.drawDice(yahtzeeGame.getDice());
               break;
               
            case "ONES":
               if (yahtzeeGame.scoreRound(Categories.ONES))
               {
                  gb.onesTextField.setText(((Integer) yahtzeeGame.getPointsForCurrentRound()).toString());
                  gb.upperPointsLabel.setText(((Integer) yahtzeeGame.getUpperScore()).toString());
                  gb.totalPointsLabel.setText(((Integer) yahtzeeGame.getTotalScore()).toString());
               }
               break;
            case "TWOS":
               if (yahtzeeGame.scoreRound(Categories.TWOS))
               {
                  gb.twosTextField.setText(((Integer) yahtzeeGame.getPointsForCurrentRound()).toString());
                  gb.upperPointsLabel.setText(((Integer) yahtzeeGame.getUpperScore()).toString());
                  gb.totalPointsLabel.setText(((Integer) yahtzeeGame.getTotalScore()).toString());
               }
               break;
            case "THREES":
               if (yahtzeeGame.scoreRound(Categories.THREES))
               {
                  gb.threesTextField.setText(((Integer) yahtzeeGame.getPointsForCurrentRound()).toString());
                  gb.upperPointsLabel.setText(((Integer) yahtzeeGame.getUpperScore()).toString());
                  gb.totalPointsLabel.setText(((Integer) yahtzeeGame.getTotalScore()).toString());
               }
               break;
            case "FOURS":
               if (yahtzeeGame.scoreRound(Categories.FOURS))
               {
                  gb.foursTextField.setText(((Integer) yahtzeeGame.getPointsForCurrentRound()).toString());
                  gb.upperPointsLabel.setText(((Integer) yahtzeeGame.getUpperScore()).toString());
                  gb.totalPointsLabel.setText(((Integer) yahtzeeGame.getTotalScore()).toString());
               }
               break;
            case "FIVES":
               if (yahtzeeGame.scoreRound(Categories.FIVES))
               {
                  gb.fivesTextField.setText(((Integer) yahtzeeGame.getPointsForCurrentRound()).toString());
                  gb.upperPointsLabel.setText(((Integer) yahtzeeGame.getUpperScore()).toString());
                  gb.totalPointsLabel.setText(((Integer) yahtzeeGame.getTotalScore()).toString());
               }
               break;
            case "SIXES":
               if (yahtzeeGame.scoreRound(Categories.SIXES))
               {
                  gb.sixesTextField.setText(((Integer) yahtzeeGame.getPointsForCurrentRound()).toString());
                  gb.upperPointsLabel.setText(((Integer) yahtzeeGame.getUpperScore()).toString());
                  gb.totalPointsLabel.setText(((Integer) yahtzeeGame.getTotalScore()).toString());
               }
               break;
            case "THREE_OF_A_KIND":
                  if (yahtzeeGame.scoreRound(Categories.THREE_OF_A_KIND))
                  {
                     gb.threeOfAKindTextField.setText(((Integer) yahtzeeGame.getPointsForCurrentRound()).toString());
                     gb.lowerPointsLabel.setText(((Integer) yahtzeeGame.getLowerScore()).toString());
                     gb.totalPointsLabel.setText(((Integer) yahtzeeGame.getTotalScore()).toString());
                  }
               break;
            case "FOUR_OF_A_KIND":
                  if (yahtzeeGame.scoreRound(Categories.FOUR_OF_A_KIND))
                  {
                     gb.fourOfAKindTextField.setText(((Integer) yahtzeeGame.getPointsForCurrentRound()).toString());
                     gb.lowerPointsLabel.setText(((Integer) yahtzeeGame.getLowerScore()).toString());
                     gb.totalPointsLabel.setText(((Integer) yahtzeeGame.getTotalScore()).toString());
                  }
               break;
            case "FULL_HOUSE":
                  if (yahtzeeGame.scoreRound(Categories.FULL_HOUSE))
                  {
                     gb.fullHouseTextField.setText(((Integer) yahtzeeGame.getPointsForCurrentRound()).toString());
                     gb.lowerPointsLabel.setText(((Integer) yahtzeeGame.getLowerScore()).toString());
                     gb.totalPointsLabel.setText(((Integer) yahtzeeGame.getTotalScore()).toString());
                  }
               break;
            case "SMALL_STRAIGHT":
                  if (yahtzeeGame.scoreRound(Categories.SMALL_STRAIGHT))
                  {
                     gb.smallStraightTextField.setText(((Integer) yahtzeeGame.getPointsForCurrentRound()).toString());
                     gb.lowerPointsLabel.setText(((Integer) yahtzeeGame.getLowerScore()).toString());
                     gb.totalPointsLabel.setText(((Integer) yahtzeeGame.getTotalScore()).toString());
                  }
               break;
            case "LARGE_STRAIGHT":
                  if (yahtzeeGame.scoreRound(Categories.LARGE_STRAIGHT))
                  {
                     gb.largeStraightTextField.setText(((Integer) yahtzeeGame.getPointsForCurrentRound()).toString());
                     gb.lowerPointsLabel.setText(((Integer) yahtzeeGame.getLowerScore()).toString());
                     gb.totalPointsLabel.setText(((Integer) yahtzeeGame.getTotalScore()).toString());
                  }
               break;
            case "YAHTZEE":
                  if (yahtzeeGame.scoreRound(Categories.YAHTZEE))
                  {
                     if(yahtzeeGame.scoreCard.yahtzee.getScored()<2)
                     {
                        gb.yahtzeeTextField.setText(((Integer) yahtzeeGame.getPointsForCurrentRound()).toString());
                        gb.lowerPointsLabel.setText(((Integer) yahtzeeGame.getLowerScore()).toString());
                        gb.totalPointsLabel.setText(((Integer) yahtzeeGame.getTotalScore()).toString());
                     }
                     else if(yahtzeeGame.scoreCard.yahtzee.getScored()<5)
                     {
                       gb.bonusCheckBoxes[yahtzeeGame.scoreCard.yahtzee.getScored()-2].setSelected(true);
                       gb.lowerPointsLabel.setText(((Integer) yahtzeeGame.getLowerScore()).toString());
                       gb.totalPointsLabel.setText(((Integer) yahtzeeGame.getTotalScore()).toString());
                     }
                  }
               break;
            case "CHANCE":
                  if (yahtzeeGame.scoreRound(Categories.CHANCE))
                  {
                     gb.chanceTextField.setText(((Integer) yahtzeeGame.getPointsForCurrentRound()).toString());
                     gb.lowerPointsLabel.setText(((Integer) yahtzeeGame.getLowerScore()).toString());
                     gb.totalPointsLabel.setText(((Integer) yahtzeeGame.getTotalScore()).toString());
                  }
               break;
         }
         
      }
      
   }
   
   private void fillGameBoardFields()
   {
      for(Categories category: Categories.values())
      {
         if(yahtzeeGame.scoreCard.getCategory(category).isScored)
         {
            categoryTextFieldMap.get(category).setText(Integer.toString(yahtzeeGame.scoreCard.getCategory(category).getScore()));
         }
         else
         {
            categoryTextFieldMap.get(category).setText("");
         }
      }
      
      YahtzeeCategory yahtzee = (YahtzeeCategory)yahtzeeGame.scoreCard.getCategory(Categories.YAHTZEE);
      gb.bonusCheckBox1.setSelected(false);
      gb.bonusCheckBox2.setSelected(false);
      gb.bonusCheckBox3.setSelected(false);
      
      if(yahtzee.getScored()>0)
      {
         gb.yahtzeeTextField.setText("50");
      }
      switch(yahtzee.getScored())
      {
         case 2:
            gb.bonusCheckBox1.setSelected(true);
            break;
         case 3:
            gb.bonusCheckBox1.setSelected(true);
            gb.bonusCheckBox2.setSelected(true);
            break;
         case 4:
            gb.bonusCheckBox1.setSelected(true);
            gb.bonusCheckBox2.setSelected(true);
            gb.bonusCheckBox3.setSelected(true);
            break;
      }
      
      gb.upperPointsLabel.setText(Integer.toString(yahtzeeGame.scoreCard.getUpperScore()));
      gb.lowerPointsLabel.setText(Integer.toString(yahtzeeGame.scoreCard.getLowerScore()));
      gb.totalPointsLabel.setText(Integer.toString(yahtzeeGame.scoreCard.getTotalScore()));
      
      gb.drawDice(yahtzeeGame.getDice());
   }
}
