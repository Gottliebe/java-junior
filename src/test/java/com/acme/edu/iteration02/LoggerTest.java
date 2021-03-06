package com.acme.edu.iteration02;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {

    String separator = System.lineSeparator();

    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion



    @Test
    public void shouldLogSequentIntegersAsSum() throws IOException {
        //region when
        Logger.log("str 1");
        Logger.log(1);
        Logger.log(2);
        Logger.log("str 2");
        Logger.log(0);
        Logger.flush();
        //endregion


        // tod contains && separator
        //region then
        assertSysoutEquals(
            "string: str 1" + separator +
            "primitive: 3" +separator +
            "string: str 2"+separator +
            "primitive: 0" + separator
        );
        //endregion
    }

    @Ignore
    @Test
    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() {
        //region when
        Logger.log("str 1");
        Logger.log(10);
        Logger.log(Integer.MAX_VALUE);
        Logger.log("str 2");
        Logger.log(0);
        Logger.flush();
        //endregion

        //region then
        assertSysoutEquals(
            "string: str 1" + separator  +
            "primitive: 10" + separator  +
            "primitive: " + Integer.MAX_VALUE + "" + separator  +
            "string: str 2" + separator  +
            "primitive: 0" + separator
        );
        //endregion
    }

    @Ignore
    @Test
    public void shouldLogCorrectlyByteOverflowWhenSequentBytes() {
        //region when
        Logger.log("str 1");
        Logger.log((byte)10);
        Logger.log((byte)Byte.MAX_VALUE);
        Logger.log("str 2");
        Logger.log(0);
        Logger.flush();
        //endregion

        //region then
        assertSysoutEquals(
            "string: str 1" + separator  +
            "primitive: 10" + separator  +
            "primitive: "+Byte.MAX_VALUE + "" + separator  +
            "string: str 2" + separator  +
            "primitive: 0" + separator
        );
        //endregion
    }

    @Test
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws IOException {
        //region when
        Logger.log("str 1");
        Logger.log("str 2");
        Logger.log("str 2");
        Logger.log(0);
        Logger.log("str 2");
        Logger.log("str 3");
        Logger.log("str 3");
        Logger.log("str 3");
        Logger.flush();
        //endregion

        //region then
        assertSysoutEquals(
            "string: str 1" + separator  +
            "string: str 2 (x2)" + separator  +
            "primitive: 0" + separator  +
            "string: str 2" + separator  +
            "string: str 3 (x3)" + separator
        );
        //endregion
    }


}