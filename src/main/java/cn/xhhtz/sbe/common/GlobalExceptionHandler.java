package cn.xhhtz.sbe.common;

import cn.xhhtz.sbe.exception.AuthorityException;
import cn.xhhtz.sbe.exception.DTOTransException;
import cn.xhhtz.sbe.exception.DTOValidException;
import cn.xhhtz.sbe.exception.NoLoginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * 全局异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ServerResponse handleExceptions(HttpServletRequest reqest, HttpServletResponse response, Exception ex) {

        // 获取未声明异常的实际异常类型
        if (ex instanceof UndeclaredThrowableException) {
            // 获取原始异常
            Exception trueEx = (Exception) ((UndeclaredThrowableException) ex).getUndeclaredThrowable();
            // 参数校验错误信息
            if (trueEx instanceof DTOValidException) {
                ex = trueEx;
                return ServerResponse.createByErrorMessage(errorMessageFilter(ex.getMessage()));
            }
            // 登录校验错误
            if (trueEx instanceof NoLoginException) {
                // 将未声明异常改为真实异常,否则controller层获取到 UndeclaredThrowableException 会抛出 error
                ex = trueEx;
                logger.debug(ex.getMessage());
                return ServerResponse.createByNeedLogin(ex.getMessage());
            }
            // 权限校验错误
            if (trueEx instanceof AuthorityException) {
                ex = trueEx;
                logger.debug(ex.getMessage());
                return ServerResponse.createByNoPermissions();
            }
        }
        // 参数校验错误信息 自定义参数验证
        if (ex instanceof DTOValidException) {
            return ServerResponse.createByErrorMessage(errorMessageFilter(ex.getMessage()));
        }
        // 参数校验错误信息 Valid参数校验
        if (ex instanceof MethodArgumentNotValidException) {
            return ServerResponse.createByErrorMessage(errorMessageFilter(ex.getMessage()));
        }
        // 登录验证错误信息
        if (ex instanceof NoLoginException) {
            return ServerResponse.createByErrorMessage(errorMessageFilter(ex.getMessage()));
        }
        // 传输参数转换错误信息
        if (ex instanceof DTOTransException) {
            return ServerResponse.createByErrorMessage(ex.getMessage());
        }
        // 其他异常
        logger.info(ex.getMessage());
        ex.printStackTrace();
        return ServerResponse.createByErrorMessage("请求异常");
    }

    /**
     * 异常信息过滤处理
     *
     * @param messages
     * @return
     */
    private String errorMessageFilter(String messages) {
        int index = messages.lastIndexOf("Exception:");
        if (index > 0) {
            return messages.substring(index + 1, messages.length());
        }
        index = messages.lastIndexOf(":");
        if (index > 0) {
            return messages.substring(index + 1, messages.length());
        }
        return messages;
    }

}