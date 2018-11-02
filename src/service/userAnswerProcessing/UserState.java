package service.userAnswerProcessing;

public class UserState {
    private UserStateType state;

    public UserState(UserStateType initialState) {
        state = initialState;
    }

    public UserStateType getState(){ return state; }

    public void updateState(UserStateType state) {
        this.state = state;
    }

    public String[] exit() {
        if (state == UserStateType.Quiz) {
            state = UserStateType.Chat;
            return new String[] {StandardResponse.QUIZ_FAREWELL};
        }
        else {
            state = UserStateType.Exit;
            return new String[] {StandardResponse.CHAT_FAREWELL};
        }
    }
}
