package Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class ValidationUtil {
    private static final Logger logger = LogManager.getLogger(ValidationUtil.class);

    /*
    Logs description in the console
     */
    public static void log(String description){
        logger.info(description);
    }

    /*
    Validate/Assert the actual and expected result and Logs description
    */
    public static void validate(String description, Object actual, String expected){
        log(description+actual);
        Assert.assertEquals(actual.toString(),expected);
    }
    /*
    Validate/Assert the actual is true or false and Logs description
     */
    public static void validate(String description, boolean actual) {
        log(description+actual);
        Assert.assertTrue(actual);

    }
}
