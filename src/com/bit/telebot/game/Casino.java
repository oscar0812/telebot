package com.bit.telebot.game;

import com.bit.telebot.BotHandler;
import com.bit.telebot.Database;
import org.telegram.telegrambots.api.objects.Message;

import java.util.Random;

public class Casino {
    /*
    have the following commands:

    /roll
    /spin
    /give user amount

     */
    public static void check(BotHandler handler, Message message){
        int coins;
        if(message.getText().equalsIgnoreCase("/coins")){
            handler.sendReplyMessage("You have "+Database.getInstance().getCasinoScore(message.getFrom().getUserName())+" coins");
        }
        else if(message.getText().equalsIgnoreCase("/roll")) {

            handler.sendMessage("rolling...");
            handler.sendMessage("LANDED ON 6");
            handler.sendMessage(message.getFrom().getUserName()+ " has won 1,000,000 coins!");
        } else if(message.getText().equalsIgnoreCase("/spin")){
            // random from 0 to 100
            coins = new Random().nextInt(100);
            Database.getInstance().addToCasino(message.getFrom().getUserName(), coins);
            handler.sendReplyMessage("Congrats! You got "+coins+" coins");

        } else if(message.getText().startsWith("/give")){
            handler.sendMessage("giving...");
        }
    }
}
