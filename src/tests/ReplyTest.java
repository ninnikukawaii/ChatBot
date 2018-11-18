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
        reply.setButtons(new Button[]{Button.HELP});
        assertEquals(createGson(true), reply.getGson());
    }

    private String createGson(Boolean hasButton){
        String str =  "{\n" +
                "  \"response\": {\n" +
                "    \"text\": \"" + command + "\",\n" +
                "    \"end_session\": false";

        if (hasButton){
            str += ",\n" +
                    "    \"buttons\": [\n" +
                    "      {\n" +
                    "        \"title\": \"" + Button.showHelp.getTitle() + "\",\n" +
                    "        \"hide\": true\n" +
                    "      }\n" +
                    "    ]";
        }

        str += "\n  },\n" +
                "  \"session\": {\n" +
                "    \"session_id\": \"" + session_id + "\",\n" +
                "    \"message_id\": " + message_id + ",\n" +
                "    \"user_id\": \"" + user_id + "\"\n" +
                "  },\n" +
                "  \"version\": \"" + version + "\"\n" +
                "";

        return str;
    }
}
