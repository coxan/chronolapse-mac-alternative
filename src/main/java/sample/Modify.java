package sample;

import java.io.File;

/**
 * Created by corto on 2017/05/29.
 */
public class Modify {
    static File src;
    static File out;
    static boolean rename;
    static boolean resize;
    static int resizeWidth;
    static int resizeHeight;

    public static void convert() {

    }

    void init() {
        src = Settings.modifySrc;
        out = Settings.modifyOut;
        rename = Settings.modifyRename;
        resize = Settings.modifyResize;
        resizeWidth = Settings.modifyResizeWidth;
        resizeHeight = Settings.modifyResizeHeight;
    }
}
