package lesson1;

import java.awt.*;

public class BackgroundColor {
    static private Color color;

    /*BackgroundColor (GameCanvas canva){
        color = new Color(  //цвет RGB
                (int)(Math.random() * 255),
                (int)(Math.random() * 255),
                (int)(Math.random() * 255)
        );
        //canva.setBackground(color);
    }*/

    static Color  setColorBackground(){
        return color = new Color(  //цвет RGB
                (int)(Math.random() * 255),
                (int)(Math.random() * 255),
                (int)(Math.random() * 255)
        );
        //canva.setBackground(color);
    }
}
