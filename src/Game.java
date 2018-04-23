import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;

import java.util.HashMap;

public class Game {

    private static class CurrentGame {
        private String current_type_word = "";
        private String current_unscrambled_word = "";
        private String current_scramble_word = "";
        private String current_taboo_word = "";
        private String player_username = "";
        private boolean is_type = false;
        private boolean is_scramble = false;
        private boolean is_taboo = false;
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

        // check here if games are on (not implemented yet)

        // type game win
        if (current != null && current.is_type && message_text_lower.equals(current.current_type_word)) {
            String username = message_sender.getUserName();
            Database.getInstance().incrementTypeScore(username);
            current.is_type = false;
            current.current_type_word = "";
            handler.sendMessage(username + " has won!", message.getChatId());
        }

        // scramble game win
        if (current != null && current.is_scramble && message_text_lower.equals(current.current_unscrambled_word)) {
            String username = message_sender.getUserName();
            Database.getInstance().incrementScrambleScore(username);
            current.is_scramble = false;
            current.current_scramble_word = "";
            current.current_unscrambled_word = "";
            handler.sendMessage(username + " has won!", message.getChatId());
        }

        // taboo game cheat
        if (current != null && current.is_taboo && message_text_lower.contains(current.current_taboo_word)
                && message_sender.getUserName().equals(current.player_username)) {
            String username = message_sender.getUserName();
            handler.sendMessage(username + " is a cheater!\n\n" +
                    current.current_taboo_word + " was the word.", message.getChatId());
            current.is_taboo = false;
            current.current_taboo_word = "";
        }

        // taboo game win
        else if (current != null && current.is_taboo && message_text_lower.contains(current.current_taboo_word)) {
            String username = message_sender.getUserName();
            Database.getInstance().incrementTabooScore(username);
            current.is_taboo = false;
            current.current_taboo_word = "";
            handler.sendMessage(username + " has won!", message.getChatId());
        }

        // check if trying to start a game
        String bot_say_this = "";

        switch (message_text_lower) {
            case "/type":

                if (currentGames.containsKey(chat_id)) {
                    CurrentGame game = currentGames.get(chat_id);
                    if (game.current_type_word.isEmpty()) {
                        game.current_type_word = dictionary.getRandomWord();
                        game.is_type = true;
                    }

                    bot_say_this = "Type: " + currentGames.get(chat_id).current_type_word;
                } else {
                    CurrentGame game = new CurrentGame();
                    game.current_type_word = dictionary.getRandomWord();
                    game.is_type = true;
                    currentGames.put(chat_id, game);

                    bot_say_this = "Type: " + game.current_type_word;
                }

                break;
            case "/scramble":
                if (currentGames.containsKey(chat_id)) {
                    CurrentGame game = currentGames.get(chat_id);
                    if (game.current_scramble_word.isEmpty()) {
                        game.current_unscrambled_word = dictionary.getRandomWord();
                        game.current_scramble_word = dictionary.scrambleWord(game.current_unscrambled_word);
                        game.is_scramble = true;
                    }

                    bot_say_this = "Unscramble: " + game.current_scramble_word;
                } else {
                    CurrentGame game = new CurrentGame();
                    game.current_unscrambled_word = dictionary.getRandomWord();
                    game.current_scramble_word = dictionary.scrambleWord(game.current_unscrambled_word);
                    game.is_scramble = true;
                    currentGames.put(chat_id, game);

                    bot_say_this = "Unscramble: " + game.current_scramble_word;
                }
                break;
            case "/taboo":
                if (!message.getChat().isUserChat()) {
                    if (!currentGames.containsKey(chat_id)) {
                        currentGames.put(chat_id, new CurrentGame());
                    }
                    CurrentGame game = currentGames.get(chat_id);
                    if (!game.current_taboo_word.isEmpty()) {
                        //sendTextMessage(chat_id, game.player_username + " has the word.", message);
                    } else {
                        game.current_taboo_word = dictionary.getRandomWord();
                        game.player_username = message_sender.getUserName();
                        game.is_taboo = true;
                        handler.sendMessage("Word is " + game.current_taboo_word, message_sender.getId());
                        bot_say_this = ("Word sent to " + message_sender.getUserName());
                    }
                } else {
                    bot_say_this = "Taboo must be played in group chats.";
                }
                break;
            case "/brick":
                // implement brick

                break;
        }
        if (!bot_say_this.isEmpty())
            handler.sendMessage(bot_say_this);

        // trying to get scores
        if(message_text_lower.equals("/scores")){
            Database d = Database.getInstance();
            String t = "Type: "+d.getTypeScore(message_sender.getUserName())+
                    "\nScramble: "+d.getScrambleScore(message_sender.getUserName())+
                    "\nTaboo: "+d.getTabooScore(message_sender.getUserName());
            handler.sendMessage(t);
        }
    }
}
