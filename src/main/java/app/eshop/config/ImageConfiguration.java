package app.eshop.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@EnableWebMvc
public class ImageConfiguration implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){

        Path imagesDir = Paths.get("images").toAbsolutePath();

        String imagesDirUri = imagesDir.toUri().toString();
        System.out.println(imagesDirUri);
        registry.addResourceHandler("/images/**")
                .addResourceLocations(imagesDirUri);
    }


}
