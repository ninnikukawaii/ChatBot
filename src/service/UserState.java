package service;

public class UserState {
    private service.enums.UserState state;

    public UserState(service.enums.UserState initialState) {
        state = initialState;
    }

    public service.enums.UserState getState(){ return state; }

    public void updateState(service.enums.UserState state) {
        this.state = state;
    }

    public String[] exit() {
        if (state == service.enums.UserState.Quiz) {
            state = service.enums.UserState.Chat;
            return new String[] {StandardResponse.QUIZ_FAREWELL};
        }
        else {
            state = service.enums.UserState.Exit;
            return new String[] {StandardResponse.CHAT_FAREWELL};
        }
    }
}
