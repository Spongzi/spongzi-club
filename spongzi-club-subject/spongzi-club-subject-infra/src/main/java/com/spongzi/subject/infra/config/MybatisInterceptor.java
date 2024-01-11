package com.spongzi.subject.infra.config;

import com.spongzi.club.common.util.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

/**
 * mybatis拦截机
 * 填充createBy，updateBy，createTime，updateTime等公用字段的拦截器
 *
 * @author spong
 * @date 2024/01/11
 */
@Slf4j
@Component
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class,
                Object.class})
})
public class MybatisInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter = invocation.getArgs()[1];
        if (parameter == null) {
            return invocation.proceed();
        }
        // 获取当前登录的用户id
        String loginId = LoginUtil.getLoginId();
        if (StringUtils.isBlank(loginId)) {
            return invocation.proceed();
        }
        if (sqlCommandType == SqlCommandType.INSERT || sqlCommandType == SqlCommandType.UPDATE) {
            replaceEntityProperty(parameter, loginId, sqlCommandType);
        }
        return invocation.proceed();
    }

    private void replaceEntityProperty(Object parameter, String loginId, SqlCommandType sqlCommandType) {
        if (parameter instanceof Map) {
            replaceMap((Map) parameter, loginId, sqlCommandType);
        } else {
            // 实体类的替换
            replace(parameter, loginId, sqlCommandType);
        }
    }

    /**
     * 自动填充数据，数据类型为map
     *
     * @param parameter      参数
     * @param loginId        登录ID
     * @param sqlCommandType SQL命令类型
     */
    private void replaceMap(Map parameter, String loginId, SqlCommandType sqlCommandType) {
        for (Object val : parameter.values()) {
            replace(val, loginId, sqlCommandType);
        }
    }

    /**
     * 自动填充数据，数据类型为实体
     *
     * @param parameter      参数
     * @param loginId        登录ID
     * @param sqlCommandType SQL命令类型
     */
    private void replace(Object parameter, String loginId, SqlCommandType sqlCommandType) {
        if (sqlCommandType == SqlCommandType.INSERT) {
            delInsert(parameter, loginId);
        }
        if (sqlCommandType == SqlCommandType.UPDATE) {
            delUpdate(parameter, loginId);
        }
    }

    private void delUpdate(Object parameter, String loginId) {
        Field[] fields = getAllFields(parameter);
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object o = field.get(parameter);
                if (Objects.nonNull(o)) {
                    field.setAccessible(false);
                    continue;
                }
                switch (field.getName()) {
                    case "updateBy":
                        field.set(parameter, loginId);
                        field.setAccessible(false);
                        break;
                    case "updateTime":
                        field.set(parameter, new Date());
                        field.setAccessible(false);
                        break;
                    default:
                        field.setAccessible(false);
                        break;
                }
            } catch (Exception e) {
                log.error("delInsert.error: {}", e.getMessage(), e);
            }
        }
    }

    private void delInsert(Object parameter, String loginId) {
        Field[] fields = getAllFields(parameter);
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object o = field.get(parameter);
                if (Objects.nonNull(o)) {
                    field.setAccessible(false);
                    continue;
                }
                switch (field.getName()) {
                    case "isDeleted":
                        field.set(parameter, 0);
                        field.setAccessible(false);
                        break;
                    case "createBy":
                        field.set(parameter, loginId);
                        field.setAccessible(false);
                        break;
                    case "createTime":
                        field.set(parameter, new Date());
                        field.setAccessible(false);
                        break;
                    default:
                        field.setAccessible(false);
                        break;
                }
            } catch (Exception e) {
                log.error("delInsert.error: {}", e.getMessage(), e);
            }
        }
    }

    private Field[] getAllFields(Object obj) {
        Class<?> clazz = obj.getClass();
        List<Field> fieldList = new LinkedList<>();
        while (clazz != null) {
            fieldList.addAll(new LinkedList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fields = fieldList.toArray(fields);
        return fields;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
