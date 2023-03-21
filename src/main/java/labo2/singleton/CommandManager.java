package labo2.singleton;

import java.util.Stack;

import labo2.command.Command;

public class CommandManager {
    private static CommandManager instance;

    private Stack<Command> commands = new Stack<>();

    private CommandManager() {
    }

    public static CommandManager getInstance() {
        if (instance == null) {
            instance = new CommandManager();
        }
        return instance;
    }

    public void executeCommand(Command command) {
        command.execute();
        commands.push(command);
    }

    public void undoCommand() {
        if (!commands.isEmpty()) {
            Command command = commands.pop();
            command.undo();
        }
    }
}
