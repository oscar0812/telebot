import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class BotHandler extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "intellij_bot";
    }

    @Override
    public void onUpdateReceived(Update arg0) {
        // TODO Auto-generated method stub
        System.out.println("here");
    }

    @Override
    public String getBotToken() {
        return "508112557:AAFOC-R-KXsFniSQSVmGOBT4iSRMWMx2URA";
    }

}