package lab11;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;

import static lab11.DownloadExample.toDownload;

static class Downloader implements Runnable{
    private final String url;

    Downloader(String url){
        this.url = url;
    }

    public void run(){
        String fileName = //nazwa pliku z url

        try(InputStream in = new URL(url).openStream(); FileOutputStream out = new FileOutputStream(fileName) ){
            for(;;){
                // czytaj znak z in
                // jeśli <0 break
                //zapisz znak do out
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done:"+fileName);
    }

    static void sequentialDownload(){
        double t1 = System.nanoTime()/1e6;
        for(String url:toDownload){
            new Downloader(url).run();
        }
        double t2 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);
    }

    static void concurrentDownload(){
        double t1 = System.nanoTime()/1e6;
        for(String url:toDownload){
            // uruchom Downloader jako wątek...
        }
        double t2 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);
    }

}