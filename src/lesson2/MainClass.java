package lesson2;

public class MainClass {
    public static void main (String[] args) {
        String str = "1 3 1 2\n2 3 2 2\n5 6 7 1\n3 3 1 0";
        System.out.println(convertStrToArr (str));

    }

    private static int convertStrToArr (String str) {
        String[][] strArr = new String[4][4];
        int i = 0;
        int j = 0;
        int result = 0;
        for (String s1: str.split("\n")){
            for (String s2:s1.split(" ")) {
                strArr[i][j] = s2;
                j++;
            }
            i++;
            j = 0;
        }


       /* String[] words = str.split("\n ");
        //String[] strArr;
        System.out.println(words[0]);
       /* int j = 0;
        for (String s:words) {
            System.out.println(s);
            //strArr[j] = s.split(" ");
            String[] w = s.split(" ");
            System.out.println(w);
           // System.out.println("s - " + s.split(" "));
            j++;
        }*/

       for (i = 0; i < strArr.length; i++) {
            for (j = 0; j < strArr[i].length; j++) {
                result = result + Integer.parseInt(strArr[i][j]);
            }
           //System.out.println();
        }

        //System.out.println(strArr[2][3]);
        return result / 2;

    }
}
