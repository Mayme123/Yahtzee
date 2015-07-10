

// This child of ScoreCategory contains methods to check for a full house and record if that category has been scored in
public class FullHouse
   extends ScoreCategory
{
   
   public FullHouse()
   {
      upperOrLower = lower;
   }

   @Override
   public boolean isScorable(Die[] dice, boolean wildCard)
   {
      boolean isMatch;
      
      if (!wildCard)
      {
         // This array will keep track of any matches. The index will coordinate with the
         // number on the die face minus 1
         int[] matches = new int[6];
         // A full house must have a combination of a three of a kind and a pair
         boolean hasPair = false;
         boolean matchOfThree = false;
         isMatch = false;
         //loop through the dieNumbers array and keep count of any matches 
         // by adding to the matches array
         for (int i = 0; i < dice.length; ++i)
         {
            ++matches[dice[i].getDieFace() - 1];
         }
         // check matches array for both a three of a kind and a pair
         for (int i = 0; i < matches.length; ++i)
         {
            if (matches[i] == 2)
            {
               hasPair = true;
            }

            if (matches[i] == 3)
            {
               matchOfThree = true;
            }
         }
         if (hasPair && matchOfThree)
         {
            isMatch = true;
         }
      }
      else
      {
         return true;
      }
      return isMatch;
   }

   @Override
   public void setScore(Die[] dice, boolean wildCard)
   {
      if(isScorable(dice, wildCard))
      {
         this.score = 25;
      }
      else
      {
         setZeroScore();
      }
   }
   
   
   // For testing
   /*public static void main(String[] args)
   {
      FullHouse fh = new FullHouse();
      Die[] dice = new Die[5];
      for(int i = 0; i < dice.length; ++i)
      {
         dice[i] = new Die();
      }
      dice[0].setDieFace(2);
      dice[1].setDieFace(2);
      dice[2].setDieFace(3);
      dice[3].setDieFace(3);
      dice[4].setDieFace(3);
      
      System.out.println(fh.isScorable(dice));
   }
   */
}
