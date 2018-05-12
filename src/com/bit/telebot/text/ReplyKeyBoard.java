package com.bit.telebot.text;

import com.bit.telebot.BotHandler;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.List;

import static org.glassfish.grizzly.ProcessorExecutor.execute;

public class ReplyKeyBoard {


    public static SendMessage CreateKeyboard(String...arg){
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setOneTimeKeyboard(true);
        final List<KeyboardRow> keyboard = ((ReplyKeyboardMarkup) markup).getKeyboard();
        // for (int i = 0; i < commands.length; i++)
        //    {
        if (keyboard.isEmpty() || (keyboard.get(keyboard.size() - 1).size() >= 1))
        {
            keyboard.add(new KeyboardRow());
        }

        //keyboard.get(keyboard.size() - 1).add(new KeyboardButton().setText(commands[0]));

        for (int i = 1; i < arg.length; i++){
            if (keyboard.isEmpty() || (keyboard.get(keyboard.size() - 1).size() >= 1))
            {
                keyboard.add(new KeyboardRow());
            }

            keyboard.get(keyboard.size() - 1).add(new KeyboardButton().setText(arg[i]));


            //    }
        }

        final SendMessage msg = new SendMessage();
        msg.setChatId(Long.toString(BotHandler.msg.getChat().getId()));
        msg.setText(arg[0]);
        msg.setReplyToMessageId(BotHandler.msg.getMessageId());
        msg.setReplyMarkup(markup);
        BotHandler b = new BotHandler();
        try {
            b.execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        return msg;

    }
    
}
