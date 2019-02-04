//package com.oliverstudio.asynctaskdemo;
//
//import android.os.AsyncTask;
//import android.util.Log;
//
//import java.util.concurrent.TimeUnit;
//
//public class InternetCheck extends AsyncTask<Void, Void, Boolean> {
//
//    private Consumer mConsumer;
//
//    public interface Consumer {
//        void accept(Boolean internet);
//    }
//
//    InternetCheck(Consumer consumer) {
//        mConsumer = consumer;
//        execute();
//    }
//
//    @Override
//    protected Boolean doInBackground(Void... voids) {
////        try {
////            Socket sock = new Socket();
////            sock.connect(new InetSocketAddress("8.8.8.8", 53), 1500);
////            sock.close();
////            return true;
////        } catch (IOException e) {
////            return false;
////        }
//        for (int i = 0; i < 5; i++) {
//            try {
//                TimeUnit.SECONDS.sleep(1);
//                Log.d("devptag", "doInBackground: " + i);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        return true;
//    }
//
//    @Override
//    protected void onPostExecute(Boolean internet) {
//        mConsumer.accept(internet);
//    }
//}