package com.ssh.bbc.util.parameterverify;

/**
 * 参数错误
 */
public class VerifyError {
    public static final String ID_NOT_NULL = "id不能为空";

    public static final String USER_NAME_NOT_NULL = "用户名不能为空";
    public static final String USER_PWD_NOT_NULL = "密码不能为空";
    public static final String USER_NAME_SIZE = "用户名长度必须为5-20";
    public static final String EMAIl_NOT_NULL = "邮箱不能为空";
    public static final String QUESTION_NOT_NULL = "安全问题不能为空";
    public static final String ANSWER_NOT_NULL = "安全问题答案不能为空";
    public static final String PWD_NAME_SIZE = "密码长度必须为6-16";

    public static final String CATEGORY_NOT_NULL = "版块名不能为空";
    public static final String CATEGORY_NAME_SIZE = "版块名长度必须在2-4字之间";
    public static final String CATEGORY_OWNER_NOT_NULL = "版主不能为空";

    public static final String MESS_CONTENT_NOT_NULL = "帖子内容不能为空";
    public static final String MESS_CONTENT_SIZE = "帖子内容最少为10字";
    public static final String MESS_KIND_OWNER_NOT_NULL = "帖子板块不能为空";
    public static final String MESS_TITLE_NOT_NULL = "帖子标题不能为空";
    public static final String MESS_TITLE_SIZE = "帖子标题必须在4-15字之间";
    public static final String MESS_ID_NOT_NULL = "回复帖子ID不能为空";

    public static final String COMMENT_NOT_NULL = "回复内容不能为空";

    public static final String COMMENT_SIZE = "回复内容最少不低于5字";



}
