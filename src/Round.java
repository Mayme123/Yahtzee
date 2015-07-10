import java.io.Serializable;

//This class will keep track of how many points were scored in a round, what category was scored, and which turn number it was
public class Round implements Serializable
{
   private int roundNum;
   private ScoreCategory categoryScored;
   private int pointsScored;
   
   public  Round(int round, ScoreCategory category)
   {
      roundNum = round;
      categoryScored = category;
      pointsScored = category.getScore();
   }

   public int getRoundNum()
   {
      return roundNum;
   }

   public void setRoundNum(int roundNum)
   {
      this.roundNum = roundNum;
   }

   public Categories getCategoryScored()
   {  
      Categories category;
      String categoryString = this.categoryScored.toString();
      categoryString = categoryString.substring(0, categoryString.indexOf('@'));
     
      switch(categoryString)
      {
         case "Ones":
            category = Categories.ONES;
            break;
         case "Twos":
            category = Categories.TWOS;
            break;
         case "Threes":
            category = Categories.THREES;
            break;
         case "Fours":
            category = Categories.FOURS;
            break;
         case "Fives":
            category = Categories.FIVES;
            break;
         case "Sixes":
            category = Categories.SIXES;
            break;
         case "ThreeOfAKind":
            category = Categories.THREE_OF_A_KIND;
            break;
         case "FourOfAKind":
            category = Categories.FOUR_OF_A_KIND;
            break;
         case "FullHouse":
            category = Categories.FULL_HOUSE;
            break;
         case "SmallStraight":
            category = Categories.SMALL_STRAIGHT;
            break;
         case "LargeStraight":
            category = Categories.LARGE_STRAIGHT;
            break;
         case "Chance":
            category = Categories.CHANCE;
            break;
         case "YahtzeeCategory":
            category = Categories.YAHTZEE;
            break;
         default:
            category = null;
      }
      
      return category;
   }

   public void setCategoryScored(ScoreCategory categoryScored)
   {
      this.categoryScored = categoryScored;
   }

   public int getPointsScored()
   {
      return pointsScored;
   }

   public void setPointsScored(int pointsScored)
   {
      this.pointsScored = pointsScored;
   }

}
