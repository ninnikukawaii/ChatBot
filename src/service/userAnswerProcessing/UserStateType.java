package service.userAnswerProcessing;

public enum UserStateType {
    Chat {

        @Override
        public String[] getHelp() {
            return StandardResponse.CHAT_HELP;
        }
    },
    Quiz{

        @Override
        public String[] getHelp() {
            return StandardResponse.QUIZ_HELP;
        }
    },
    Exit;

    public String[] getHelp() {
        return null;
    }
}
