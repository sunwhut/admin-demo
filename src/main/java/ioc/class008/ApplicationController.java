package ioc.class008;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApplicationController {

    @RequestMapping("/testApplication")
    @ResponseBody
    public String test(){
        return this.toString();
    }
}
