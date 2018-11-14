package tests;

import org.junit.Test;
import service.alice.protocol.Button;
import service.alice.protocol.Reply;
import service.alice.protocol.Session;

import static tests.TestParameters.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReplyTest {

    private Session session = new Session(session_id, message_id, user_id);
    private Reply reply = new Reply(command, false, session, version);

    @Test
    public void testReplyCreation(){
        assertEquals(createGson(false), reply.getGson());
    }

    @Test
    public void testReplyCreationWithButton(){
        reply.setButtons(new Button[]{Button.showHelp});
        assertEquals(createGson(true), reply.getGson());
    }

    private String createGson(Boolean hasButton){
        StringBuilder builder = new StringBuilder();

        builder.append("{\n");
        builder.append("  \"response\": {\n");
        builder.append("    \"text\": \"");
        builder.append(command).append("\",\n");
        builder.append("    \"end_session\": false");

        if (hasButton){
            builder.append(",\n");
            builder.append("    \"buttons\": [\n");
            builder.append("      {\n");
            builder.append("        \"title\": \"");
            builder.append(Button.showHelp.getTitle()).append("\",\n");
            builder.append("        \"hide\": true\n");
            builder.append("      }\n");
            builder.append("    ]");
        }

        builder.append("\n  },\n");
        builder.append("  \"session\": {\n");
        builder.append("    \"session_id\": \"");
        builder.append(session_id).append("\",\n");
        builder.append("    \"message_id\": ");
        builder.append(message_id).append(",\n");
        builder.append("    \"user_id\": \"");
        builder.append(user_id).append("\"\n");
        builder.append("  },\n");
        builder.append("  \"version\": \"");
        builder.append(version).append("\"\n");
        builder.append("}");

        return builder.toString();
    }
}
