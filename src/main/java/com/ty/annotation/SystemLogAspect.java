package com.ty.annotation;

import com.alibaba.fastjson.JSONObject;
import com.ty.model.SysLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Order(3)
//@PropertySource(value = "classpath:bootstrap.yml",encoding="utf-8")
public class SystemLogAspect {

	private Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

//    @Autowired
//    private KafkaTemplate kafkaTemplate;
//
//	@Value("${system.ignore.login.url}")
//	private  String ignore;//
//
//	@Value("${spring.application.name}")
//	private String platform;
//
//    @Autowired
//    private GetLoginUserInformationByToken getLoginUserInformationByToken;


    /***
     *
    * 定义controller切入点拦截规则，拦截SystemControllerLog注解的方法
     */
    //@Pointcut("within(com.rondaful.controller..*)") // 不需要在每个方法前写入注解
    @Pointcut("@annotation(com.ty.annotation.AspectContrLog)")
    public void controllerAspect(){}


    /***
     *  拦截控制层的操作日志
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("SystemLogAspect.controllerAspect()&&@annotation(controllerAspect)")
    public Object recordLog(ProceedingJoinPoint joinPoint,AspectContrLog controllerAspect) throws Throwable {
    	SysLog systemLog = new SysLog();
        Object proceed = null ;
        try{
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest httpServletRequest =  requestAttributes.getRequest();
//		logger.info("日志层====》"+httpServletRequest.getRequestURL().toString());
		String username = null;
		if ( httpServletRequest.getRequestURL().toString().contains("/login/") ) {
			username = httpServletRequest.getParameter("username");
		}
		if ("/login/supplierLogin".contains(httpServletRequest.getServletPath())) {
			systemLog.setPlatformType(0);
		}
		if ("/login/sellerLogin".contains(httpServletRequest.getServletPath())) {
			systemLog.setPlatformType(1);
		}
		if ("/login/manageUserLogin".contains(httpServletRequest.getServletPath())) {
			systemLog.setPlatformType(2);
		}
        //获取请求的ip
		systemLog.setUsername(username);
//        systemLog.setClientIp(RequestClientUtil.getIpAddress(httpServletRequest));
        systemLog.setOptionUrl(httpServletRequest.getServletPath()); // 请求的接口
        systemLog.setCreateDate(new Date()); //获取方法执行前时间
        systemLog.setOptionActiontype(controllerAspect.actionType());
        systemLog.setOptionDescrption(controllerAspect.descrption());
        systemLog.setUserToken(httpServletRequest.getHeader("token"));
        proceed = joinPoint.proceed();

        logger.info("接口类型："+controllerAspect.actionType());
        logger.info("接口说明："+controllerAspect.descrption());

        StringBuilder builder = new StringBuilder("");
        //System.out.println("接口名称"+joinPoint...actionType());
        builder.append(joinPoint.toString());

        Object[] params = joinPoint.getArgs() ;
        for (Object param : params) {
            builder.append("={").append(param).append(",").append("}");
            builder.deleteCharAt(builder.length()-2);
        }
        logger.info("请求参数："+builder.toString());
        //获取执行的方法名
        systemLog.setOptionParams(builder.toString());
        //调用User服务接口进行日志信息传递
        Map<String,Object> map = new HashMap<String,Object>();
//        UserAll user = getLoginUserInformationByToken.getUserInfo();
//        if ( user != null ){
//            map.put("user",user);
//        }
        map.put("systemLog",systemLog);
        logger.info("获取到用户的ip:{}",systemLog.getClientIp());
        String sysLog = JSONObject.toJSONString(map);
//        logger.debug("操作日志信息===》"+sysLog);
//        kafkaTemplate.send("systemOperationLogKafka",  sysLog);
        //iUserSysLogFeign.insertLogController(sysLog);
        //sysLogService.insert(systemLog);
        } catch (Exception e){
            logger.error("日志操作失败",e);
            throw e;
        }
        return proceed ;
    }

    /***
     *    获取controller的操作信息
     * @param point
     * @return
     */
    public String getControllerMethodDescription(ProceedingJoinPoint point) throws  Exception{
        //获取连接点目标类名
        String targetName = point.getTarget().getClass().getName() ;
        //获取连接点签名的方法名
        String methodName = point.getSignature().getName() ;
        //获取连接点参数
        Object[] args = point.getArgs() ;
        //根据连接点类的名字获取指定类
        Class targetClass = Class.forName(targetName);
        //获取类里面的方法
        Method[] methods = targetClass.getMethods() ;
        String description="" ;
        for (Method method : methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == args.length){
                    description = method.getAnnotation(AspectContrLog.class).descrption();
                    break;
                }
            }
        }
        return description ;
    }

    //异常处理
    //@AfterThrowing(pointcut = "controllerAspect()",throwing="e")
//    public void doAfterThrowing(JoinPoint joinPoint,Throwable e) throws Throwable{
//        // SystemLog systemLog = new SystemLog();
//		// HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        // TODO save to db
//        //systemLogService.saveUser(systemLog);
//    }


    /***
     * 获取service的操作信息
     * @param joinpoint
     * @return
     * @throws Exception
     */
/*    public String getServiceMethodMsg(JoinPoint joinpoint) throws Exception{
        //获取连接点目标类名
        String className =joinpoint.getTarget().getClass().getName() ;
        //获取连接点签名的方法名
        String methodName = joinpoint.getSignature().getName() ;
        //获取连接点参数
        Object[] args = joinpoint.getArgs() ;
        //根据连接点类的名字获取指定类
        Class targetClass = Class.forName(className);
        //拿到类里面的方法
        Method[] methods = targetClass.getMethods() ;

        String description = "" ;
        //遍历方法名，找到被调用的方法名
        for (Method method : methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes() ;
                if (clazzs.length==args.length){
                    //获取注解的说明
                    description = method.getAnnotation(AspectServiceAnnotation.class).decription();
                    break;
                }
            }
        }
        return description ;
    }*/



}
