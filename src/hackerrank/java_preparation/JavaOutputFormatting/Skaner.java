package hackerrank.java_preparation.JavaOutputFormatting;

import java.util.Scanner;

public class Skaner {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("================================");
        for(int i=0;i<3;i++){
            String s1=sc.next();
            int x=sc.nextInt();
            //Complete this line
            System.out.printf("%-15s%03d%n", s1, x);
            /*
            * %03d регулирует расстояние между данными котоые выводим в одной строке
            *%n означает перевод строки
            *
            *
            *
            *
            * В предыдущем примере формат был «%4d», где d означает вывод десятичного
             * целого числа, а 4  — означает то, что если количество знаков в числе меньше,
              * чем 4, то оно будет спереди дополнено пробелами на недостающее (до 4-х)
               * количество знаков (тем самым подвинуто вправо).
            *
            *
            * */
        }
        System.out.println("================================");

}
}

