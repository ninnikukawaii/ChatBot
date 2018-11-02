package service.userAnswerProcessing;

public enum Command {
    Help("справка"),
    Quiz("викторина"),
    Score("счет"),
    Exit("выход");

    private final String name;

    Command(String name) {
        this.name = name;
    }

    public static Command parse(String name) {
        Command[] values = values();

        for (Command command: values){
            if (command.name.equals(name)) {
                return command;
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }
}
