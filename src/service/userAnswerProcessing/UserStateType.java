package service.userAnswerProcessing;

import java.util.List;

public enum UserStateType {
    Chat {

        @Override
        public List<String> getHelp() {
            return StandardResponse.CHAT_HELP;
        }
    },
    Quiz{

        @Override
        public List<String> getHelp() {
            return StandardResponse.QUIZ_HELP;
        }
    },
    Exit;

    public List<String> getHelp() {
        return null;
    }
}
