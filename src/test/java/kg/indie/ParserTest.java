package kg.indie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParserTest {

    Parser parser;

    @BeforeEach
    public void init() {
        parser = new Parser();
    }

    @Test
    public void parserTest() {
        parser.parse("(human(head(eyes))(body)(legs))");;
    }
}
