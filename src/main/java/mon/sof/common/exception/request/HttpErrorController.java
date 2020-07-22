package mon.sof.common.exception.request;

import mon.sof.common.exception.entity.Resp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class HttpErrorController implements ErrorController {
    private static Logger Log = LoggerFactory.getLogger(HttpErrorController.class);

    private final static String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(value = ERROR_PATH)
    public Resp error(HttpServletRequest request, HttpServletResponse response){
        Log.info("访问/error" + "  错误代码："  + response.getStatus());
        return Resp.error(response.getStatus(),"访问/error" + "错误代码："  + response.getStatus(),null);
    }
}
