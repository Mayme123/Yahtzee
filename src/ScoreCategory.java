import java.io.Serializable;

// This will be used as a template to make all of the score categories. Every category will have a point value, a flag to check if it is scored in already.
// and a rule method to be overridden.

public abstract class ScoreCategory  implements Serializable
{

   protected int score;
   
   protected boolean isScored;

   protected final String upper = "UPPER";
   
   protected final String lower = "LOWER";
   
   protected String upperOrLower;
   
   public String getUpperOrLower()
   {
      if(upperOrLower.equals(upper))
      {
         return upper;
      }
      else if(upperOrLower.equals(lower))
      {
         return lower;
      }
      else
      {
         return null;
      }
      
   }

   //public abstract boolean isScorable(Die[] dice, boolean wildCard);
   public boolean isScorable(Die[] dice, boolean wildCard)
   {
      if(!isScored)
      {
         return true;
      }
      else
      {
         return false;
      }
   }
   
   public abstract void setScore(Die[] dice, boolean wildCard); 
   
   public void setZeroScore()
   {
      this.score = 0;
   }

   public int getScore()
   {
      return score;
   }

   public boolean isScored()
   {
      return isScored;
   }

   public void setScored(boolean isScored)
   {
      this.isScored = isScored;
   }

   
}
