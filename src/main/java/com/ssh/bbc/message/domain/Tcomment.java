package com.ssh.bbc.message.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssh.bbc.util.parameterverify.VerifyError;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Table(name = "tcomment")
@Entity
public class Tcomment {
    @Id
    @GeneratedValue
    @Column(name = "CommentId")
    private int commentId;

    @Column(name = "CommentContent")
    @NotEmpty(message = VerifyError.COMMENT_NOT_NULL)
    private String commentContent;

    @Column(name = "CommentTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime commentTime;

    @Column(name = "MessageId")
    @NotNull(message = VerifyError.MESS_ID_NOT_NULL)
    private int messageId;

    @Column(name = "CommentUser")
    private String commentUser;

    public Tcomment() {

    }

    public Tcomment(int commentIdl, String commentContent, LocalDateTime commentTime, int messageId, String commentUser) {
        this.commentId = commentIdl;
        this.commentContent = commentContent;
        this.commentTime = commentTime;
        this.messageId = messageId;
        this.commentUser = commentUser;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public LocalDateTime getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(LocalDateTime commentTime) {
        this.commentTime = commentTime;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(String commentUser) {
        this.commentUser = commentUser;
    }
}
