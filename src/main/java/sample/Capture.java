package sample;

import javax.swing.*;

/**
 * Created by coxan on 2017/04/22.
 */
public class Capture implements Runnable{
    static KeyStroke key;
    static int interval;
    static boolean enableSS;
    static boolean enableWC;

    static void setCaptureHotkey(String s){
        key = KeyStroke.getKeyStroke(s);
    }

    void init(){
        key = Settings.captureHotkey;
        interval = Settings.captureInterval;
        enableSS = Settings.captureEnableSS;
        enableWC = Settings.captureEnableWC;
    }

    public void run(){

    }

    static void toggle(){

    }

    static void force() {

    }
}