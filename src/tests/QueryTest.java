package tests;

import service.alice.protocol.Query;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tests.TestParameters.*;

public class QueryTest {

    @Test
    public void testQuery() {
        String gsonForTesting = createGson();
        Query expected = new Query(COMMAND, SESSION_ID, MESSAGE_ID, USER_ID, VERSION);
        Query actual = new Query(gsonForTesting);
        assertEquals(expected, actual);
    }

    private String createGson(){

        return "{\n" +
                "  \"request\": {\n" +
                "    \"command\": \"" + COMMAND + "\"\n" +
                "  },\n" +
                "  \"session\": {\n" +
                "    \"session_id\": \"" + SESSION_ID + "\",\n" +
                "    \"message_id\": " + MESSAGE_ID + ",\n" +
                "    \"user_id\": \"" + USER_ID + "\"\n" +
                "  },\n" +
                "  \"version\": \"" + VERSION + "\"\n" +
                "}";
    }
}
