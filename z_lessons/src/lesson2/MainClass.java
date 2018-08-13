package lesson2;

public class MainClass {
    public static void main (String[] args) {
        String str = "1 3 1 2\n2 3 2 2\n5 6 7 1\n3 3 1 0"; // верная строка
        //String str = "1 3 1 2\n2 3 2\n5 6 7 1\n3 3 1 0"; // не верная строка, не хватает числа в 2
        //String str = "1 3 1 2\n2 3 2 2\n5 6 7 1\n3 3 1 о"; // не верная строка, вместо 0, буква о
        //String str = "2 3 2\n5 6 7 1\n3 3 j"; // не верная строка, не хватает чисел и  строки целой

        try {
            System.out.println("Результат - " + convertStrToArr(str));
        } catch(NumberFormatException e){  //почему он раньше следующего исключения должен быть? Если поменять местами валится с ошибкой
            System.out.println("В строке вместо чисел, подан неизвестный символ");
            e.printStackTrace();
        } catch(IllegalArgumentException e){
            System.out.println("Не верная входная строка.");
            e.printStackTrace();
        };
    }

    private static int convertStrToArr (String str) throws IllegalArgumentException, NumberFormatException {
        String[][] strArr = new String[4][4];
        int i = 0;
        int j = -1;
        //а если дробное?
        int result = 0;
        //а если не использовать foreach то и не надо было бы выдумывать счётчики
        for (String s1: str.split("\n")){
            for (String s2:s1.split(" ")) {
                ++j;
                strArr[i][j] = s2;
            }
            //эту проверку лучше осуществлять до того как начинаете разбирать посимвольно
            if (j<3) { //не хватает столбца
                //try {
                    throw new IllegalArgumentException();
                /*}catch (IllegalArgumentException e) {
                    //e.printStackTrace();
                    for (int c = j+1; c < 4; c++) {
                        strArr[i][c] = "0";
                    }
                }*/
            }
            i++;
            j=-1;
        }
        //думаю это было бы проще проверить с самого начала, ещё не начав разбирать строку на столбцы
        if (i<4) { //не хватает строк
            //try {
                throw new IllegalArgumentException();
            /*}catch (IllegalArgumentException e) {
                for (int c = i; c < 4; c++) {
                    for (j=0; j<4; j++) {
                        strArr[c][j] = "0";
                    }
                }
                //e.printStackTrace();
            }*/
        }
        //такие разнородные действия лучше разделять, потому что мы никогда не можем знать, какое из них понадобится отдельно от другого
       for (i = 0; i < strArr.length; i++) {
            for (j = 0; j < strArr[i].length; j++) {
                //try {
                    result = result + Integer.parseInt(strArr[i][j]);
                /*}/*catch (NumberFormatException e) {
                    System.out.println("В строке была буква");
                    //e.printStackTrace();
                }*/

            }
        }
        return result / 2;
    }
}
