package noStrings.semicolon.data.models;

import java.time.LocalDateTime;

public class Message {
    private LocalDateTime timeSent;
    private String contenet;
    private int messageId;
    private int reciverId;
    private int senderId;

    public LocalDateTime getTimeSent(){return timeSent;}

    public void setTimeSent(LocalDateTime time){this.timeSent = time;}

    public int getId(){return messageId;}

    public void setId (int messageId){ this.messageId = messageId;}

    public int getReciverId(){return reciverId;}

    public void setReciverId (int messageId){ this.reciverId = reciverId;}

    public int getSenderId(){return senderId;}

    public void setSenderId (int senderId){ this.senderId = senderId;}

    public String getContenet() {return contenet;}

    public void setContenet (String content){ this.contenet = content;}
}
