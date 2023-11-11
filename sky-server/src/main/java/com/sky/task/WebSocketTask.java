package com.sky.task;

import com.sky.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Slf4j
public class WebSocketTask {


    private WebSocketServer webSocketServer;

    /**
     * 通过WebSocket每隔5秒向客户端发送消息
     */
    @Scheduled(cron="0/5 * *  * * ? ")
    public void sendMessageToClient() {
        log.info("发送消息");
        webSocketServer.sendToAllClient("这是来自服务端的消息:" + DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()));
    }

}
