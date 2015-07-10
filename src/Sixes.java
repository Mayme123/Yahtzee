
public class Sixes
   extends ScoreCategory
{
   
   public Sixes()
   {
      upperOrLower = upper;
   }

   @Override
   public void setScore(Die[] dice, boolean wildCard)
   {
      for(Die element: dice)
      {
         if(element.getDieFace() == 6)
         {
            this.score += 6;
         }
      }

   }

}
