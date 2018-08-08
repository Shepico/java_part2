package lesson5;

import java.util.Arrays;

public class L5_MainClass {

    private static int SIZE = 10000000;
    private static int H = SIZE / 2;
    private float[] arr;

    L5_MainClass() {
        this.arr = new float[SIZE];  // создали
        this.fillArr(1); //заполнили
    }

    public static void main (String[] args) {
        L5_MainClass one = new L5_MainClass();
        one.oneMetod();  //Я так понимаю в одном потоке, по умолчанию
        //
        L5_MainClass two = new L5_MainClass();
        two.twoMetod();
        //
        /*System.out.println(one.arr[9000000]);
        System.out.println(two.arr[9000000]);*/
        System.out.println("Compare arrays: " + one.compareArr(two.arr)); //сравнение массивов

    }

    class OneMetod  implements Runnable{

        @Override
        public void run() {
            arr = new float[SIZE];
        }
    }

    void oneMetod() {
        long a = System.currentTimeMillis();
        for (int i=0; i<this.arr.length; i++) {
            this.arr[i] = (float)(this.arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long b = System.currentTimeMillis();
        System.out.println("Lead time(mS) ONE = " + (b - a));
    }

    void twoMetod() {
        float[] arr1 = new float[H];
        float[] arr2 = new float[H];
        long a = System.currentTimeMillis();
        //разбить
        System.arraycopy(this.arr,0, arr1,0, H);
        System.arraycopy(this.arr,0, arr2,0, H);

        //System.out.println(this.arr.length);
        Thread t1 = new Thread(new Runnable(){

            @Override
            public void run() {
                for (int i=0; i<arr1.length; i++) {
                    arr1[i] = (float)(arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i=5000000;
                for (int j=0; j<arr2.length; j++, i++) {
                    arr2[j] = (float)(arr2[j] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        t2.start();


        //
        //System.out.println(Arrays.equals(arr1, arr2));

       //склеить
        try {
            t2.join();
            System.arraycopy(arr1,0, this.arr,0, H);
            System.arraycopy(arr2,0,this.arr, H, H);
        } catch (InterruptedException e){
            System.out.println("Первый поток прерван");
        }
        //System.arraycopy(arr1,0, this.arr,0, H);
        //System.arraycopy(arr2,0,this.arr, H, H);
        //System.out.println(this.arr.length);
        //
        long b = System.currentTimeMillis();
        System.out.println("Lead time(mS) TWO = " + (b - a));


    }
///////////////////////////////////////////////////////////////////////////////////
    private void fillArr(int num){
        for (int i=0; i<this.arr.length; i++) {
            this.arr[i] = num;
        }
    }

    private boolean compareArr(float[] arr) {
        return Arrays.equals(this.arr, arr);
    }


}
