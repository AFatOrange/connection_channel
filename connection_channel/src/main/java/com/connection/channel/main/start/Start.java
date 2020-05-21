package com.connection.channel.main.start;

import java.io.UnsupportedEncodingException;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.connection.channel.main.connection.mqtt.ClientMQTT;
import com.connection.channel.main.connection.mqtt.ServerMQTT;

@Component
public class Start {
    
    @Autowired
    ClientMQTT clientMQTT;
    
    @Autowired
    ServerMQTT serverMQTT;
    
    //定义一个主题
    @Value("${mqtt.server.topic}")
    String TOPIC;
    
    public void init() {
        
        try {
            serverMQTT.init();
            serverMQTT.message = new MqttMessage();
            serverMQTT.message.setQos(1);
            //设置消息保留
            serverMQTT.message.setRetained(true);
            serverMQTT.message.setPayload("eeeeeaaaaaawwwwww---".getBytes("UTF-8"));
            MqttTopic topic = serverMQTT.client.getTopic(TOPIC);
            serverMQTT.publish(topic,serverMQTT.message);
        }catch (UnsupportedEncodingException e) {
            System.out.println("负载转换失败");
            e.printStackTrace();
        }
        
        clientMQTT.init();
        int[] Qos  = {1};
        String[] topic = {"tokudu/yzq1242"};
        String[] topic1 = {"tokudu/yzq125"};
        String[] topic2 = {"tokudu/yzq1233"};
        clientMQTT.subscribe(Qos, topic);
        
        try {
          Thread.sleep(5000);
          clientMQTT.subscribe(Qos, topic1);
//          MqttTopic topic3 = serverMQTT.client.getTopic(TOPIC);
//          serverMQTT.publish(topic3,serverMQTT.message);
          Thread.sleep(15000);
          clientMQTT.subscribe(Qos, topic2);
      } catch (InterruptedException e1) {
          e1.printStackTrace();
      }
        
    }
    
}
