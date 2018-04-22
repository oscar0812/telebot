import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args) {

        // Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot
        System.out.println("Bot starting up...");
        try {
            botsApi.registerBot(new BotHandler());
            System.out.println("Bot startup successful.");
        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.out.println("Bot startup failed.");
        }


    }//end main()
}
