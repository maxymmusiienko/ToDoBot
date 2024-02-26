package com.example.todobot;

import com.example.todobot.config.BotConfig;
import com.example.todobot.constant.Commands;
import com.example.todobot.service.ListPointServiceImpl;
import com.example.todobot.service.ListServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final ListPointServiceImpl listPointService;
    private final ListServiceImpl listService;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    private void sendMessage(Long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException("Can`t send message: " + sendMessage.getText(), e);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        //todo rewrite this method; seems bad
        //todo update command
        long chatId = update.getMessage().getChatId();
        final String regex = " ";
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            String[] commandsAndPoints = messageText.split(regex);
            String command = commandsAndPoints[0];
            String point = getPoint(commandsAndPoints);
            switch (command) {
                case Commands.SHOW_COMMAND -> {
                    String list = listService.prepareList(chatId);
                    sendMessage(chatId, list);
                }
                case Commands.ADD_COMMAND -> {
                    listPointService.addPoint(chatId, point);
                    String list = listService.prepareList(chatId);
                    sendMessage(chatId, Commands.ADD_POINT_MESSAGE + list);
                }
                case Commands.DELETE_COMMAND -> {
                    listPointService.deletePoint(chatId, Long.parseLong(point));
                    String list = listService.prepareList(chatId);
                    sendMessage(chatId, Commands.DELETE_POINT_MESSAGE + list);
                }
                case Commands.DONE_COMMAND -> {
                    listPointService.markPointAsDone(chatId, Long.parseLong(point));
                    String list = listService.prepareList(chatId);
                    sendMessage(chatId, Commands.DONE_POINT_MESSAGE + list);
                }
                case Commands.START_COMMAND -> sendMessage(chatId, Commands.START_COMMAND_MESSAGE);
                case Commands.INFO_COMMAND -> sendMessage(chatId, Commands.INFO_COMMAND_MESSAGE);
                case Commands.STAT_COMMAND -> {
                    String stat = listService.prepareStats(chatId);
                    sendMessage(chatId, stat);
                }
                default -> sendMessage(chatId, Commands.INVALID_COMMAND_MESSAGE);
            }
        } else {
            sendMessage(chatId, Commands.INVALID_COMMAND_MESSAGE);
        }
    }

    private String getPoint(String[] strings) {
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < strings.length; i++) {
            res.append(strings[i]).append(" ");
        }
        return res.toString().trim();
    }

    private String[] extractParts(String point) {
        int count = 2;
        int numberIndex = 0;
        int pointIndex = 1;
        String[] parts = new String[count];
        String[] words = point.split(" ");
        parts[numberIndex] = words[numberIndex];
        StringBuilder text = new StringBuilder();
        for (int i = 1; i < words.length; i++) {
            text.append(words[i]);
        }
        parts[pointIndex] = text.toString();
        return parts;
    }
}
