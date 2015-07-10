
public class Fours
   extends ScoreCategory
{

   
   public Fours()
   {
      upperOrLower = upper;
   }

   @Override
   public void setScore(Die[] dice, boolean wildCard)
   {
      for(Die element: dice)
      {
         if(element.getDieFace() == 4)
         {
            this.score += 4;
         }
      }

   }

}
