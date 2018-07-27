package lesson1;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {

    private MainWindow mainWindow;
    private long lastFrameTime;
    private long lastFrameTimeColor;

    GameCanvas(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        lastFrameTime = System.nanoTime(); //сняли штамп времени
        lastFrameTimeColor = System.nanoTime(); //для смены цвета фона
        setBackground(BackgroundColor.setColorBackground());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        long currentTime = System.nanoTime(); //Текущее штамп времени
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f; //разница времени, перевод в сек
        float deltaTimeColor = (currentTime - lastFrameTimeColor) * 0.000000001f; //разница времени, перевод в сек
        lastFrameTime = currentTime;

        // friday magic
        try { //Обработка исключения
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mainWindow.onDrawFrame(this, g, deltaTime);  //обновить и перерисовать

        //Цвет фона, каждые 3 сек +-
        if (deltaTimeColor > 3) {
            setColorBackground();
            lastFrameTimeColor = currentTime;
        }

        repaint();
    }

    private void setColorBackground() {
       setBackground(BackgroundColor.setColorBackground());
    }

    public int getLeft() { return 0; }
    public int getRight() { return getWidth() - 1; }
    public int getTop() { return 0; }
    public int getBottom() { return getHeight() - 1; }
}