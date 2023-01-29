package com.exemple.service;

import com.exemple.model.Bot;
import com.exemple.processor.ParserImp;

import java.util.Timer;
import java.util.TimerTask;


public class TaskBot {
    private boolean newDay = false;

    public void startTask(int period, Bot bot) {

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            ParserImp parserImp = new ParserImp();

            @Override
            public void run() {
                bot.processCustomers();
            }
        };
        timer.schedule(task, 0, period * 1000);

    }


}
