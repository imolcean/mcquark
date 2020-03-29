package tk.nenua4e.mc.server.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class TelegramService extends TelegramLongPollingBot
{
    final String USERNAME;

    final String TOKEN;

    final String CHANNEL_ID;

    public TelegramService(@Value("${telegram.bot.username}") String username,
                           @Value("${telegram.bot.token}") String token,
                           @Value("${telegram.channel.id}") String channel_id)
    {
        this.USERNAME = username;
        this.TOKEN = token;
        this.CHANNEL_ID = channel_id;
    }

    public void publish(String text)
    {
        // TODO: Text preparation

        SendMessage msg = new SendMessage(this.CHANNEL_ID, text);

        try
        {
            this.execute(msg);
        }
        catch (TelegramApiException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {}

    @Override
    public String getBotUsername()
    {
        return this.USERNAME;
    }

    @Override
    public String getBotToken()
    {
        return this.TOKEN;
    }
}
