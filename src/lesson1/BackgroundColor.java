package lesson1;

import java.awt.*;

public class BackgroundColor extends Sprite {
    private float time;
    private Color color;
    private int count = 0;

    @Override
    void update(GameCanvas canvas, float deltaTime) {
        count += 1;
        if (count > 50 ){
            color = new Color(  //цвет RGB
                    (int) (Math.random() * 255),
                    (int) (Math.random() * 255),
                    (int) (Math.random() * 255));
            count = 0;
        }
    }

    @Override
    void render(GameCanvas canvas, Graphics g) {
        canvas.setBackground(color);

    }
}
