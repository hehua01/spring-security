package com.example.springsecurityjwt.constants;

/**
 * @Date 2022/3/7 19:41
 * @Description
 */
public class Const {
    public static final long EXPIRATION_TIME = 432_000_000;     // 5天(以毫秒ms计)
    public static final String SECRET = "Hehua";      // JWT密码
    public static final String TOKEN_PREFIX = "Bearer";         // Token前缀
    public static final String HEADER_STRING = "Authorization"; // 存放Token的Header Key
}
