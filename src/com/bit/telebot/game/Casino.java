package com.bit.telebot.game;

import com.bit.telebot.BotHandler;
import com.bit.telebot.Database;
import org.telegram.telegrambots.api.objects.Message;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Random;

public class Casino {
    /*
    have the following commands:

    /roll
    /spin
    /give user amount

     */

    // string = username, Long = last time they spinned
    private static HashMap<String, Long> currentPlayers = new HashMap<>();

    public static void check(BotHandler handler, Message message) {
        String text = message.getText();
        String username = message.getFrom().getUserName();
        long coins = Database.getInstance().getCasinoScore(username);
        if (text.equalsIgnoreCase("/coins")) {
            handler.sendReplyMessage("You have " + coins + " coins");
        } else if (text.startsWith("/roll") && text.trim().contains(" ")) {
            // roll int
            try {
                int roll_amount = Integer.parseInt(text.substring(text.indexOf(" ")).trim());
                if (roll_amount > coins) {
                    handler.sendReplyMessage("You only have " + coins + " coins");
                    return;
                }
                boolean win = new Random().nextBoolean();
                if (win) {
                    handler.sendReplyMessage("You won " + roll_amount + " coins!");

                } else {
                    handler.sendReplyMessage("You lost " + roll_amount + " coins!");
                    roll_amount = -roll_amount;
                }
                Database.getInstance().addToCasino(username, roll_amount);

            } catch (Exception ignore) {
                System.out.println(ignore);
            }
        } else if (text.equalsIgnoreCase("/spin")) {
            // random from 0 to 100
            if (!currentPlayers.containsKey(username)) {
                // not in hashmap, so hasnt spinned since bot turned on
                spin(handler, message);
            } else {
                // in hashmap, only spin if time difference since last spin is 5 min
                Long last_spin = currentPlayers.get(username);
                Long now = System.currentTimeMillis();
                if (now - last_spin > 300_000) {
                    spin(handler, message);
                } else {
                    handler.sendReplyMessage("You need to wait 5 min to spin again");
                }
            }

        } else if (text.startsWith("/give")) {
            handler.sendMessage("giving...");
        }
    }

    private static void spin(BotHandler handler, Message message) {
        int add_coins = new Random().nextInt(99) + 1;
        Database.getInstance().addToCasino(message.getFrom().getUserName(), add_coins);
        handler.sendReplyMessage("Congrats! You got " + add_coins + " coins");
        currentPlayers.put(message.getFrom().getUserName(), System.currentTimeMillis());
    }
}
