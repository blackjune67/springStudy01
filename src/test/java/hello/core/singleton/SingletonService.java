package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonService {

    private static final SingletonService intance = new SingletonService();

    public static SingletonService getInstance() {
        return intance;
    }

    private SingletonService() {

    }

    void logic() {
        System.out.println("intance = " + intance + "싱글톤 로직!");
    }
}
