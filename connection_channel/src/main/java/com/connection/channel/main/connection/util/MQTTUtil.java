package com.connection.channel.main.connection.util;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MQTTUtil {
    
    private static final Logger log = LoggerFactory.getLogger(MQTTUtil.class);
    
    /**
     * 重新链接
     */
    public static void startReconnect(MqttClient client, MqttConnectOptions options) {
        long reconnectTimes = 1;
        while (true) {
            try {
                if (client.isConnected()) {
                    log.info("mqtt重连成功");
                    break;
                }
                log.info("mqtt第:{}次重连失败,5秒后重试......", reconnectTimes++);
                client.reconnect();
            }catch (MqttException e) {
                log.error("mqtt重连出错", e);
                e.printStackTrace();
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 断开连接
     */
    public static void disconnect(MqttClient client) {
         try {
            client.disconnect();
            log.info("mqtt客户端接收断开连接成功");
        } catch (MqttException e) {
            log.error("mqtt客户端接收断开连接失败:{}");
            e.printStackTrace();
        }
    }
    
}
