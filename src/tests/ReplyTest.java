package tests;

import org.junit.Test;
import service.alice.protocol.Button;
import service.alice.protocol.Reply;
import service.alice.protocol.Session;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReplyTest {
    private Reply reply;

    @Test
    public void testOnc(){
        Session session = new Session("2eac48545ab60", 4, "AC9WA922E16381DC");
        this.reply = new Reply("", false, session, "1.0");
        assertEquals(createGson("", false), this.reply.convertToGson());
    }

    @Test
    public void testOnSetButton(){
        Session session = new Session("2eac48545ab60", 4, "AC9WA922E16381DC");
        this.reply = new Reply("", false, session, "1.0");
        this.reply.setButtons(new Button[]{Button.showHelp});
        assertEquals(createGson("", true), this.reply.convertToGson());

    }

    private String createGson(String command, Boolean flagOfButton){
        StringBuilder str = new StringBuilder();

        str.append("{\n");
        str.append("  \"response\": {\n");
        str.append("    \"text\": \"" + command + "\",\n");
        if (flagOfButton){
            str.append("    \"end_session\": false,\n");
            str.append("    \"buttons\": [\n");
            str.append("      {\n");
            str.append("        \"title\": \"Показать справку\",\n");
            str.append("        \"payload\": {\n");
            str.append("          \"command\": \"справка\"\n");
            str.append("        }\n");
            str.append("      }\n");
            str.append("    ]\n");
        }
        else {
            str.append("    \"end_session\": false\n");
        }
        str.append("  },\n");
        str.append("  \"session\": {\n");
        str.append("    \"session_id\": \"2eac48545ab60\",\n");
        str.append("    \"message_id\": 4,\n");
        str.append("    \"user_id\": \"AC9WA922E16381DC\"\n");
        str.append("  },\n");
        str.append("  \"version\": \"1.0\"\n");
        str.append("}");
        return str.toString();
    }
}
