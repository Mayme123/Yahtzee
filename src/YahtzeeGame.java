import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

// This is the class for the game logic
public class YahtzeeGame implements Serializable
{
   Die[] dice;
   int roll = 1;
   boolean endOfRound;
   boolean wildCard;
   boolean bonus;
   ScoreCard scoreCard;
   Stack<Round> rounds;

   public YahtzeeGame()
   {
      dice = new Die[5];
      for(int i = 0; i < dice.length; ++i)
      {
         dice[i] = new Die();
      }

      scoreCard = new ScoreCard();
      rounds = new Stack<Round>();
      roll = 1;
      endOfRound = false;
      wildCard = false;
      bonus = false;
   }

   // roll for a new set of die faces
   public Die[] roll()
   {
      if (!endOfRound)
      {
         if (roll <= 3)
         {
            for (int i = 0; i < dice.length; ++i)
            {
               // If a die is held, do not roll for a new one
               if (dice[i].isHeld() != true)
               {
                  dice[i] = new Die();
                  //TESTING ONLY- COMMENT OUT AFTER TESTING
//                dice[i].setDieFace(5);
                  //------------------------------------------l

               }

            }

            //TESTING FULL HOUSE. DELETE
            //         dice[0].setDieFace(2);
            //         dice[1].setDieFace(2);
            //         dice[2].setDieFace(3);
            //         dice[3].setDieFace(3);
            //         dice[4].setDieFace(3);
            //----------------------------
         }
         ++roll;
      }
      return dice;
   }
   // This method sets all the dice to be unheld
   public void unholdDice()
   {
      for (int i = 0; i < dice.length;++i)
      {
         dice[i].setHeld(false);
      }
   }

   // this method initiates the next round
   public void nextRound()
   {
      if (!bonus)
      {
         roll = 1;
         unholdDice();
         endOfRound = false;
      }
   }

   //Score a category for the current round
   /**
    * @param category
    * @return
    */
   public boolean scoreRound(Categories category)
   {
      boolean roundScored = false;
      
      // there are five checks below to score for the round.
      // first, check to see if a bonus chip has been used(1). if it has, the player must take a zero in a category.
      // then, check to see if a wild card can be used(2). if so, player gets full points in a lower category even if they
      // do not have the required dice.
      // then, if it is not already the end of the round(3), check to see if the attempted category is a yahtzee(4).
      // if it is not, the category is scored normally. Otherwise, special yahtzee rules are put into play(bonuses and wild cards).
      // the final check looks at whether the round was scored on the score card(or, in the yahtzee category, how many times its been scored)(5).
      // player cannot take a zero in the yahtzee category
      
      if (!bonus) //(1)
      {
         
         if (scoreCard.yahtzee.isScorable(dice, wildCard)
                                       && (scoreCard.yahtzee.getScored() > 3))//(2)
         {
            wildCard = true;
         }
         
         if (!endOfRound)//(3)
         {
            if (!(category.equals(Categories.YAHTZEE)))//(4)
            {
               if (scoreCard.scoreRound(dice, category, wildCard))//(5)
               {
                  rounds.push(new Round((rounds.size() + 1),
                                        scoreCard.getCategory(category)));
//                  System.out.println(rounds.peek().getRoundNum() + ", "
//                                                + rounds.peek().getCategoryScored() + ", "
//                                                + rounds.peek().getPointsScored()); // for debugging
                  endOfRound = true;
                  roundScored = true;
               }
            }
            else if(scoreCard.yahtzee.getScored() < 4 && scoreCard.yahtzee.isScorable(dice, wildCard))//(5)
            {
               scoreCard.scoreYahtzee(dice);
               rounds.push(new Round((rounds.size() + 1),
                                     scoreCard.getCategory(category)));
//               System.out.println(rounds.peek().getRoundNum() + ", "
//                                             + rounds.peek().getCategoryScored() + ", "
//                                             + rounds.peek().getPointsScored()); // for debugging
               if (scoreCard.yahtzee.getScored() < 2)
               {
                  endOfRound = true;
               }
               else
               {
                  JOptionPane.showMessageDialog(null , "Choose a category to take a zero in.");    
                  bonus = true;
               }
               roundScored = true;
            }
         }
//         System.out.println(rounds.peek().getRoundNum() + ", "
//                                       + rounds.peek().getCategoryScored() + ", "
//                                       + rounds.peek().getPointsScored()); // for debugging
      }
      else//(1)
      {
         if (category != Categories.YAHTZEE)
         {
            scoreCard.takeZero(category);
            endOfRound = true;
            roundScored = true;
            //         System.out.println(rounds.peek().getRoundNum() + ", "
            //                                       + rounds.peek().getCategoryScored() + ", "
            //                                       + rounds.peek().getPointsScored()); // for debugging
         }
      }
      wildCard = false;
      return roundScored;
   }

   //get the score for the current round
   public int getPointsForCurrentRound()
   {

      if (!bonus)
      {
         if (!rounds.isEmpty())
         {
            Round currentRound = rounds.peek();
            return currentRound.getPointsScored();
         }
         else
         {
            return 0;
         }
      }
      else
      {
         bonus = false;
         return 0;
      }
   }

   public int getLowerScore()
   {
      return scoreCard.getLowerScore();
   }

   public int getUpperScore()
   {
      return scoreCard.getUpperScore();
   }

   public int getTotalScore()
   {
      return scoreCard.getTotalScore();
   }

   // this method change the hold state of a die
   public void holdDie(int dieNumber)
   {
      if(dice[dieNumber].isHeld() == true)
      {
         dice[dieNumber].setHeld(false);
         dice[dieNumber].setDieImage(new ImageIcon(getClass().getClassLoader().getResource("die"+dice[dieNumber].getDieFace()+".jpg")));
      }
      else
      {
         dice[dieNumber].setHeld(true);
         dice[dieNumber].setDieImage(new ImageIcon(getClass().getClassLoader().getResource("die"+dice[dieNumber].getDieFace()+"held.jpg")));
      }
   }

   public Die[] getDice()
   {
      return dice;
   }
   
   public void newGame()
   {
      scoreCard = new ScoreCard();
      rounds.removeAllElements();
   }
   
   public void loadGame(File savedGame)
   {
      YahtzeeGame yg;
      try
      {
         FileInputStream fis = new FileInputStream(savedGame);
         
         ObjectInputStream ois = new ObjectInputStream(fis);
         
         try
         {
            yg = (YahtzeeGame) ois.readObject();
            //System.out.println(yg.getUpperScore());
            
            dice = yg.dice;
            roll = yg.roll;
            endOfRound = yg.endOfRound;
            wildCard = yg.wildCard;
            bonus = yg.bonus;
            scoreCard = yg.scoreCard;
            rounds = yg.rounds;
            
            
         }
         catch (ClassNotFoundException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         
         ois.close();
         
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   public void changeRound(int roundNum)
   {
      
      while(roundNum <= rounds.size())
      {
         scoreCard.removeRound(rounds.peek().getCategoryScored(), rounds.peek().getPointsScored());
         rounds.pop();
      }
      roll();
   }
}

