import java.io.Serializable;
import java.util.Random;

import javax.swing.ImageIcon;

// This a die for the yahtzee game to use. It will keep track of it's hold state and has a number and associated picture icon
public class Die implements Serializable
{
   private int dieFace;
   
   private ImageIcon dieImage;
   
   private boolean isHeld;
   
   //Constructor gets a random integer value and uses it to set the die and die face. Each die starts out unheld
   public Die()
   {
      Random randomNumber = new Random();
      dieFace = randomNumber.nextInt(6)+1;
      
      //set dieImage based on what die face was given
      dieImage = new ImageIcon(getClass().getClassLoader().getResource("die"+dieFace+".jpg")); 
      
      isHeld = false;
   }
   
   public Die roll()
   {
      return new Die();
   }

   public void setDieFace(int dieFace)
   {
      this.dieFace = dieFace;
      //set dieImage based on what die face was given
      this.dieImage = new ImageIcon(getClass().getClassLoader().getResource("die"+dieFace+".jpg")); 
   }

   public boolean isHeld()
   {
      return isHeld;
   }

   public void setDieImage(ImageIcon dieImage)
   {
      this.dieImage = dieImage;
   }

   public void setHeld(boolean isHeld)
   {
      this.isHeld = isHeld;
   }

   public int getDieFace()
   {
      return dieFace;
   }

   public ImageIcon getDieImage()
   {
      return dieImage;
   }
   
   
}
