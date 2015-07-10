
public class Fives
   extends ScoreCategory
{

   public Fives()
   {
      upperOrLower = upper;
   }
   
   @Override
   public void setScore(Die[] dice, boolean wildCard)
   {
      for(Die element: dice)
      {
         if(element.getDieFace() == 5)
         {
            this.score += 5;
         }
      }

   }

}
