package com.ssh.bbc.util;


/**
 * 业务异常工具类
 */
public class BussinessUtil {
    public static final String USER_NOT_EXIST = "用户不存在";
    public static final String CATEGORY_REPETITION = "板块名重复";
    public static final String OWNER_REPETITION = "板主重复";

    public static final String USERNAME_REPETITION = "用户名重复";
    public static final String EMAIL_REPETITION = "邮箱已经被注册";

    public static final String UPDATE_FAILED = "修改失败";
    public static final String ANSWER_FAILED = "安全问题答案错误";
    public static final String DELETE_FAILED = "删除失败";
    public static final String ADD_FAILED = "添加失败";

    public static final String MESSAGE_IS_NULL = "帖子不存在";

    public static final String CLICK_FAILED = "验证失败";
    public static final String TOP_FAILED = "置顶失败";

    public static final String PWD_ERROR = "登录失败";

    public static final String USER_FREEZE = "你的账号被冻结，无法发帖";


    private BussinessUtil() { throw new IllegalStateException("Utility class"); }

    public static void isNull(Object object, String error){
        if (object == null){
            throw new BussinessException(error);
        }
    }
    public static void isnotNull(Object object, String error){
        if (object != null){
            throw new BussinessException(error);
        }
    }

    public static void error(String error){
        throw new BussinessException(error);
    }

    public static void pagingfind(boolean judge){
        if (judge){
            throw new BussinessException("当前页码超出总页数");
        }
    }

    public static void pleaseLogin(String error){
        throw new LoginException(error);
    }

}
