package com.bit.telebot.game;

import com.bit.telebot.BotHandler;
import com.bit.telebot.Database;
import org.telegram.telegrambots.api.objects.Message;

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
    private static Random random = new Random();
    private static final int MINUTE = 60_000;

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
                if (random.nextBoolean()) {
                    // winner, now give them a chance to multiply earning by hitting jackpot
                    if (random.nextInt(10) == 7) {
                        // JACKPOT
                        roll_amount *= random.nextInt(10) + 5;
                        handler.sendReplyMessage("You just hit the jackpot and won " + roll_amount + " coins!");
                    } else
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
                if (now - last_spin > 5 * MINUTE) {
                    spin(handler, message);
                } else {
                    handler.sendReplyMessage("You need to wait " + timeRemaining(last_spin, now) + " to spin again");
                }
            }

        } else if (text.startsWith("/give")) {
            handler.sendMessage("giving...");
        }
    }

    private static void spin(BotHandler handler, Message message) {
        int add_coins = random.nextInt(99) + 1;
        Database.getInstance().addToCasino(message.getFrom().getUserName(), add_coins);
        handler.sendReplyMessage("Congrats! You got " + add_coins + " coins");
        currentPlayers.put(message.getFrom().getUserName(), System.currentTimeMillis());
    }

    private static String timeRemaining(long last_spin, long now) {
        long seconds = (5 * MINUTE - (now - last_spin)) / 1000;
        String str = "";
        if (seconds > 60)
            str += ((int) seconds / 60) + " min ";
        return str + ((seconds % 60) + " seconds ");
    }
}
