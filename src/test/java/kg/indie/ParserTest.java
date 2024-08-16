package kg.indie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    Parser parser;

    @BeforeEach
    public void init() {
        parser = new Parser();
    }

    @Test
    public void parserTest() {
        parser.parse("(human(head(eyes))(body)(legs))", 0);
    }

    @ParameterizedTest
    @CsvSource(
            {
                    "asdsd(,asdsd",
                    "asd(sd(,asd",
                    "asdd,asdd",
            })
    public void nextStringBracketTest(String input, String expected) {
        assertEquals(parser.getNextString(input, List.of("(",")")), expected);
    }

    @ParameterizedTest
    @CsvSource(
            {
                    "asdsd<tag>,asdsd",
                    "asd</tag>sd(,asd",
                    "asdd,asdd",
            })
    public void nextStringXMLTest(String input, String expected) {
        assertEquals(parser.getNextString(input, List.of("<tag>","</tag>")), expected);
    }
}
