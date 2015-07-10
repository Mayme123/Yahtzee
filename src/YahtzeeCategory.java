import java.io.Serializable;
import java.util.Stack;



// A child of MatchingCategory, this looks for a match of five
public class YahtzeeCategory
   extends MatchingCategory implements Serializable
{
   private int timesScored;
   private Stack<Categories> zeroCategories;
   
   public YahtzeeCategory()
   {
      matchNumber = 5;
      timesScored = 0;
      upperOrLower = lower;
      zeroCategories = new Stack<Categories>();
   }
   
   //return category based on how many times yahtzee has been scored
   public Categories getLastZeroCategory()
   {
      return this.zeroCategories.pop();
   }

   public void addZeroCategories(Categories zeroCategory)
   {
      this.zeroCategories.push(zeroCategory);
   }

   @Override
   public void setScore(Die[] dice, boolean wildCard)
   {
      if(this.getScored() < 2)
      {
         score = 50;
      }
      else if(!wildCard)
      {
         score = 100;
         
      }
      
   }
   
   public void setTimesScored(int scored)
   {
      timesScored = scored;
   }
   
   public int getScored()
   {
      return this.timesScored;
   }
   
}
