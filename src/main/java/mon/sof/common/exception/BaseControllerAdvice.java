package mon.sof.common.exception;


import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import mon.sof.common.exception.entity.Resp;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常统一处理基类
 * @Author  zhangxiaomei
 * @Date    2020-02-25 15:58:36
 * @Param
 * @Return
 */
@RestControllerAdvice
public class BaseControllerAdvice {
    Log log = LogFactory.get();

    /**
     * 运行时异常
     * @Author  zhangxiaomei
     * @Date    2020-02-25 15:59:09
     * @Param
     * @Return
     */
    @ExceptionHandler(value = BaseException.class)
    public Resp baseExceptionHander(BaseException be){
        log.error("BaseExceptionHander" , be);
        return Resp.error(HttpStatus.HTTP_OK,"errMsg",be.getMessage());
    }


    /**
     * 全局异常
     * @Author  zhangxiaomei
     * @Date    2020-02-25 17:01:26
     * @Param
     * @Return
     */

    @ExceptionHandler(value = Exception.class)
    public Resp exceptionHander(Exception e){
        log.error("ExceptionHander",e);
        if(StrUtil.isEmpty(e.getMessage())){
            return Resp.error(HttpStatus.HTTP_INTERNAL_ERROR , "errMsg" , "程序异常，请联系管理人员");
        }
        return Resp.error(HttpStatus.HTTP_INTERNAL_ERROR , "errMsg", e.getMessage());
    }

}
