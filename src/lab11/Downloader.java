//package lab11;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;
//
//static class Downloader implements Runnable{
//    private final String url;
//
//    Downloader(String url){
//        this.url = url;
//    }
//
//    public void run(){
//        String fileName = //nazwa pliku z url
//
//        try(InputStream in = new URL(url).openStream(); FileOutputStream out = new FileOutputStream(fileName) ){
//            for(;;){
//                // czytaj znak z in
//                // jeśli <0 break
//                //zapisz znak do out
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Done:"+fileName);
//    }
//
//}