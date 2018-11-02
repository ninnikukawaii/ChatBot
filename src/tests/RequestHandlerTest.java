package tests;

import org.junit.Test;
import service.alice.RequestHandler;
import service.quiz.QuizParsingException;

public class RequestHandlerTest {
    private RequestHandler requestHandler = new RequestHandler();
    //private String str  = proc();

    /*public String proc(){
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
        return str.toString();
    }*/

    @Test
    public void TestingPreprocessor() throws QuizParsingException {

        //String str1 = requestHandler.handleRequest(this.str);
        //str1 = requestHandler.handleRequest(str.toString());
        //System.out.println(str1);

    }
}
