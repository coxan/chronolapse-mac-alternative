package sample;

import java.io.File;

/**
 * Created by corto on 2017/05/14.
 */
public class Settings {
    static File ssPath;
    static File videoPath;
    private File settingsFile;
    static boolean timelapse;
    static boolean startupLaunch;
    static boolean startupCapture;

    public Settings(){
        settingsFile = new File("settings");

    }

    public static void enableSS(Boolean newValue) {
    }
}
