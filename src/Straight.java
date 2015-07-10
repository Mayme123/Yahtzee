// This class is a child of Score Category that contains methods to check for straights
import java.util.Arrays;

public abstract class Straight
   extends ScoreCategory
{
   protected int straight;
   
   public abstract void setScore(Die[] dice, boolean wildCard);
   
   @Override
   public boolean isScorable(Die[] dice, boolean wildCard)
   {  
      if (!wildCard)
      {
         int[] dieFaces = new int[5];
         for (int i = 0; i < dieFaces.length; ++i)
         {
            dieFaces[i] = dice[i].getDieFace();
         }
         if (straightCount(dieFaces) >= straight)
         {
            return true;
         }
         else
         {
            return false;
         }
      }
      else
      {
         return true;
      }
   }
   
   protected int straightCount(int dieNumbers[])
   {
      int inARow = 1;
      Arrays.sort(dieNumbers);
      
      if(dieNumbers[0]<=3)
      {
         for(int i=0; i < dieNumbers.length -1; ++i)
         {
            
            if(dieNumbers[i+1] == dieNumbers[i]+1)
            {
               inARow +=1;
            }
         }
      }
      
      return inARow;
   }
   
   

}
