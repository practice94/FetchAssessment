package Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;


public class ValidationUtil {
    private static final Logger logger = LogManager.getLogger(ValidationUtil.class);

    public static void validate(String description, Object actual, String expected){
        logger.info(description);
        Assert.assertEquals(actual,expected);
    }

    public static void validate(String description, boolean actual, boolean expected) {
        logger.info(description);

        if(!expected)
            Assert.assertFalse(actual);
        else
            Assert.assertTrue(actual);
    }

    public static void validate(String description, boolean actual) {
        logger.info(description);
        Assert.assertTrue(actual);

    }
}
