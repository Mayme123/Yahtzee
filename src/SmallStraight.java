

// This child of Straight is specific for a small straight
public class SmallStraight
   extends Straight
{
   public SmallStraight()
   {
      straight = 4;
      upperOrLower = lower;
   }

   @Override
   public void setScore(Die[] dice, boolean wildCard)
   {
      if(isScorable(dice, wildCard))
      {
         this.score = 30;
      }
      else
      {
         setZeroScore();
      }
   }
   
   
}
