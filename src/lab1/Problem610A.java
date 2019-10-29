package lab1;

import java.util.Scanner;

public class Problem610A {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int n2 = n / 2;
        int[] tab = new int[4];
        for (int i = 1; i <= n2 / 2; i++) {

            tab[0] = i;
            tab[1] = i;
            tab[2] = n2 - i;
            tab[3] = n2 - i;
            if (tab[0] == tab[2]){
                continue;
            }
            for (int ii = 0; ii < 4; ii++){
                System.out.print(tab[ii] + " ");

            }
            System.out.println();
        }
    }
}
