package mon.sof;

import cn.hutool.log.LogFactory;
import io.netty.channel.ChannelFuture;
import mon.sof.project.ws.NettyConfig;
import mon.sof.project.ws.ServerBootStrap;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.InetSocketAddress;

@ComponentScan(basePackages = {"mon.sof.*"}) //扫描bean
@MapperScan(value = {"mon.sof.project.**.mapper"})
@EnableTransactionManagement(proxyTargetClass = true)// 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@SpringBootApplication //Spring boot 程序启动注解
public class ProjectApplication implements CommandLineRunner {

    @Autowired
    private ServerBootStrap ws;

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }
    // 注意这里的 run 方法是重载自 CommandLineRunner
    @Override
    public void run(String... args) throws Exception {

        LogFactory.get().info("Netty's ws server is listen: " + NettyConfig.WS_PORT);
        InetSocketAddress address = new InetSocketAddress(NettyConfig.WS_HOST, NettyConfig.WS_PORT);
        ChannelFuture future = ws.start(address);
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                ws.destroy();
            }
        });
        future.channel().closeFuture().syncUninterruptibly();

    }


}
