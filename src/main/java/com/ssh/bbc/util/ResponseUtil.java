package com.ssh.bbc.util;


import java.util.HashMap;
import java.util.Map;

/**
 * 响应操作结果
 * <pre>
 *  {
 *      errno： 错误码，
 *      errmsg：错误消息，
 *      data：  响应数据
 *  }
 * </pre>
 * <p>
 * 错误码：
 * <ul>
 * <li> 200，成功；
 *      300  错误
 * <li> 4xx，前端错误，说明前端开发者需要重新了解后端接口使用规范：
 * <ul>
 * <li> 401，参数错误，即前端没有传递后端需要的参数；
 * <li> 402，参数值错误，即前端传递的参数值不符合后端接收范围。
 * </ul>
 * <li> 501，验证失败，即后端要求用户登录；
 * </ul>
 *
 */
public class ResponseUtil {
    public static Object ok() {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", 200);
        obj.put("errmsg", "成功");
        return obj;
    }

    public static Object ok(Object data) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", 200);
        obj.put("errmsg", "成功");
        obj.put("data", data);
        return obj;
    }

    public static Object okList(PageUtil date) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("list", date.getData());
        data.put("total", date.getAllDataSize());
        data.put("page", date.getPageNum());
        data.put("limit", date.getPageSize());
        return ok(data);
    }

    public static Object fail() {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", -1);
        obj.put("errmsg", "错误");
        return obj;
    }

    public static Object fail(int errno, String errmsg) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", errno);
        obj.put("errmsg", errmsg);
        return obj;
    }

    public static Object badArgument(String error) {
        return fail(501, error);
    }
    public static Object unlogin() {
        return fail(501, "请登录");
    }
    public static Object parameterError(String error) {
        return fail(401, error);
    }
    public static Object unsupport() {
        return fail(503, "业务不支持");
    }
    public static Object unsupport(String error) {
        return fail(503,error );
    }
    public static Object afterError(String error) {
        return fail(300,error );
    }


}

