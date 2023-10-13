package com.frame.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.frame.util.TimeStampUtils;
import com.frame.util.UserContextUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author chenyuntao
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createBy", String.class, UserContextUtil.getCurrentUser().getId());
        this.strictInsertFill(metaObject, "createByName", String.class, UserContextUtil.getCurrentUser().getNickName());
        this.strictInsertFill(metaObject, "createTime", String.class, TimeStampUtils.generateTimeStamp());
        this.strictInsertFill(metaObject, "updateBy", String.class, "");
        this.strictInsertFill(metaObject, "updateByName", String.class, "");
        this.strictInsertFill(metaObject, "updateTime", String.class, TimeStampUtils.generateTimeStamp());
        this.strictInsertFill(metaObject, "delFlag", Integer.class, 0);
        this.strictInsertFill(metaObject, "revision", Integer.class, 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateBy", String.class, UserContextUtil.getCurrentUser().getId());
        this.strictUpdateFill(metaObject, "updateByName", String.class, UserContextUtil.getCurrentUser().getNickName());
        this.strictUpdateFill(metaObject, "updateTime", String.class, TimeStampUtils.generateTimeStamp());
    }


}
