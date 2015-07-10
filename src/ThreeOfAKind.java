

// A child of MatchingCategory, this looks for a match of three
public class ThreeOfAKind
   extends MatchingCategory
{
   public ThreeOfAKind()
   {
      matchNumber = 3;
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
