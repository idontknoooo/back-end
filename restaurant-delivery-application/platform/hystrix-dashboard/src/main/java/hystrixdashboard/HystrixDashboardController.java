package hystrixdashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// http://localhost:8080/hystrix.stream
@Controller
// Redirect /hystrix to /
public class HystrixDashboardController {

    // root
    @RequestMapping("/")
    // redirect
    public String home(){
        return "forward:/hystrix";
    }
}
