package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggerService {

    private final ObjectProvider<MyLogger> myLogger;

    public void logic(String id) {
        MyLogger myLoggerObject= myLogger.getObject();
        myLoggerObject.log("service id = " + id);
//        myLogger.log("service id = " + id);
    }
}
