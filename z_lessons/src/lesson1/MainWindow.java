package lesson1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainWindow extends JFrame {
    private static final int POS_X = 300; //Начальная координата Х
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

        add(gameCanvas, BorderLayout.CENTER);
        initGame();
        addMouseListener(new MyMouseListener()); //слушатель
        setVisible(true);  //показали окно
    }

    private void initGame() {
        sprites[0] = new BackgroundColor();
        for (int i = 1; i < sprites.length; i++) {
            sprites[i] = new Ball(); //создаем  мячи
        }
    }

    private void addBall() {
        int i;
        Sprite[] spritesNew = new Sprite[sprites.length+1];
        for (i = 0; i< sprites.length; i++) {
            spritesNew[i] = sprites[i];
        }
        spritesNew[i] = new Ball();
        sprites = spritesNew;
    }

    private void removeBall() {
        int i;
        if (sprites.length != 1) {
            Sprite[] spritesNew = new Sprite[sprites.length - 1];
            for (i = 0; i < spritesNew.length; i++) {
                spritesNew[i] = sprites[i];
            }

            sprites = spritesNew;
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

    class MyMouseListener implements java.awt.event.MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getButton() == MouseEvent.BUTTON1) { //левая
                //System.out.println("click left");
                addBall();
            }else {
                //System.out.println("click right");
                removeBall();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}

// Background
//Ctrl + v