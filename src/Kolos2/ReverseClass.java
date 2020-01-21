package Kolos2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ReverseClass {
    static int[] toReverse = new int[100000000];
    static Semaphore sem = new Semaphore(0);


    public static void main(String[] args) {
        fill();
        parallelReverse(5);
    }

    static void fill() {
        for (int i = 0; i < 100000000; i++) {
            toReverse[i] = i;
        }
    }

    static class ReverseThread extends Thread {
        private final int start;
        private final int end;
        int[] reversed;


        ReverseThread(int start, int end) {
            this.start = start;
            this.end = end;
            reversed = new int[end - start];
        }

        public void run() {
            for (int i = start; i < end; i++) {
                reversed[i - start] = toReverse[end - i];
            }
            sem.release();
        }
    }

    static void parallelReverse(int cnt) {
        List<ReverseThread> threads = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            threads.add(new ReverseThread(toReverse.length / cnt * i, toReverse.length / cnt * (i + 1)));
        }

        threads.forEach(Thread::start);
        try {
            sem.acquire(cnt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int currentCnt = 0;
        int arrLen = toReverse.length;
        int revLen;
        int ii = 0;
        for (int i = toReverse.length - 1; i >= 0; i--) {
            revLen = threads.get(currentCnt).reversed.length;
            toReverse[i] = threads.get(currentCnt).reversed[ii];
            ii++;
            if (ii == revLen) {
                ii = 0;
                currentCnt++;
            }
        }
        for (int i : toReverse) {
            System.out.println(i);
        }
    }
}
