package com.connection.channel.main.connection.mqtt;
 
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.connection.channel.main.connection.util.MQTTUtil;
 
/**
 * 发布消息的回调类 
 *  
 * 必须实现MqttCallback的接口并实现对应的相关接口方法 
 *      ◦CallBack 类将实现 MqttCallBack。每个客户机标识都需要一个回调实例。在此示例中，构造函数传递客户机标识以另存为实例数据。在回调中，将它用来标识已经启动了该回调的哪个实例。 
 *  ◦必须在回调类中实现三个方法： 
 *  
 *  public void messageArrived(String topic, MqttMessage message) 
 *  接收已经预订的发布。 
 *  
 *  public void connectionLost(Throwable cause) 
 *  在断开连接时调用。 
 *  
 *  public void deliveryComplete(IMqttDeliveryToken token)) 
 *      接收到已经发布的 QoS 1 或 QoS 2 消息的传递令牌时调用。 
 *  ◦由 MqttClient.connect 激活此回调。 
 * @author AFatOrange
 *
 */
public class PushCallback implements MqttCallback {
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    private MqttClient client;
    
    private MqttConnectOptions options;
    
    public PushCallback() {}
    
    public PushCallback(MqttClient client, MqttConnectOptions options) {
        this.client = client;
        this.options = options;
    }
 
    /**
     * 连接丢失后，一般在这里面进行重连
     */
    @Override
    public void connectionLost(Throwable cause) {
        log.info("连接断开，开始重连");
        MQTTUtil.disconnect(client);
        MQTTUtil.startReconnect(client,options);
    }
 

    /**
     * 发布）publish后会执行到这里,发送状态,接收到已经发布的 QoS 1 或 QoS 2 消息的传递令牌时调用。
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        log.info("deliveryComplete:{}",token.isComplete());
    }
 
    /**
     * 消息到达：指收到消息,subscribe后得到的消息会执行到这里面
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        log.info("接收消息主题:{}",topic);
        log.info("接收消息Qos:{}",message.getQos());
        String content = new String(message.getPayload(), "utf-8");
        log.info("接收消息内容:{}",content);
    }
 
}