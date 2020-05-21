package com.connection.channel.main.connection.mqtt;
 
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ScheduledExecutorService;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
 
/**
 * 客户端接收消息
 * @author AFatOrange
 *
 */
@Component
public class ClientMQTT {
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());
 
    //tcp://MQTT安装的服务器地址:MQTT定义的端口号
    @Value("${mqtt.client.host}")
    String HOST;
    //定义MQTT的ID，可以在MQTT服务配置中指定
    @Value("${mqtt.client.clientid}")
    String clientid;
    //连接用户名
    @Value("${mqtt.client.username}")
    String userName;
    //密码
    @Value("${mqtt.client.password}")
    String passWord;
    //设置超时时间 单位为秒
    @Value("${mqtt.client.connection-timeout}")
    Integer connectionTimeout;
    //设置会话心跳时间 单位为秒
    @Value("${mqtt.client.keep-alive-interval}")
    Integer keepAliveInterval;
    
    private MqttClient client;
    private MqttConnectOptions options;
    
    public void init() {
        try {
            //防止重复创建MQTTClient实例
            if(client==null) {
                // host为主机名，test为clientid即连接MQTT的客户端ID，一般以客户端唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
                client = new MqttClient(HOST, clientid, new MemoryPersistence());
                // 设置回调
                client.setCallback(new PushCallback(client, options));
            }
            setMqttConnectOptions();
            connect();
        } catch (MqttException e) {
            log.error("mqtt客户端连接连接失败:{}");
            e.printStackTrace();
        }
    }
    
    /**
     * MQTT的连接设置
     */
    public void setMqttConnectOptions() {
        try {
            options = new MqttConnectOptions();
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            // 如果想要断线这段时间的数据，要设置成false，并且重连后不用再次订阅，否则不会得到断线时间的数据
            options.setCleanSession(true);
            // 设置连接的用户名
            options.setUserName(userName);
            // 设置连接的密码
            options.setPassword(passWord.toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(connectionTimeout);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(keepAliveInterval);
            //注意最后遗嘱的topic不可以带通配符
            //topic = client.getTopic(TOPIC);
            //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息  
            //topic主题
            //负载(内容)
            //qos服务质量
            //是否保留该条消息
            options.setWill("client/close", "client is close".getBytes("UTF-8"), 0, true);
        }catch (UnsupportedEncodingException e) {
            log.error("负载转换失败");
            e.printStackTrace();
        }
    }
    
    /**
     * 用来连接服务器
     */
    public void connect() {
        try {
            if (!client.isConnected()) {
                client.connect(options);
                log.info("mqtt客户端接收连接成功");
            }else {
                log.info("mqtt客户端接收连接成功已存在");
                disconnect();
                client.connect(options);
                log.info("mqtt客户端接收连接成功");
            }
            
        } catch (MqttException  e) {
                log.error("mqtt客户端接收连接失败");
                e.printStackTrace();
        }
    }
    
    
    
    public void subscribe(int[] Qos,String[] topic){
        //订阅消息
        try {
            client.subscribe(topic, Qos);
            for (String string : topic) {
                log.info("订阅消息主题:{}",string);
            }
        } catch (MqttException e) {
            log.error("mqtt客户端主题订阅失败");
            e.printStackTrace();
        }

    }
    
    /**
     * 断开连接
     */
    public void disconnect() {
         try {
            client.disconnect();
            log.info("mqtt客户端接收断开连接成功");
        } catch (MqttException e) {
            log.error("mqtt客户端接收断开连接失败:{}");
            e.printStackTrace();
        }
    }
    
 
}
