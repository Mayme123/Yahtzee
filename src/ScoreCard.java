import java.io.Serializable;
import java.util.HashMap;

// This class is a model level class that represents the score card for the yahtzee game
public class ScoreCard implements Serializable
{
   Ones ones;
   Twos twos;
   Threes threes;
   Fours fours;
   Fives fives;
   Sixes sixes;
   
   ThreeOfAKind threeOfAKind;
   FourOfAKind fourOfAKind;
   FullHouse fullHouse;
   SmallStraight smallStraight;
   LargeStraight largeStraight;
   YahtzeeCategory yahtzee;
   Chance chance;
   
   HashMap<Categories, ScoreCategory> categoryMap;
   
   private int upperScore;
   private int lowerScore;
   private int totalScore;
   
   private boolean wildCard;

   public ScoreCard()
   {
      ones = new Ones();
      twos = new Twos();
      threes = new Threes();
      fours = new Fours();
      fives = new Fives();
      sixes = new Sixes();
      
      threeOfAKind = new ThreeOfAKind();
      fourOfAKind = new FourOfAKind();
      fullHouse = new FullHouse();
      smallStraight = new SmallStraight();
      largeStraight = new LargeStraight();
      yahtzee = new YahtzeeCategory();
      chance = new Chance();
      
      categoryMap = new HashMap<Categories, ScoreCategory>();
      categoryMap.put(Categories.ONES, ones);
      categoryMap.put(Categories.TWOS, twos);
      categoryMap.put(Categories.THREES, threes);
      categoryMap.put(Categories.FOURS, fours);
      categoryMap.put(Categories.FIVES, fives);
      categoryMap.put(Categories.SIXES, sixes);
      categoryMap.put(Categories.THREE_OF_A_KIND, threeOfAKind);
      categoryMap.put(Categories.FOUR_OF_A_KIND, fourOfAKind);
      categoryMap.put(Categories.FULL_HOUSE, fullHouse);
      categoryMap.put(Categories.SMALL_STRAIGHT, smallStraight);
      categoryMap.put(Categories.LARGE_STRAIGHT, largeStraight);
      categoryMap.put(Categories.YAHTZEE, yahtzee);
      categoryMap.put(Categories.CHANCE , chance);
      
      wildCard = false;
   }
   
   public int getLowerScore()
   {
      return lowerScore;
   }

   public int getUpperScore()
   {
      return upperScore;
   }

   public int getTotalScore()
   {
      return totalScore;
   }
   
   public ScoreCategory getCategory(Categories category)
   {
      
      return categoryMap.get(category);
   }
   
   public boolean scoreRound(Die[] dice, Categories chosenCategory, boolean hasWildCard)
   {
      boolean roundScored = false;
      wildCard = hasWildCard;
      ScoreCategory category = categoryMap.get(chosenCategory);
      
      
      if (!category.isScored())
      {
         category.setScore(dice, wildCard);
         category.setScored(true);
         if (category.getUpperOrLower().equals("UPPER"))
         {
            addToUpperScore(category.getScore());
         }
         else if (category.getUpperOrLower().equals("LOWER"))
         {
            addToLowerScore(category.getScore());
         }
         totalScore = upperScore + lowerScore;
         roundScored = true;
      }

      wildCard = false;
      return roundScored;
   }

   public int scoreYahtzee(Die[] dice)
   {
      if (yahtzee.getScored() < 4)
      {
         yahtzee.setTimesScored(yahtzee.getScored()+1);
         yahtzee.setScore(dice, wildCard);
         addToLowerScore(yahtzee.getScore());
      }
      else
      {
         //put message dialog to score another category here
      }
      
      totalScore = upperScore + lowerScore;
      
      return yahtzee.getScored();
   }
   
   public void takeZero(Categories category)
   {
         categoryMap.get(category).setZeroScore();
         categoryMap.get(category).setScored(true);
         yahtzee.addZeroCategories(category);
   }
   
   public void removeRound(Categories removedCategory, int score)
   {
      ScoreCategory category = categoryMap.get(removedCategory);
      
      //Check if round category was a yahtzee
      if (!(category.equals(yahtzee)))
      {
         category.setZeroScore();
         category.setScored(false);
      }
      else//set special removal rules for a round that scored a yahtzee
      {
         //If a bonus chip was used, remove last category that the player took a zero in
         if(yahtzee.getScored() < 5 && yahtzee.getScored() > 1)
         {
            category = categoryMap.get(yahtzee.getLastZeroCategory()); //use the category variable temporarily
            category.setZeroScore();
            category.setScored(false);
            category = categoryMap.get(removedCategory); //assign it back to original category
         }
         yahtzee.setTimesScored(yahtzee.getScored() - 1);
         if(yahtzee.getScored()<1)
         {
            yahtzee.setZeroScore();
         }
         
         
      }
      
      if(category.getUpperOrLower().equals("UPPER"))
      {
        upperScore -= score; 
      }
      else
      {
         lowerScore -= score;
      }
      
      setTotalScore(upperScore + lowerScore);
   }
   
   public void addToUpperScore(int points)
   {
      this.upperScore += points;
   }

   public void addToLowerScore(int points)
   {
      this.lowerScore += points;
   }

   public void setTotalScore(int totalScore)
   {
      this.totalScore = totalScore;
   }
}
