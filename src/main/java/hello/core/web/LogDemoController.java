package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LoggerService loggerService;
    private final ObjectProvider<MyLogger> myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        MyLogger myLoggerObject = myLogger.getObject();
        String requestURL = request.getRequestURL().toString();
        myLoggerObject.setRequestURL(requestURL);
//        myLogger.setRequestURL(requestURL);

        myLoggerObject.log("controller test");
        loggerService.logic("test id");
        return "OK";
    }
}
