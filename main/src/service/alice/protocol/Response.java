package src.service.alice.protocol;

class Response {
    private String text;
    private Boolean end_session;
    private Button[] buttons;

    Response(String text, Boolean end_session) {
        this.text = text;
        this.end_session = end_session;
    }

    void setEndSession(){ this.end_session = true; }

    void setButtons(Button[] buttons) {
        if (buttons != null)
        {
            this.buttons = buttons;
        }
    }
}
