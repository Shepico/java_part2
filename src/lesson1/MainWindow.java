package lesson1;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private static final int POS_X = 600; //Начальная координата Х
    private static final int POS_Y = 200; //Начальная координата У
    private static final int WINDOW_WIDTH = 800; //ширина окна
    private static final int WINDOW_HEIGHT = 600; //высота окна

    Sprite[] sprites = new Sprite[7];  //создаем спрайты
    //BackgroundColor colorBgr;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow();
            }
        });
    }

    MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  //Окно, при выходе закрывать
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Circles");

        GameCanvas gameCanvas = new GameCanvas(this);
        //colorBgr = new BackgroundColor(gameCanvas);

        add(gameCanvas, BorderLayout.CENTER);
        initGame();
        setVisible(true);  //показали окно
    }

    private void initGame() {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new Ball(); //создаем  мячи
        }
    }

    public void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
        //colorBgr.setColorBackground(canvas); //cлишком часто меняет
    }


    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].render(canvas, g);
        }
    }
}

// Background
//Ctrl + v