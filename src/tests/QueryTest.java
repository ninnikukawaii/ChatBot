package tests;

import org.junit.Test;
import service.alice.protocol.Query;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryTest {
    private String gsonForTesting = CreatingGson("Привет");
    private Query query;

    private String CreatingGson(String comand){
        StringBuilder str = new StringBuilder();

        str.append("{\n");
        str.append("  \"request\": {\n");
        str.append("    \"command\": \"" + comand + "\"\n");
        str.append("  },\n");
        str.append("  \"session\": {\n");
        str.append("    \"session_id\": \"2eac48545ab60\",\n");
        str.append("    \"message_id\": 4,\n");
        str.append("    \"user_id\": \"AC9WA922E16381DC\"\n");
        str.append("  },\n");
        str.append("  \"version\": \"1.0\"\n");
        str.append("}\n");
        return str.toString();
    }

    @Test
    public void TestIP(){
        this.query.Query(this.gsonForTesting);
        assertEquals("AC9WA922E16381DC", this.query.GetUserID());
    }

    @Test
    public void TestCreatingCommand(){
        this.query.Query(this.gsonForTesting);
        assertEquals("Привет", this.query.GetCommand());
    }

    @Test
    public void TestOnEmptyCommand(){
        this.gsonForTesting = CreatingGson("");
        this.query.Query(this.gsonForTesting);
        assertEquals("", this.query.GetCommand());
    }
}
