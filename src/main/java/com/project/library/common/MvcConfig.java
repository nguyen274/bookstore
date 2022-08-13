package com.project.library.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String dirName = "book-photos";
        Path bookPhotosDir = Paths.get(dirName);
        String bookPhotosPath = bookPhotosDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" +bookPhotosPath + "/");
    }
}
