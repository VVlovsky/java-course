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
                reversed[i - start] = toReverse[end - i + start - 1];
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

        int count = 0;
        for (int i = cnt -1; i >= 0; i--){
            for (int j = 0; j < threads.get(i).reversed.length; j++){
                toReverse[count] = threads.get(i).reversed[j];
                count++;
            }
        }

    }
}
