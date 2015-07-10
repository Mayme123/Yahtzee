// This class is a child of ScoreCategory that checks an array of five dice for different numbers of matches
public abstract class MatchingCategory
   extends ScoreCategory
{
   //will be altered by child classes to the right match number 
   protected int matchNumber;
   
   @Override
   public boolean isScorable(Die[] dice, boolean wildCard)
   {
      if (!wildCard)
      {
         // This array will keep track of any matches. The index will coordinate with the
         // number on the die face minus 1
         int[] matches = new int[6];
         boolean isMatch = false;
         //loop through the dieNumbers array and keep count of any matches 
         // by adding to the matches array
         for (int i = 0; i < dice.length; ++i)
         {
            ++matches[dice[i].getDieFace() - 1];
         }
         for (int i = 0; i < matches.length; ++i)
         {
            if (matches[i] >= matchNumber)
            {
               isMatch = true;
            }
         }
         if (isMatch && (!isScored))
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
   
   public abstract void setScore(Die[] dice, boolean wildCard);
   
}
