package group.urbancompany.Demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@RestController
public class HelloController {

    @Value("${spring.application.name:Unknown}")
    private String appName;

    @GetMapping("/hello")
    public String sayHello() {

        return "Hello World :  " + appName;
    }
}
