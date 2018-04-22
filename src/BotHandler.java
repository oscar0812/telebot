import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class BotHandler extends TelegramLongPollingBot {
    private String rmsg, arg, cmd, username;
    private Message message;
    private boolean echo = false;

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            message = update.getMessage();

            if (message.hasText()) {
                rmsg = message.getText().toString();
                username = message.getFrom().getUserName().toString();
                System.out.println(username + ":" + rmsg);
                if (echo) {
                    sendMessage(username + ": " + rmsg);
                }

                // COMMAND DOSEN'T TAKE ARGUMENT
                if (rmsg.contains("/") && !rmsg.contains(" ")) {
                    cmd = message.getText().toString().split("/")[1];

                    if (cmd.equalsIgnoreCase("test")) {
                        sendMessage("bbc pornos");
                    } else if (cmd.equalsIgnoreCase("echo") && !echo) {
                        echo = true;
                        sendMessage("Echo is Enabled!");

                    } else if (cmd.equalsIgnoreCase("echo off")) {
                        echo = false;
                    }


                    // COMMAND TAKES ARGUMENT
                } else if (rmsg.startsWith("/") && rmsg.contains(" ")) {
                    cmd = message.getText().toString().split("/")[1].split(" ")[0];
                    arg = rmsg.split("/say")[1];

                    if (cmd.equals("say")) {
                        sendMessage(arg);
                    }


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
}