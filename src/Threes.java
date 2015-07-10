
public class Threes
   extends ScoreCategory
{

   public Threes()
   {
      upperOrLower = upper;
   }
   
   @Override
   public void setScore(Die[] dice, boolean wildCard)
   {
      for(Die element: dice)
      {
         if(element.getDieFace() == 3)
         {
            this.score += 3;
         }
      }

   }

}
