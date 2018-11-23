package src.service.alice.protocol;

import org.junit.Test;

import static src.service.alice.protocol.TestParameters.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReplyTest {

    private Session session = new Session(SESSION_ID, MESSAGE_ID, USER_ID);
    private Reply reply = new Reply(COMMAND, false, session, VERSION);

    @Test
    public void testReplyCreation(){
        assertEquals(createGson(false), reply.getGson());
    }

    @Test
    public void testReplyCreationWithButton(){
        reply.setButtons(new Button[]{Button.HELP});
        assertEquals(createGson(true), reply.getGson());
    }

    private String createGson(Boolean hasButton){
        String str =  "{\n" +
                "  \"response\": {\n" +
                "    \"text\": \"" + COMMAND + "\",\n" +
                "    \"end_session\": false";

        if (hasButton){
            str += ",\n" +
                    "    \"buttons\": [\n" +
                    "      {\n" +
                    "        \"title\": \"" + Button.HELP.getTitle() + "\",\n" +
                    "        \"hide\": true\n" +
                    "      }\n" +
                    "    ]";
        }

        str += "\n  },\n" +
                "  \"session\": {\n" +
                "    \"session_id\": \"" + SESSION_ID + "\",\n" +
                "    \"message_id\": " + MESSAGE_ID + ",\n" +
                "    \"user_id\": \"" + USER_ID + "\"\n" +
                "  },\n" +
                "  \"version\": \"" + VERSION + "\"\n" +
                "}";

        return str;
    }
}
