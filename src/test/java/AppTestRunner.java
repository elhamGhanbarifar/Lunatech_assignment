package test.java;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class AppTestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(Test4ProjectDB.class);
//        Result result = JUnitCore.runClasses(Test4BusinessHandler.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}
