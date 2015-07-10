
public class Twos
   extends ScoreCategory
{

   public Twos()
   {
      upperOrLower = upper;
   }
   
   @Override
   public void setScore(Die[] dice, boolean wildCard)
   {
      for(Die element: dice)
      {
         if(element.getDieFace() == 2)
         {
            this.score += 2;
         }
      }

   }

}
