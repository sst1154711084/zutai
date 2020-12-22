package com.sst.projectService.config;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sst.commonutils.MyException;
import com.sst.projectService.entity.Variable;
import com.sst.projectService.service.VariableService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author zhengkai.blog.csdn.net
 */
@ServerEndpoint("/WebSocket/{id}")
@Component
@Slf4j
public class WebSocketServer {

    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    private static ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**接收数据源id*/
    private String id;

    static VariableService variableService;
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("id") String id) {
        this.session = session;
        this.id =id;
        if(webSocketMap.containsKey(id)){
            webSocketMap.remove(id);
            webSocketMap.put(id,this);
            //加入set中
        }else{
            webSocketMap.put(id,this);
            //加入set中
            addOnlineCount();
            //在线数加1
        }

        log.info("websocket连接:"+id+",当前WebSocket连接个数:" + getOnlineCount());

        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error("websocket连接:"+id+",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(id)){
            webSocketMap.remove(id);
            //从set中删除
            subOnlineCount();
        }
        log.info("WebSocket连接断开"+ id +",当前WebSocket连接数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("WebSocket消息:"+ id +",报文:"+message);
        //可以群发消息
        //消息保存到数据库、redis
        if(StringUtils.isNotBlank(message)){
            try {
                //解析发送的报文
                JSONObject jsonObject = JSON.parseObject(message);
                String vid = jsonObject.getString("vid");
                // TODO 把变量的值推送到设备上（修改设备值）
                Variable variable = variableService.getById(vid);
                if(Objects.isNull(variable)){
                    sendMessage("无此变量");
                    throw new MyException("无此变量");
                }


                double max = variable.getMax();
                double min = variable.getMin();
                int millTime = variable.getFrequency();
                // 如果是模拟的变量
                if(variable.getDataSourceId()==null){
                    ((Runnable) () -> {
                        while(webSocketMap.get(id)!=null){
                            try {
                                Thread.sleep(millTime);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Double value = min + Math.random() * (max - min);
                            try {
                                sendMessage(value.toString());
                            } catch (IOException e) {
                                e.printStackTrace();
                                throw new MyException("发送数据失败");
                            }
                        }
                    }).run();
                }
                /*String toUserId=jsonObject.getString("toUserId");
                //传送给对应toUserId用户的websocket
                if(StringUtils.isNotBlank(toUserId)&&webSocketMap.containsKey(toUserId)){
                    webSocketMap.get(toUserId).sendMessage(jsonObject.toJSONString());
                }else{
                    log.error("数据源:"+toUserId+"未连接");
                    //否则不在这个服务器上，发送到mysql或者redis
                }*/
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:"+this.id +",原因:"+error.getMessage());
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 发送自定义消息
     * */
    public static void sendInfo(String message,@PathParam("id") String id) throws IOException {
        log.info("发送消息到:"+id+"，报文:"+message);
        if(StringUtils.isNotBlank(id)&&webSocketMap.containsKey(id)){
            webSocketMap.get(id).sendMessage(message);
        }else{
            log.error("用户"+id+",不在线！");
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}

