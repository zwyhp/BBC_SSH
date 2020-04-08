package com.ssh.bbc.user.domain;

import javax.persistence.*;

import com.ssh.bbc.util.parameterverify.VerifyError;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tuser")
public class Tuser {
    @Id
    @GeneratedValue
    @Column(name = "UserID")
    private int userId;

    @Column(name = "UserName")
    @NotEmpty(message = VerifyError.USER_NAME_NOT_NULL)
    private String userName;

    @Column(name = "UserPwd")
    @NotEmpty(message = VerifyError.USER_PWD_NOT_NULL)
    private String userPwd;

    @Column(name = "UserEmail")
    @NotEmpty(message = VerifyError.EMAIl_NOT_NULL)
    private String userEmail;

    @Column(name = "InBlack")
    private int inBlack;  //默认为0   1为加入黑名单

    @Column(name = "Question")
    @NotEmpty(message = VerifyError.QUESTION_NOT_NULL)
    private String question;

    @Column(name = "Answer")
    @NotEmpty(message = VerifyError.ANSWER_NOT_NULL)
    private String answer;

    @Column(name = "IsOwner")
    private int isOwner; //默认为0   1为版主, 可以有一个为2的作为内置管理员

    public Tuser() {

    }
    public Tuser(String userName, String userPwd, String userEmail, int inBlack, String question, String answer, int isOwner) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.userEmail = userEmail;
        this.inBlack = inBlack;
        this.question = question;
        this.answer = answer;
        this.isOwner = isOwner;
    }


    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getInBlack() {
        return inBlack;
    }

    public void setInBlack(int inBlack) {
        this.inBlack = inBlack;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getIsOwner() {
        return isOwner;
    }

    public void setIsOwner(int isOwner) {
        this.isOwner = isOwner;
    }
}
