import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class BotHandler extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            // valid
            Message message = update.getMessage();
            if (message.hasText()) {
                SendMessage sendMessageRequest = new SendMessage();
                sendMessageRequest.setChatId(message.getChatId().toString());
                sendMessageRequest.setText("you said: " + message.getText());
                try {
                    execute(sendMessageRequest);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                } //end catch()
            } //end if()
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
}