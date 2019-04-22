package com.ty.exception;

import com.ty.enums.ResultEnum;
import com.ty.model.Result;
import com.ty.utils.Utils;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * 统一异常捕获处理类
 *
 * @ClassName ExceptionHandler
 * @Author tianye
 * @Date 2019/4/22 10:56
 * @Version 1.0
 */
@RestControllerAdvice
public class ExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public void exceptionHandle(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        try {
            JSONObject json;
            if (exception instanceof ExceptionTy) {
                logger.error("自定义异常", exception);
                json = JSONObject.fromObject(new Result(((GlobalException) exception).getErrorCode(), exception.getMessage()));
            } else if (exception instanceof GlobalException) {
                logger.error("权限异常", exception);
                json = JSONObject.fromObject(new Result(((GlobalException) exception).getErrorCode(), exception.getMessage()));
            } else if (exception instanceof SQLException) {
                logger.error("数据库操作错误", exception);
                json = JSONObject.fromObject(new Result(ResultEnum.RESULT_CODE_100500.getCode(), "数据库操作异常"));
            } else {
                logger.error("未知异常", exception);
                json = JSONObject.fromObject(new Result(ResultEnum.RESULT_CODE_100500.getCode(), "系统操作异常"));
            }
//            json.put("msg", Utils.translation(json.getString("msg")));
            Utils.print(json);
        } catch (Throwable e) {

           /* if(e instanceof SQLException)
            {
                logger.error("数据库操作错误",e);
                throw new GlobalException(ResponseCodeEnum.RETURN_CODE_100500, "数据库操作异常");
            }else  if(e instanceof GlobalException)
            {
                logger.error("自定义操作异常",e);
                throw new GlobalException(((GlobalException) e).getErrorCode(), e.getMessage());
            }*/
            logger.error("系统异常", e);
            throw new GlobalException(ResultEnum.RESULT_CODE_100500, "系统异常");
        }
    }

}
