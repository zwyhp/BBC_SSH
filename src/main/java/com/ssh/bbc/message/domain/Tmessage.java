package com.ssh.bbc.message.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssh.bbc.util.parameterverify.VerifyError;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Table(name = "tmessage")
@Entity
public class Tmessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MessageId")
    private int messageId;
    @Column(name = "GuestName")
    private String guestName;

    @NotEmpty(message = VerifyError.MESS_CONTENT_NOT_NULL)
    @Size(min = 10 , message = VerifyError.MESS_CONTENT_SIZE)
    @Column(name = "MessageContent")
    private String messageContent;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "MessageTime")
    private LocalDateTime messageTime;

    @Column(name = "Reply")
    private String reply;

    @Column(name = "IsPass")
    private int isPass;

    @NotEmpty(message = VerifyError.MESS_KIND_OWNER_NOT_NULL)
    @Column(name = "MessKind")
    private String messKind;

    @NotEmpty(message = VerifyError.MESS_TITLE_NOT_NULL)
    @Size(min = 4 ,max = 15 , message = VerifyError.MESS_TITLE_SIZE)
    @Column(name = "MessageTitle")
    private String messageTitle;

    @Column(name = "ClickNumber")
    private int clickNumber;

    @Column(name = "IsTop")
    private int isTop;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "LastCommentTime")
    private LocalDateTime lastCommentTime;

    public Tmessage() {

    }

    public Tmessage(int messageId, String guestName, String messageContent, LocalDateTime messageTime, String reply, int isPass, String messKind, String messageTitle, int clickNumber, int isTop, LocalDateTime lastCommentTime) {
        this.messageId = messageId;
        this.guestName = guestName;
        this.messageContent = messageContent;
        this.messageTime = messageTime;
        this.reply = reply;
        this.isPass = isPass;
        this.messKind = messKind;
        this.messageTitle = messageTitle;
        this.clickNumber = clickNumber;
        this.isTop = isTop;
        this.lastCommentTime = lastCommentTime;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public LocalDateTime getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(LocalDateTime messageTime) {
        this.messageTime = messageTime;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public int getIsPass() {
        return isPass;
    }

    public void setIsPass(int isPass) {
        this.isPass = isPass;
    }

    public String getMessKind() {
        return messKind;
    }

    public void setMessKind(String messKind) {
        this.messKind = messKind;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public int getClickNumber() {
        return clickNumber;
    }

    public void setClickNumber(int clickNumber) {
        this.clickNumber = clickNumber;
    }

    public int getIsTop() {
        return isTop;
    }

    public void setIsTop(int isTop) {
        this.isTop = isTop;
    }

    public LocalDateTime getLastCommentTime() {
        return lastCommentTime;
    }

    public void setLastCommentTime(LocalDateTime lastCommentTime) {
        this.lastCommentTime = lastCommentTime;
    }
}
