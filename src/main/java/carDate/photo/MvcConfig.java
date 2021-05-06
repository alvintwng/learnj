package carDate.photo;

import java.nio.file.*;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory("photos", registry);
    }
     
    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        if (dirName.startsWith("../")) dirName = dirName.replace("../", "");
         
        registry
        	.addResourceHandler("/" + dirName + "/**")
        	.addResourceLocations("file:"+ uploadPath + "/");
        	//.addResourceLocations("file:/"+ uploadPath + "/"); //was windows user?
        
        	System.out.println("\t====> "+"file:"+ uploadPath + "/");
    }
}