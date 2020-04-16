package ioc.class008;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SesionController {

    @RequestMapping("/testSession")
    @ResponseBody
    public String test(){
        return this.toString();
    }
}
