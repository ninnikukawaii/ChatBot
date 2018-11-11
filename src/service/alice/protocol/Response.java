package service.alice.protocol;

class Response {
    private String text;
    private Boolean end_session = false;
    private Button[] buttons;

    void setText(String text){
        this.text = text;
    }

    void setEndSession(){ this.end_session = true; }

    void setButtons(Button[] buttons) { this.buttons = buttons; }
}
