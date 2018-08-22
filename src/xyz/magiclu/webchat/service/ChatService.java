package xyz.magiclu.webchat.service;

import xyz.magiclu.webchat.model.User;
import xyz.magiclu.webchat.util.ChatUtil;
import xyz.magiclu.webchat.util.UserNotFindException;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * 聊天信息的逻辑service
 *
 * Created by Administrator on 2018/8/13.
 */
public class ChatService {


    public void doChat(String domain, String send_message, HttpServletRequest request) throws UserNotFindException {

        HashMap<String,User> online_user = ChatUtil.getOnlineUser(request);

        String username = request.getParameter("username");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time =dateFormat.format(System.currentTimeMillis());

        //所有人
        if(domain.equals("all")){

            //发送所有人
            for (String user : online_user.keySet()){
                online_user.get(user).receive(time+"<br/> &nbsp;&nbsp;&nbsp;&nbsp;"+username+":"+send_message);
            }
        }else{
            //私聊
            try {
                online_user.get(domain).receive(time+"<br/> &nbsp;&nbsp;&nbsp;&nbsp;"+username+":"+send_message);
            }catch (NullPointerException e){
                throw new UserNotFindException("无该用户");
            }
        }
    }
}
