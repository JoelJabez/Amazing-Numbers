import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import util.Checker;
import util.LinesChecker;
import util.RegexChecker;
import util.UserProgram;

import java.util.Random;
import java.util.stream.LongStream;

public final class NumbersTest extends StageTest {
    private static final Random random = new Random();
    private static final long RANDOM_TESTS = 20;
    private static final long FIRST_NUMBERS = 15;

    private static final Checker ASK_FOR_NUMBER = new RegexChecker(
            "enter( a)? natural number",
            "The program should ask the user to enter a natural number."
    );
    private static final Checker ERROR_MESSAGE = new RegexChecker(
            "is( not|n't) natural",
            "Number {0} is not natural. The program should print an error message."
    );
    private static final Checker PROPERTIES_OF = new RegexChecker(
            "properties of \\d",
            "The first line of number''s properties should contains \"Properties of {0}\"."
    );
    private static final Checker PROFILE_LINES = new LinesChecker(NumberProperty.values().length + 1);

    private static final Checker FINISHED = new Checker(UserProgram::isFinished,
            "The program should finish."
    );
    private final long[] notNaturalNumbers = {-1, -2, -3, -4, -5};

    @DynamicTest(order = 1)
    CheckResult zeroTest() {
        return new UserProgram()
                .start()
                .check(ASK_FOR_NUMBER)
                .execute(0)
                .check(new RegexChecker("is( not|n't) natural",
                        "Only computers and robots start counting from zero. " +
                                "For humans the natural numbers starts from one. " +
                                "Expected message: \"... is not natural\""))
                .check(FINISHED)
                .result();
    }


    private long[] getNumbers() {
        return LongStream.concat(
                LongStream.range(1, FIRST_NUMBERS),
                random.longs(RANDOM_TESTS, 1, Short.MAX_VALUE)
        ).toArray();
    }

    @DynamicTest(data = "getNumbers", order = 20)
    CheckResult naturalNumbersTest(long number) {
        return new UserProgram()
                .start()
                .check(ASK_FOR_NUMBER)
                .execute(number)
                .check(PROPERTIES_OF)
                .check(PROFILE_LINES)
                .check(new PropertiesChecker(number))
                .check(FINISHED)
                .result();
    }

}
