package labo2.singleton;

import java.util.Stack;

import labo2.command.Command;

/**
 * The CommandManager class is responsible for executing and undoing commands.
 * It uses a stack to keep track of executed commands.
 */
public class CommandManager {
    private static CommandManager instance;

    private Stack<Command> commands = new Stack<>();

    /**
     * Private constructor to prevent instantiation.
     */
    private CommandManager() {
    }

    /**
     * Returns the singleton instance of the CommandManager.
     *
     * @return the singleton instance of the CommandManager
     */
    public static CommandManager getInstance() {
        if (instance == null) {
            instance = new CommandManager();
        }
        return instance;
    }

    /**
     * Executes a command and adds it to the stack of executed commands.
     *
     * @param command the command to execute
     */
    public void executeCommand(Command command) {
        command.execute();
        commands.push(command);
    }

    /**
     * Undoes the most recent command that was executed.
     */
    public void undoCommand() {
        if (!commands.isEmpty()) {
            Command command = commands.pop();
            command.undo();
        }
    }
}
