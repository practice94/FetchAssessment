package Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class ValidationUtil {
    private static final Logger logger = LogManager.getLogger(ValidationUtil.class);

    public static void log(String description){
        logger.info(description);
    }

    public static void validate(String description, Object actual, String expected){
        log(description+actual);
        Assert.assertEquals(actual.toString(),expected);
    }

    public static void validate(String description, boolean actual, boolean expected) {
        log(description+actual);

        if(!expected)
            Assert.assertFalse(actual);
        else
            Assert.assertTrue(actual);
    }

    public static void validate(String description, boolean actual) {
        log(description+actual);
        Assert.assertTrue(actual);

    }

    public static void warn(String description, boolean condition) {
        logger.warn(description,condition);
    }
}
