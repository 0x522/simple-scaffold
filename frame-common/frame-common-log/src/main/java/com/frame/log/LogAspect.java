//package com.frame.log;
//
//import cn.hutool.core.bean.BeanUtil;
//import cn.hutool.core.util.ObjectUtil;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.frame.bean.User;
//import com.frame.log.annotation.IrLog;
//import com.frame.log.model.dto.LogExceptionDto;
//import com.frame.log.model.dto.LogOperationDto;
//import com.frame.log.model.entity.LogException;
//import com.frame.log.model.entity.LogOperation;
//import com.frame.log.service.ILogExceptionService;
//import com.frame.log.service.ILogOperationService;
//import com.frame.util.IpUtil;
//import com.frame.util.TimeStampUtils;
//import com.frame.util.UserContextUtil;
//import com.google.gson.Gson;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.exception.ExceptionUtils;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Method;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Objects;
//
///**
// * @author qiruilong
// */
//@Slf4j
//@Component
//@Aspect
//@ConditionalOnProperty(name = {"log.aspect.enable"}, havingValue = "true", matchIfMissing = true)
//public class LogAspect {
//
//    @Autowired
//    ILogOperationService logOperationService;
//
//    @Autowired
//    ILogExceptionService logExceptionService;
//    private static final String BUSINESS_ID = "businessId";
//
//    /**
//     * 链路追踪切点
//     */
//    @Pointcut("execution(* com.frame.*.controller.*Controller.*(..)) || execution(* com.frame.*.service.*Service.*(..))")
//    private void pointCut() {
//    }
//
//    /**
//     * 配置错误日志切入点
//     */
//    @Pointcut("execution(* com.frame.*.controller.*Controller.*(..))")
//    public void errorPoint() {
//    }
//
//    @Around("pointCut()")
//    public Object around(ProceedingJoinPoint pjp) throws Throwable {
//        Object[] reqArgs = pjp.getArgs();
//        String req = filterReqParam(reqArgs);
//        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
//        String methodName = methodSignature.getDeclaringType().getName() + "." + methodSignature.getName();
//        log.info("{},request:{}", methodName, req);
//        Long startTime = System.currentTimeMillis();
//        Object responseObj = pjp.proceed();
//        String resp = null;
//        if (!(responseObj instanceof HttpServletRequest || responseObj instanceof HttpServletResponse)) {
//            resp = new Gson().toJson(responseObj);
//        }
//        Long endTime = System.currentTimeMillis();
//        long time = endTime - startTime;
//        log.info("{},response:{},costTime:{} millis", methodName, resp, time);
//        return responseObj;
//    }
//
//    private String filterReqParam(Object[] reqArgs) {
//        List<Object> reqParam = new ArrayList<>();
//        for (Object arg : reqArgs) {
//            if (!(arg instanceof HttpServletRequest || arg instanceof HttpServletResponse)) {
//                reqParam.add(arg);
//            }
//            if (arg instanceof MultipartFile) {
//                String filename = ((MultipartFile) arg).getOriginalFilename();
//                reqParam.add(filename);
//            }
//        }
//        return new Gson().toJson(reqParam);
//    }
//
//    /**
//     * 保存访问日志
//     */
//    private void setLog(ProceedingJoinPoint pjp, HttpServletRequest request, String resp) {
//        // 异步执行保存日志,构建操作日志对象
//        LogOperationDto logOperationDto = new LogOperationDto();
//        MethodSignature signature = (MethodSignature) pjp.getSignature();
//        Method method = signature.getMethod();
//        // 注解说明信息
//        IrLog irLog = method.getAnnotation(IrLog.class);
//        if (ObjectUtil.isNotEmpty(irLog)) {
//            logOperationDto.setLogContent(irLog.value());
//            logOperationDto.setOperateType(irLog.operateType().getCode());
//            logOperationDto.setOperateName(irLog.operateType().getName());
//        }
//        Object[] reqArgs = pjp.getArgs();
//        String req = filterReqParam(reqArgs);
//        logOperationDto.setRequestPath(request.getRequestURI());
//        logOperationDto.setRequestParam(req);
//        User user = UserContextUtil.getCurrentUser();
//        if (ObjectUtil.isNotEmpty(user)) {
//            logOperationDto.setUserId(user.getId());
//            logOperationDto.setUserName(user.getNickName());
//            logOperationDto.setEmail(user.getEmail());
//        }
//        // 优先读取header属性，否则在respond取
//        if (StringUtils.isNotBlank(request.getHeader(BUSINESS_ID))) {
//            logOperationDto.setBizId(request.getHeader(BUSINESS_ID));
//        } else {
//            JSONObject resObj = JSON.parseObject(resp);
//            JSONObject data = resObj.getJSONObject("data");
//            if (ObjectUtil.isNotEmpty(data)) {
//                // 避免业务id取不到异常
//                logOperationDto.setBizId(ObjectUtil.isNotEmpty(data.get(BUSINESS_ID)) ? data.getString(BUSINESS_ID) : "");
//            }
//        }
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        logOperationDto.setOperateTime(sdf.format(date));
//        // 日志记录异常，不影响正常业务逻辑
//        try {
//            logOperationService.saveOrUpdate(BeanUtil.copyProperties(logOperationDto, LogOperation.class));
//        } catch (Exception e) {
//            log.error("记录操作日志发生异常，异常操作:{}，异常信息:{}", logOperationDto.getRequestPath(), e);
//        }
//    }
//
//    /**
//     * 异常返回通知，用于拦截异常日志信息 连接点抛出异常后执行
//     *
//     * @param e 异常信息
//     */
//    @AfterThrowing(pointcut = "errorPoint()", throwing = "e")
//    public void saveExceptionLog(JoinPoint point, Throwable e) {
//        // 获取request
//        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
//        // 构建日志对象
//        LogExceptionDto logExceptionDto = new LogExceptionDto();
//        Object[] reqArgs = point.getArgs();
//        String req = filterReqParam(reqArgs);
//        logExceptionDto.setIp(IpUtil.getIpAddr(request));
//        logExceptionDto.setRequestPath(request.getRequestURI());
//        logExceptionDto.setRequestParam(req);
//        String[] split = e.toString().split(":");
//        if (ObjectUtil.isNotEmpty(split)) {
//            logExceptionDto.setErrorCategory(split[0]);
//        }
//        logExceptionDto.setErrorInfo(e.getMessage());
//        logExceptionDto.setStackInfo(ExceptionUtils.getStackTrace(e));
//        logExceptionDto.setOperateTime(TimeStampUtils.generateTimeStamp());
//        User user = UserContextUtil.getCurrentUser();
//        if (ObjectUtil.isNotEmpty(user)) {
//            logExceptionDto.setUserId(user.getId());
//            logExceptionDto.setUserName(user.getNickName());
//        }
//        // 日志记录异常，不影响正常业务逻辑
//        try {
//            // 保存日志
//            logExceptionService.save(BeanUtil.copyProperties(logExceptionDto, LogException.class));
//        } catch (Exception ex) {
//            log.error("记录错误日志发生异常，异常操作:{}，异常信息:{}", logExceptionDto.getRequestPath(), ex);
//        }
//    }
//}
