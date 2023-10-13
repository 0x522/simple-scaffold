package com.frame.util;

import com.frame.bean.User;

/**
 * 用户信息
 *
 * @author: chenyuntao
 **/
public class UserContextUtil {

    private static final ThreadLocal<User> CURRENT_USER = new InheritableThreadLocal<>();

    /**
     * 设置当前登录人
     *
     * @param user
     */
    public static void setCurrentUser(User user) {
        CURRENT_USER.set(user);
    }

    /**
     * 获取当前登录人
     *
     * @return
     */
    public static User getCurrentUser() {
        return CURRENT_USER.get();
    }

    /**
     * 清空当前登录人
     */
    public static void clear() {
        CURRENT_USER.set(null);
        CURRENT_USER.remove();
    }
}
