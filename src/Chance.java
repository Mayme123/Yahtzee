


public class Chance
   extends ScoreCategory
{

   public Chance()
   {
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
