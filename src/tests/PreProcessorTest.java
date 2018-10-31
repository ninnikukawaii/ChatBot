package tests;

import org.junit.Test;
import service.PreProcessor;
import service.exceptions.QuizParsingException;

public class PreProcessorTest {
    private PreProcessor preProcessor = new PreProcessor();

    @Test
    public void TestingPreprocessor() throws QuizParsingException {
        StringBuilder str = new StringBuilder();

        str.append("{\n");
        str.append("  \"request\": {\n");
        str.append("    \"command\": \"" + "Справка".toLowerCase() + "\"\n");
        str.append("  },\n");
        str.append("  \"session\": {\n");
        str.append("    \"session_id\": \"2eac48545ab60\",\n");
        str.append("    \"message_id\": 4,\n");
        str.append("    \"user_id\": \"AC9WA922E16381DC\"\n");
        str.append("  },\n");
        str.append("  \"version\": \"1.0\"\n");
        str.append("}\n");
        System.out.println(str);

        String str1 = preProcessor.HandleRequest(str.toString());
        str1 = preProcessor.HandleRequest(str.toString());
        System.out.println(str1);

    }
}
