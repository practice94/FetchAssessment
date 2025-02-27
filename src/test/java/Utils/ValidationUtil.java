package Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;


public class ValidationUtil {

    public static void log(String description){
        Logger logger = LogManager.getLogger(ValidationUtil.class);
        logger.info(description);
    }

    public static void validate(String description, Object actual, String expected){
        log(description);
        Assert.assertEquals(actual,expected);
    }

    public static void validate(String description, boolean actual, boolean expected) {
        log(description);

        if(!expected)
            Assert.assertFalse(actual);
        else
            Assert.assertTrue(actual);
    }

    public static void validate(String description, boolean actual) {
        log(description);
        Assert.assertTrue(actual);

    }
}
