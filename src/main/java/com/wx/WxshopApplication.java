package com.wx;

import com.github.pagehelper.PageHelper;
import org.springframework.boot.ApplicationHome;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
;import java.util.Properties;

@EnableAutoConfiguration
@SpringBootApplication
@EnableSwagger2
//@EnableTransactionManagement
public class WxshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxshopApplication.class, args);
		
	}
	private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1
        corsConfiguration.addAllowedHeader("*"); // 2
        corsConfiguration.addAllowedMethod("*"); // 3
        return corsConfiguration;
    }
    @Configuration
    public class ApplicationConfig extends WebMvcConfigurerAdapter {

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {

        /*
        * 说明：增加虚拟路径(经过本人测试：在此处配置的虚拟路径，用springboot内置的tomcat时有效，
        * 用外部的tomcat也有效;所以用到外部的tomcat时不需在tomcat/config下的相应文件配置虚拟路径了,阿里云linux也没问题)
        */
            ApplicationHome home = new ApplicationHome(getClass());
            String szTemp=home.getDir().getAbsolutePath();
            String szsppath = szTemp+"\\imgupload\\";
            registry.addResourceHandler("/imgupload/**").addResourceLocations("file:"+szsppath);


            //阿里云(映射路径去除盘符)
            //registry.addResourceHandler("/ueditor/image/**").addResourceLocations("/upload/image/");
            //registry.addResourceHandler("/ueditor/video/**").addResourceLocations("/upload/video/");
            super.addResourceHandlers(registry);
        }
    }
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4
        return new CorsFilter(source);
    }


}
