package tests;

import org.junit.Test;
import service.alice.protocol.Query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tests.TestParameters.*;

public class QueryTest {

    private String gsonForTesting = createGson();
    private Query query = new Query(this.gsonForTesting);

    @Test
    public void testUserID(){
        assertEquals(user_id, this.query.getUserID());
    }

    @Test
    public void testSessionID(){
        assertEquals(session_id, this.query.getSessionID());
    }

    @Test
    public void testCommand(){ assertEquals(command, this.query.getCommand()); }

    @Test
    public void testVersion(){ assertEquals(version, this.query.getVersion()); }


    private String createGson(){
        StringBuilder builder = new StringBuilder();

        builder.append("{\n");
        builder.append("  \"request\": {\n");
        builder.append("    \"command\": \"");
        builder.append(command).append("\"\n");



        builder.append("  },\n");
        builder.append("  \"session\": {\n");
        builder.append("    \"session_id\": \"");
        builder.append(session_id).append("\",\n");
        builder.append("    \"message_id\": ");
        builder.append(message_id).append(",\n");
        builder.append("    \"user_id\": \"");
        builder.append(user_id).append("\"\n");
        builder.append("  },\n");
        builder.append("  \"version\": ");
        builder.append(version).append("\n");
        builder.append("}\n");

        return builder.toString();
    }
}
