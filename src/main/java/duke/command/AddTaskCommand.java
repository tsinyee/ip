package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.TaskHandler;
import duke.util.Storage;
import duke.util.Ui;

/**
 * This class encapsulates commands that add tasks to the list.
 *
 * @author Teo Sin Yee
 */
public class AddTaskCommand extends Command {
    private final Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskHandler taskHandler, Storage storage, Ui ui) throws DukeException {
        taskHandler.addTask(task);
        storage.writeToFile(task);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof AddTaskCommand) {
            return ((AddTaskCommand) o).task.equals(this.task);
        }
        return false;
    }
}