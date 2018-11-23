package src.service.userAnswerProcessing;

import java.util.List;

public enum UserStateType {
    CHAT {

        @Override
        public List<String> getHelp() {
            return StandardResponse.CHAT_HELP;
        }
    },
    QUIZ {

        @Override
        public List<String> getHelp() {
            return StandardResponse.QUIZ_HELP;
        }
    },
    EXIT;

    public List<String> getHelp() {
        return null;
    }
}
