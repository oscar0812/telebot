package com.bit.telebot;

import com.bit.telebot.text.LanguageDetection;
import com.bit.telebot.text.Lyrics;
import com.bittle.urban.Definition;
import com.bittle.urban.UrbanDictionary;
import com.bit.telebot.game.GameHandler;
import org.javia.arity.MathSolver;
import org.telegram.telegrambots.api.methods.GetUserProfilePhotos;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class BotHandler extends TelegramLongPollingBot {
    private String rmsg, arg, cmd, username;
    private Message message;
    private boolean echo = false;
    static int COUNTER;

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            message = update.getMessage();
            User message_sender = message.getFrom();

            if (message.hasText()) {
                GameHandler.check(this, update);
                rmsg = message.getText();
                username = message.getFrom().getUserName();
                System.out.println(username + ":" + rmsg);

                // COMMAND DOSEN'T TAKE ARGUMENT
                if (rmsg.contains("/") && !rmsg.contains(" ")) {
                    if (rmsg.equalsIgnoreCase("/echo")) {
                        COUNTER++;
                        if (COUNTER == 1) {
                            echo = true;
                            sendMessage("Echo is Enabled!");

                        } else {
                            echo = false;
                            sendMessage("Echo is Disabled!");
                            COUNTER = 0;
                        }
                    }

                    if (rmsg.equalsIgnoreCase("/exit") && Database.getInstance().isDev(message_sender.getUserName())) {
                        sendMessage("Shutting off...");
                        System.exit(0);
                    }
                    if (rmsg.equalsIgnoreCase("/pfp")) {
                        sendPfp(message);
                    }
                }
                // COMMAND TAKES ARGUMENT
                else if (rmsg.contains(" ") && rmsg.startsWith("/")) {
                    cmd = rmsg.split("\\s+")[0];
                    arg = rmsg.substring(rmsg.indexOf(" ")).trim();

                    if (cmd.equalsIgnoreCase("/say")) {
                        sendMessage(arg);
                    } else if (cmd.equalsIgnoreCase("/ud")) {

                        UrbanDictionary dictionary = new UrbanDictionary();
                        if (dictionary.searchWord(arg)) {
                            Definition definition = dictionary.getRandomDefinition();
                            sendMessage(definition.toString());
                        } else {
                            sendMessage("No definition found!");
                        }
                    } else if (cmd.equalsIgnoreCase("/math")) {
                        sendMessage(MathSolver.solve(arg));

                    } else if (cmd.equalsIgnoreCase("/detect")) {
                        String lang = LanguageDetection.detect(arg);
                        sendMessage("Language Detected: " + lang);
                    } else if (!arg.contains(" ") && cmd.equalsIgnoreCase("/admin")
                            && Database.getInstance().isDev(message_sender.getUserName())) {

                        Database.getInstance().addAdmin(arg);
                        sendMessage("Added admin " + arg);
                    } else if (cmd.equalsIgnoreCase("/lyrics") && arg.contains("_")) {
                        String artist = arg.substring(0, arg.indexOf("_"));
                        String song = arg.substring(arg.indexOf("_") + 1);
                        try {
                            String lyrics = Lyrics.getSongLyrics(artist, song).lyrics;
                            if (lyrics.length() > Constants.MAX_MESSAGE_INT) {
                                sendReplyMessage("Message too long, sending to pm");
                                sendSplitMessage(lyrics, message_sender.getId());
                            } else {
                                sendMessage(lyrics);
                            }
                        } catch (Exception e) {
                            sendReplyMessage("No lyrics found");
                        }
                    }

                }
                if (echo && !rmsg.equals("/echo")) {
                    sendMessage(username + ": " + rmsg);
                }

                // ping pong ching chong support
                if (!rmsg.startsWith("/") && rmsg.toLowerCase().endsWith("ing") || rmsg.toLowerCase().endsWith("ong")) {
                    int index = rmsg.toLowerCase().lastIndexOf("ng") - 1;
                    String start = rmsg.substring(0, index);
                    String end = rmsg.substring(index + 1);

                    char mid = rmsg.charAt(index);
                    mid = mid == 'i' ? 'o' : mid == 'I' ? 'O' : mid == 'o' ? 'i' : 'I';
                    //sendMessage();
                    sendMessage(start + mid + end);

                } else if ((rmsg.toLowerCase().startsWith("no ") && rmsg.toLowerCase().endsWith(" u"))
                        || rmsg.toLowerCase().equals("u")) {
                    // message: no u
                    // reply: no no u
                    String scrapedText = rmsg.toLowerCase().replaceAll("\\s+", " ");
                    scrapedText = scrapedText.replaceAll("no ", "");
                    if (scrapedText.equals("u")) {
                        sendMessage(rmsg.toLowerCase().replace("u", " no u").
                                replaceAll("\\s+", " "));
                    }
                } else if (rmsg.matches("(.)\\1+") && !rmsg.isEmpty() && rmsg.startsWith("^")) {
                    // check if string is only made up of one char, if it is
                    // replay with a longer string of the same char
                    sendMessage(rmsg + "" + rmsg.charAt(0));
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return Constants.BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return Constants.BOT_TOKEN;
    }

    public void sendMessage(String args) {
        sendMessage(args, message.getChatId());
    }

    public void sendMessage(String args, long chat_id) {
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(chat_id);
        sendMessageRequest.setText(args);
        try {
            execute(sendMessageRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    // if the message is too long, split it uses high tech algorithm, and send separate message
    public void sendSplitMessage(String message_text, long chat_id) {
        String[] strings = StringUtil.splitString(message_text, Constants.MAX_MESSAGE_INT);

        for (String string : strings) {
            sendMessage(string, chat_id);
        }
    }

    public void sendReplyMessage(String message_text) {
        SendMessage msg = new SendMessage() // Create a message object object
                .setChatId(message.getChat().getId())
                .setText(message_text)
                .setReplyToMessageId(message.getMessageId());
        try {
            execute(msg); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendPhoto(String args) {
        SendPhoto sendPhotoRequest = new SendPhoto();
        sendPhotoRequest.setChatId(message.getChatId().toString());
        sendPhotoRequest.setPhoto(args);

        try {
            sendPhoto(sendPhotoRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendPhoto(String args, String caption) {
        SendPhoto sendPhotoRequest = new SendPhoto();
        sendPhotoRequest.setChatId(message.getChatId().toString());
        sendPhotoRequest.setPhoto(args);
        sendPhotoRequest.setCaption(caption);

        try {
            sendPhoto(sendPhotoRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    void sendPfp(Message message) {
        try {
            GetUserProfilePhotos pfp = new GetUserProfilePhotos();
            pfp.setUserId(message.getFrom().getId());
            pfp.setLimit(4);
            pfp.setOffset(0);

            List<PhotoSize> photos = execute(pfp).getPhotos().listIterator(0).next();

            String f_id = Objects.requireNonNull(photos.stream().max(Comparator.comparing(PhotoSize::getFileSize)).orElse(null)).getFileId();
            sendPhoto(f_id);
        } catch (Exception e) {
            sendReplyMessage("No pfp");
        }
    }
}