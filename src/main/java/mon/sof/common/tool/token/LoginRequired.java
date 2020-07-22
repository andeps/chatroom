package mon.sof.common.tool.token;


import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Mapping
public @interface LoginRequired {
    UserTokenTypeEnum value();
}
