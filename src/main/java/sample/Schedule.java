package sample;

import java.time.chrono.Chronology;
import java.util.Date;

/**
 * Created by corto on 2017/05/29.
 */
public class Schedule {
    Date start;
    Date stop;
    static boolean run;

    void init() {
        start = Settings.scheduleStart;
        stop = Settings.scheduleStop;
        run = Settings.scheduleRun;
    }

    static void setStartTime(Chronology c){

    }

    static void setStopTime(Chronology c){

    }

}
