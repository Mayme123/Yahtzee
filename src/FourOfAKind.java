

// A child of MatchingCategory, this looks for a match of four
public class FourOfAKind
   extends MatchingCategory
{

   public FourOfAKind()
   {
      matchNumber = 4;
      score = 0;
      upperOrLower = lower;
   }

   @Override
   public void setScore(Die[] dice, boolean wildCard)
   {
      if(isScorable(dice, wildCard))
      {
         for(Die element: dice)
         {
            score += element.getDieFace();
         }
      }
      else
      {
         setZeroScore();
      }
   }


}
