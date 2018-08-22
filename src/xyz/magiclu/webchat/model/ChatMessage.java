package xyz.magiclu.webchat.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Administrator on 2018/8/13.
 */
public class ChatMessage {

    private ArrayList<String> messages = new ArrayList<>(); //信息存储
    private int timeout = 10;                               //过期信息
    private final String format_char = "~";

    public ChatMessage(){}

    public ChatMessage(String s){

        this.format(s);
    }
    /**
     * 添加信息
     * @param msg 信息
     */
    public void append(String msg){

        //聊天语句大于10条，则删除前面的5条
        if(messages.size() > timeout){
            this.clear();
        }
        messages.add(msg);
    }

    /**
     *
     */
    public List<String> getMessages(){

        return this.messages;
    }

    /**
     * 清除过期信息
     */
    private void clear(){

        for(int i = 0; i < timeout/2; i++){
            messages.remove(i);
        }
    }

    public String toStringFormat(){

        String format ="";
        for(String msg : this.messages){

            format += format_char+msg;
        }

        if(format.length() == 0)
            return "";

        return format.substring(1,format.length());
    }

    public void format(String str){

        if(str == null || str.equals(""))
            return;

        String[] msg = str.split(format_char);

        for(String m : msg){
            this.append(m);
        }
    }
}
