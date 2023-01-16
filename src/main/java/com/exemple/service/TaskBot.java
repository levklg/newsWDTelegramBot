package com.exemple.service;

import com.exemple.Bot;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class TaskBot {
    private boolean newDay = false;

   public void startTask(int period, Bot bot) {

       Timer timer = new Timer();
       TimerTask task = new TimerTask() {

           @Override
           public void run() {

               String nowTimeHH = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH"));
               String nowTimeMM = LocalDateTime.now().format(DateTimeFormatter.ofPattern("mm"));
               //

               if (nowTimeHH.equals("09") && newDay == false) {
                   String nowDate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);


                     bot.processCustomers();

                   newDay = true;

               }
               //
               if (!nowTimeHH.equals("09")) {
                   newDay = false;
               }

           }

       };

       timer.schedule(task, 0, period * 1000);

    }


}
