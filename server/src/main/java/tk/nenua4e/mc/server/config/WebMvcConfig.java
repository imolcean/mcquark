package tk.nenua4e.mc.server.config;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/**/*")
                .addResourceLocations("classpath:/resources/")
                .resourceChain(true)
                .addResolver(
                        new PathResourceResolver()
                        {
                            @Override
                            protected Resource getResource(String resourcePath, Resource location) throws IOException
                            {
                                Resource requestedResource = location.createRelative(resourcePath);
                                return requestedResource.exists() && requestedResource.isReadable()
                                        ? requestedResource
                                        : new ClassPathResource("/resources/index.html");
                            }
                        });

        registry
                .addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
