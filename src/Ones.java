
public class Ones
   extends ScoreCategory
{

   public Ones()
   {
      upperOrLower = upper;
   }
   @Override
   public void setScore(Die[] dice, boolean wildCard)
   {
      for(Die element: dice)
      {
         if(element.getDieFace() == 1)
         {
            this.score += 1;
         }
      }

   }

}
