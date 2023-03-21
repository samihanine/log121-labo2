package labo2.command;

/**
 * The Command interface defines the methods for executing a command and for
 * undoing a previously executed command.
 */
public interface Command {

    /**
     * Executes the command.
     */
    void execute();

    /**
     * Undoes a previously executed command.
     */
    void undo();
}
