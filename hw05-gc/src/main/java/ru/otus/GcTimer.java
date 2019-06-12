package ru.otus;

import java.util.TimerTask;

public class GcTimer extends TimerTask {

    @Override
    public void run() {
        CollectorController.printGcStat();
    }
}
