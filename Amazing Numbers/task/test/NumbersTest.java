import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import util.Checker;
import util.RegexChecker;
import util.TextChecker;
import util.UserProgram;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.LongStream;

public final class NumbersTest extends StageTest {
    private static final Random random = new Random();

    private static final int NEGATIVE_NUMBERS_TESTS = 5;
    private static final int FIRST_NUMBERS = 15;
    private static final int RANDOM_TESTS = 10;

    private static final Checker WELCOME = new TextChecker("Welcome to Amazing Numbers!");

    private static final Function<UserProgram, UserProgram> HELP =
            new TextChecker("Supported requests")
                    .andThen(new TextChecker("enter a natural number"))
                    .andThen(new TextChecker("enter 0 to exit"));

    private static final Checker ASK_REQUEST = new TextChecker(
            "enter a request",
            "The program should print \"Enter a request\" instead of asking for a natural number."
    );
    private static final Checker ERROR_FIRST = new RegexChecker(
            "The first (parameter|number) should be a natural number or zero",
            "The first parameter \"{0}\" is wrong. The program should print an error message."
    );
    private static final Checker PROPERTIES_OF = new RegexChecker(
            "properties of \\d",
            "The first line of number''s properties should contain \"Properties of {0}\"."
    );
    private static final Checker RUNNING = new Checker(Predicate.not(UserProgram::isFinished),
            "The program should continue to work till the user enter \"0\"."
    );
    private static final Checker FINISHED = new Checker(UserProgram::isFinished,
            "The program should finish when the user entered \"0\"."
    );
    private final UserProgram program = new UserProgram();

    // Stage #3

    @DynamicTest(order = 5)
    CheckResult welcomeTest() {
        return program
                .start()
                .check(WELCOME)
                .check(HELP)
                .check(RUNNING)
                .check(ASK_REQUEST)
                .execute(0)
                .check(FINISHED)
                .result();
    }

    @DynamicTest(repeat = NEGATIVE_NUMBERS_TESTS, order = 10)
    CheckResult notNaturalNumbersTest() {
        long negativeNumber = -random.nextInt(Byte.MAX_VALUE) - 1L;
        return program
                .start()
                .check(WELCOME)
                .check(HELP)
                .check(ASK_REQUEST)
                .execute(negativeNumber)
                .check(ERROR_FIRST)
                .check(RUNNING)
                .check(ASK_REQUEST)
                .execute(0)
                .check(FINISHED)
                .result();
    }

    @DynamicTest(order = 20)
    CheckResult firstNumbersTest() {
        final var numbers = LongStream.range(1, FIRST_NUMBERS);

        program.start().check(WELCOME).check(HELP);

        numbers.forEach(number -> program
                .check(ASK_REQUEST)
                .execute(number)
                .check(PROPERTIES_OF)
                .check(new PropertiesChecker(number))
                .check(RUNNING));

        return program
                .check(ASK_REQUEST)
                .execute(0)
                .check(FINISHED)
                .result();
    }

    private long[] getLongNumbers() {
        return random.longs(RANDOM_TESTS, Integer.MAX_VALUE, Long.MAX_VALUE).toArray();
    }

    @DynamicTest(data = "getLongNumbers", order = 30)
    CheckResult longNumbersTest(long number) {
        return new UserProgram()
                .start()
                .check(ASK_REQUEST)
                .execute(number)
                .check(PROPERTIES_OF)
                .check(new PropertiesChecker(number))
                .check(ASK_REQUEST)
                .execute(0)
                .check(FINISHED)
                .result();
    }

}
