

// This child of Straight is specific for a large straight
public class LargeStraight
   extends Straight
{
   public LargeStraight()
   {
      straight = 5;
      upperOrLower = lower;
   }
   
   @Override
   public void setScore(Die[] dice, boolean wildCard)
   {
      if(isScorable(dice, wildCard))
      {
         this.score = 40;
      }
      else
      {
         setZeroScore();
      }
   }
   
}
