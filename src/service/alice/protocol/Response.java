package service.alice.protocol;

class Response {
    private String text;

    void SetText(String text){
        this.text = text;
    }

    String GetText(){
        return this.text;
    }
}
