package com.bit.telebot.game;

import com.bit.telebot.BotHandler;
import org.telegram.telegrambots.api.objects.Update;

public class Casino {
    /*
    have the following commands:

    /roll
    /spin
    /give user amount

     */

    public static void check(BotHandler handler, String input){
        if(input.equalsIgnoreCase("/roll")) {
            handler.sendMessage("rolling...");
        } else if(input.equalsIgnoreCase("/spin")){
            handler.sendMessage("spinning...");
        } else if(input.startsWith("/give")){
            handler.sendMessage("giving...");
        }
    }
}
