package tk.nenua4e.mc.server.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import tk.nenua4e.mc.server.dto.PostDto;

@Service
@Slf4j
public class TelegramService extends TelegramLongPollingBot
{
    final String USERNAME;

    final String TOKEN;

    final String CHANNEL_ID;

    final String FRONTEND_HOST;

    public TelegramService(@Value("${telegram.bot.username}") String username,
                           @Value("${telegram.bot.token}") String token,
                           @Value("${telegram.channel.id}") String channel_id,
                           @Value("${frontend.host}") String frontend_host)
    {
        this.USERNAME = username;
        this.TOKEN = token;
        this.CHANNEL_ID = channel_id;
        this.FRONTEND_HOST = frontend_host;

        log.info("Telegram token: " + this.TOKEN);
    }

    public void publish(PostDto post)
    {
        SendMessage msg = new SendMessage(this.CHANNEL_ID, this.prepareMsgContent(post))
                .enableHtml(true)
                .enableWebPagePreview();

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

    private String prepareMsgContent(PostDto post)
    {
        Document doc = Jsoup.parse(post.getPreview());

        doc.outputSettings(new Document.OutputSettings().prettyPrint(false));

        doc.select("h1").after("\n");
        doc.select("h2").after("\n");
        doc.select("p").after("\n");
        doc.select("ol").after("\n");
        doc.select("ul").after("\n");
        doc.select("li").prepend("&#8226; ").after("\n");

        StringBuilder str = new StringBuilder();

        str.append(String.format("<b>%s</b>", post.getTitle()));
        str.append("\n\n");
        str.append(doc.body().wholeText());
        str.append("\n");
        str.append(this.FRONTEND_HOST).append("/news/").append(post.getId());

        return str.toString();
    }
}
