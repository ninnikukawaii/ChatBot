package tests;

import org.junit.Test;
import service.alice.protocol.Query;
import service.alice.protocol.Request;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class QueryTest {
    private String gsonForTesting = creatingGson("Привет", "non");
    private Query query = new Query(this.gsonForTesting);

    private String creatingGson(String comand, String payload){
        StringBuilder str = new StringBuilder();

        str.append("{\n");
        str.append("  \"request\": {\n");
        str.append("    \"command\": \"" + comand + "\",\n");

        if (payload.equals("non")){
            str.append("    \"payload\": {}\n");
        }
        else{
            str.append("    \"payload\": {\n");
            str.append("      \"command\": \"" + payload + "\"\n");
            str.append("    }\n");
        }
        str.append("  },\n");
        str.append("  \"session\": {\n");
        str.append("    \"session_id\": \"2eac48545ab60\",\n");
        str.append("    \"message_id\": 4,\n");
        str.append("    \"user_id\": \"AC9WA922E16381DC\"\n");
        str.append("  },\n");
        str.append("  \"version\": \"1.0\"\n");
        str.append("}\n");
        System.out.println(str.toString());
        return str.toString();
    }

    @Test
    public void testIP(){
        creatingGson("", "non");
        assertEquals("AC9WA922E16381DC", this.query.getUserID());
    }

    @Test
    public void testCreatingCommand(){
        assertEquals("Привет", this.query.GetCommand());
    }

    @Test
    public void testOnEmptyCommand(){
        this.gsonForTesting = creatingGson("", "non");
        this.query = new Query(this.gsonForTesting);
        assertEquals("", this.query.GetCommand());
    }

    @Test
    public void testOnEmptyPayload(){
        this.query = new Query(creatingGson("", "non"));
        assertFalse(this.query.havePayload());
    }

    @Test
    public void testOnPayload(){
        this.gsonForTesting = creatingGson("", "Викторина");
        this.query = new Query(this.gsonForTesting);
        assertTrue(this.query.havePayload());
        Request request = new Request("Викторина");
        assertEquals("Викторина", this.query.getPayload());
    }
}

