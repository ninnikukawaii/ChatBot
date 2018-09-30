package service.enums;

import java.util.HashMap;
import java.util.Map;

public enum Command {
    Help,
    Quiz,
    Score,
    Exit;

    private static final Map<String, Command> commands;
    static {
        commands = new HashMap<>();
        commands.put("справка", Command.Help);
        commands.put("викторина", Command.Quiz);
        commands.put("счет", Command.Score);
        commands.put("выход", Command.Exit);
    }

    public static Command parse(String name) {
        return commands.get(name);
    }
}
