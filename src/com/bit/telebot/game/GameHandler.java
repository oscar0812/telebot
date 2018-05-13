package com.bit.telebot.game;

import com.bit.telebot.BotHandler;
import com.bit.telebot.Database;
import com.bit.telebot.Dictionary;
import com.bit.telebot.StringUtil;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;

import java.util.HashMap;

public class GameHandler {

    private static class CurrentGame {
        private String current_type_word = "";
        private String current_unscrambled_word = "";
        private String current_scramble_word = "";
        private String current_taboo_word = "";
        private String player_username = "";
        private boolean is_type = false;
        private boolean is_scramble = false;
        private boolean is_taboo = false;
        private long type_start = 0;

        private ImageGuess guessGame = null;
    }

    // to play separate games on separate groups
    private static HashMap<Long, CurrentGame> currentGames = new HashMap<>();

    private static final Dictionary dictionary = new Dictionary();

    public static void check(BotHandler handler, Update update) {
        CurrentGame current = null;
        long chat_id = update.getMessage().getChatId();
        if (currentGames.containsKey(chat_id))
            current = currentGames.get(chat_id);

        check(handler, update, current); // check if its a game command
    }

    // check if its a command for a game
    private static void check(BotHandler handler, Update update, CurrentGame current) {

        Message message = update.getMessage();
        long chat_id = message.getChatId();
        String message_text = message.getText();
        message_text = StringUtil.removeCall(message_text);
        String message_text_lower = message_text.toLowerCase();
        User message_sender = message.getFrom();


        // check if its a casino command
        Casino.check(handler, message);

        // check here if games are on (not implemented yet)

        String username = message_sender.getUserName();
        // type game win
        if (current != null && current.is_type && message_text_lower.equals(current.current_type_word)) {
            Database.getInstance().incrementTypeScore(username);
            current.is_type = false;
            current.current_type_word = "";

            handler.sendMessage(username + " has won in " +
                    (System.currentTimeMillis() - current.type_start) + " milliseconds!", message.getChatId());

            current.type_start = 0;
        }

        // scramble game win
        if (current != null && current.is_scramble && message_text_lower.equals(current.current_unscrambled_word)) {
            Database.getInstance().incrementScrambleScore(username);
            current.is_scramble = false;
            current.current_scramble_word = "";
            current.current_unscrambled_word = "";
            handler.sendMessage(username + " has won!", message.getChatId());
        }

        // taboo game cheat
        if (current != null && current.is_taboo && message_text_lower.contains(current.current_taboo_word)
                && message_sender.getUserName().equals(current.player_username)) {
            handler.sendMessage(username + " is a cheater!\n\n" +
                    current.current_taboo_word + " was the word.", message.getChatId());
            current.is_taboo = false;
            current.current_taboo_word = "";
        }

        // taboo game win
        else if (current != null && current.is_taboo && message_text_lower.equals(current.current_taboo_word)) {
            Database.getInstance().incrementTabooScore(username);
            current.is_taboo = false;
            current.current_taboo_word = "";
            handler.sendMessage(username + " has won!", message.getChatId());

            // guess game win
        } else if (current != null && current.guessGame != null && message_text_lower.contains(current.guessGame.getAnswer())) {
            Database.getInstance().incrementGuessScore(username);
            current.guessGame = null;
            handler.sendMessage(username + " has won!", message.getChatId());
        }

        // check if trying to start a game

        CurrentGame game = new CurrentGame();
        if (!currentGames.containsKey(chat_id)) {
            currentGames.put(chat_id, game);
        }
        game = currentGames.get(chat_id);

        if (message_text_lower.equals("/type") || message_text_lower.equals("⌨ type️")) {
            if (game.current_type_word.isEmpty()) {
                game.current_type_word = dictionary.getRandomWord();
                game.is_type = true;
                game.type_start = System.currentTimeMillis();
            }

            handler.sendMessage("Type: " + game.current_type_word);

        } else if (message_text_lower.equals("/scramble") || message_text_lower.equals("🤷‍ scramble")) {
            if (game.current_scramble_word.isEmpty()) {
                game.current_unscrambled_word = dictionary.getRandomWord();
                game.current_scramble_word = dictionary.scrambleWord(game.current_unscrambled_word);
                game.is_scramble = true;
            }

            handler.sendMessage("Unscramble: " + game.current_scramble_word);

        } else if (message_text_lower.equals("/taboo") || message_text_lower.equals("🤔 taboo")) {
            if (!message.getChat().isUserChat()) {

                if (!game.current_taboo_word.isEmpty()) {
                    handler.sendMessage(game.player_username + " has the word.", chat_id);
                } else {
                    game.current_taboo_word = dictionary.getRandomWord();
                    game.player_username = message_sender.getUserName();
                    game.is_taboo = true;
                    // pm the person
                    handler.sendMessage("Word is " + game.current_taboo_word, message_sender.getId());
                    // send notification to group
                    handler.sendMessage("Word sent to " + message_sender.getUserName());
                }
            } else {
                handler.sendMessage("Taboo must be played in group chats.");
            }

        } else if (message_text_lower.equals("/guess") || message_text_lower.equals("🖼 guess")) {
            if (game.guessGame == null) {
                game.guessGame = ImageGuess.random();
            }
            handler.sendPhoto(game.guessGame.getUrl());
        }

        if (message_text_lower.equals("/reset") && Database.getInstance().isAdmin(message_sender.getUserName())) {
            if (currentGames.containsKey(chat_id) && current != null) {
                String all = "";
                if (!current.current_type_word.isEmpty()) {
                    all += "Type: " + current.current_type_word + "\n\n";
                }
                if (!current.current_unscrambled_word.isEmpty()) {
                    all += "Scramble answer: " + current.current_unscrambled_word + "\n\n";
                }
                if (!current.current_taboo_word.isEmpty()) {
                    all += "Taboo answer: " + current.current_taboo_word + "\n\n";
                }
                if (current.guessGame != null) {
                    all += "Guess answer: " + current.guessGame.getAnswer() + "\n\n";
                }

                if (!all.trim().isEmpty())
                    handler.sendMessage("Games cleared\n\n" + all.trim());
                else
                    handler.sendMessage("Nothing to reset.");
                currentGames.remove(chat_id);
            } else {
                handler.sendMessage("Nothing to reset.");

            }

        } else if (message_text_lower.equals("/scores") || message_text_lower.equals("🏆 scores")) {
            // trying to get scores
            Database d = Database.getInstance();
            handler.sendReplyMessage("Type: " + d.getTypeScore(message_sender.getUserName()) +
                    "\nScramble: " + d.getScrambleScore(message_sender.getUserName()) +
                    "\nTaboo: " + d.getTabooScore(message_sender.getUserName()) +
                    "\nGuess: " + d.getGuessScore(message_sender.getUserName()));

        }
    }
}