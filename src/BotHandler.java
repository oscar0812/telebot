import com.bittle.urban.Definition;
import com.bittle.urban.UrbanDictionary;
import org.javia.arity.MathSolver;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.ChatMember;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiValidationException;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class BotHandler extends TelegramLongPollingBot {
    private String rmsg, arg, cmd, username;
    private Message message;
    private boolean echo = false;
    static  int COUNTER;


    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            message = update.getMessage();

            if (message.hasText()) {
                rmsg = message.getText().toString();
                username = message.getFrom().getUserName().toString();
                System.out.println(username + ":" + rmsg);


                //sendPhoto("https://www.google.com/imgres?imgurl=https%3A%2F%2Fimages.pexels.com%2Fphotos%2F67636%2Frose-blue-flower-rose-blooms-67636.jpeg%3Fauto%3Dcompress%26cs%3Dtinysrgb%26h%3D350&imgrefurl=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Fflower%2F&docid=5UbOpOqf9qM23M&tbnid=ZAXhvwKefKr6jM%3A&vet=10ahUKEwjNktOF7c3aAhUEvFkKHVwWA3YQMwivASgBMAE..i&w=528&h=350&bih=620&biw=612&q=images&ved=0ahUKEwjNktOF7c3aAhUEvFkKHVwWA3YQMwivASgBMAE&iact=mrc&uact=8", "Now has captionk");
                // COMMAND DOSEN'T TAKE ARGUMENT
                if (rmsg.contains("/") && !rmsg.contains(" ")) {
                    cmd = message.getText().toString().split("/")[1];

                    if (cmd.equalsIgnoreCase("echo") ) {
                        COUNTER++;
                        if(COUNTER == 1){
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
                    } else if(cmd.equalsIgnoreCase("/math")){
                            sendMessage(MathSolver.solve(arg));

                    }

                } if (echo && !rmsg.equals("/echo")) {
                    sendMessage(username + ": " + rmsg);
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "intellij_bot";
    }

    @Override
    public String getBotToken() {
        return "508112557:AAFOC-R-KXsFniSQSVmGOBT4iSRMWMx2URA";
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
    public void sendPhoto(String args){
        SendPhoto sendPhotoRequest = new SendPhoto();
        sendPhotoRequest.setChatId(message.getChatId().toString());
        sendPhotoRequest.setPhoto(args);

        try {
            sendPhoto(sendPhotoRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void sendPhoto(String args, String caption){
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