package mon.sof.common.tool;

import io.undertow.server.DefaultByteBufferPool;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;
import mon.sof.common.filter.Filter;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class HandlerApplication implements WebMvcConfigurer, WebServerFactoryCustomizer<UndertowServletWebServerFactory> {

    /**
     * 拦截器
     *
     * @Author zhangxiaomei
     * @Date 2020-03-11 12:58:49
     * @Param
     * @Return
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(filter())
                .addPathPatterns("/**");    // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
    }

    /**
     * 上传图片显示
     *
     * @Author zhangxiaomei
     * @Date 2020-03-11 12:59:04
     * @Param
     * @Return
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(Constant.PIC_UPLOAD).addResourceLocations("file:" + Constant.PIC_PATH);
    }

    /**
     * 跨域
     *
     * @Author zhangxiaomei
     * @Date 2020-03-11 12:59:25
     * @Param
     * @Return
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
                .maxAge(3600);
    }

    /**
     * undertow中间件pool
     *
     * @Author zhangxiaomei
     * @Date 2020-03-11 12:59:51
     * @Param
     * @Return
     */
    @Override
    public void customize(UndertowServletWebServerFactory factory) {
        factory.addDeploymentInfoCustomizers(deploymentInfo -> {
            WebSocketDeploymentInfo webSocketDeploymentInfo = new WebSocketDeploymentInfo();
            webSocketDeploymentInfo.setBuffers(new DefaultByteBufferPool(false, 1024));
            deploymentInfo.addServletContextAttribute("io.undertow.websockets.jsr.WebSocketDeploymentInfo", webSocketDeploymentInfo);
        });
    }

    /**
     * filter
     *
     * @Author zhangxiaomei
     * @Date 2020-03-11 13:00:20
     * @Param
     * @Return
     */
    @Bean
    public Filter filter() {
        return new Filter();
    }
}
