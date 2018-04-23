package com.bit.telebot;

import com.bittle.urban.Definition;
import com.bittle.urban.UrbanDictionary;
import com.bit.telebot.game.GameHandler;
import org.javia.arity.MathSolver;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

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


                //sendPhoto("https://www.google.com/imgres?imgurl=https%3A%2F%2Fimages.pexels.com%2Fphotos%2F67636%2Frose-blue-flower-rose-blooms-67636.jpeg%3Fauto%3Dcompress%26cs%3Dtinysrgb%26h%3D350&imgrefurl=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Fflower%2F&docid=5UbOpOqf9qM23M&tbnid=ZAXhvwKefKr6jM%3A&vet=10ahUKEwjNktOF7c3aAhUEvFkKHVwWA3YQMwivASgBMAE..i&w=528&h=350&bih=620&biw=612&q=images&ved=0ahUKEwjNktOF7c3aAhUEvFkKHVwWA3YQMwivASgBMAE&iact=mrc&uact=8", "Now has captionk");
                // COMMAND DOSEN'T TAKE ARGUMENT
                if (rmsg.contains("/") && !rmsg.contains(" ")) {
                    cmd = message.getText().split("/")[1];

                    if (cmd.equalsIgnoreCase("echo")) {
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

                    } else if(!arg.contains(" ") && cmd.equalsIgnoreCase("/admin")
                            && Database.getInstance().isDev(message_sender.getUserName())){

                        Database.getInstance().addAdmin(arg);
                        sendMessage("Added admin "+arg);
                    }

                }
                if (echo && !rmsg.equals("/echo")) {
                    sendMessage(username + ": " + rmsg);
                }

                // ping pong ching chong support
                if (!rmsg.startsWith("/") && rmsg.toLowerCase().endsWith("ing")) {
                    int index = rmsg.toLowerCase().indexOf("ing");
                    String start = rmsg.substring(0, index);
                    String end = rmsg.substring(index+1);
                    char mid = rmsg.charAt(index) == 'i'?'o':'O';
                    sendMessage(start+mid+end);
                } else if (!rmsg.startsWith("/") && rmsg.toLowerCase().endsWith("ong")) {
                    int index = rmsg.toLowerCase().indexOf("ong");
                    String start = rmsg.substring(0, index);
                    String end = rmsg.substring(index+1);
                    char mid = rmsg.charAt(index) == 'o'?'i':'I';
                    sendMessage(start+mid+end);
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
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(message.getChatId().toString());
        sendMessageRequest.setText(args);
        try {
            execute(sendMessageRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
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
}