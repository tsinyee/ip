import java.util.ArrayList;
import java.util.List;

/**
 * This class encapsulates a Task Handler that manages all the tasks Duke can handle.
 *
 * @author Teo Sin Yee
 * @version CS2103T AY21/22 Semester 1
 */
public class TaskHandler {
    // Initialising constants
    private static final String NO_TASKS_FOUND = "Nothing in the list... :( Type todo/event/deadline to add something first! :^)";
    private static final String TASK_ADDED_MESSAGE = "Voila! ^_^ I've added this task:\n\t  %s\n\tYou currently have %d task(s) in the list.";
    private static final String TASK_DONE_MESSAGE = "Good Job! :D I've marked this task as done:\n\t  %s\n\tYou currently have %d undone task(s) in the list.";
    private static final String TASK_DELETED_MESSAGE = "Voila! ^_^ I've deleted this task:\n\t  %s\n\tYou currently have %d task(s) in the list.";
    private static final String TASK_LIST = "Here are the task(s) in your list! n_n\n\t";
    private static final String NO_SUCH_TASK_ID = "Awwww, I can't seem to find this task index. Try again? U_U";
    private static final String NEGATIVE_TASK_ID = "Please enter a number starting from 1! V_V";

    private ArrayList<Task> taskList;
    private final Storage storage;

    /** Instantiates a new Task Handler **/
    public TaskHandler(ArrayList<Task> taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Adds the given task to the task list
     * and prints out a message that informs the user that task is added successfully.
     *
     * @param task the task given to Duke.
     */
    public void addTask(Task task) {
        taskList.add(task);
        Ui.prettify(String.format(TASK_ADDED_MESSAGE, task.toString(), getTotalTasksCount()));
    }

    /**
     * Gets the number of incomplete tasks
     *
     * @return the number of incomplete tasks.
     */
    private int getUndoneTasksCount() {
        int count = 0;
        for (Task task : taskList) {
            if (!task.isComplete()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Gets the total number of tasks.
     *
     * @return the total number of tasks.
     */
    private int getTotalTasksCount() {
        return taskList.size();
    }

    /**
     * Marks tasks as done
     *
     * @param taskNumber the index of the task to be mark as done
     * @throws DukeException for invalid task indexes given by the user.
     */
    public void markTaskAsDone(int taskNumber) throws DukeException {
        if (taskNumber < 1) {
            throw new DukeException(NEGATIVE_TASK_ID);
        } else if (taskList.size() == 0) {
            throw new DukeException(NO_TASKS_FOUND);
        } else if (taskNumber > taskList.size()) {
            throw new DukeException(NO_SUCH_TASK_ID);
        }
        int index = taskNumber - 1;
        Task t = taskList.get(index);
        t.markAsDone();
        Ui.prettify(String.format(TASK_DONE_MESSAGE, t, getUndoneTasksCount()));
    }

    /**
     * Deletes tasks at indexes specified by user.
     *
     * @param taskNumber the index of the task to be deleted.
     * @throws DukeException for invalid task indexes given by the user.
     */
    public void deleteTask(int taskNumber) throws DukeException {
        if (taskNumber < 1) {
            throw new DukeException(NEGATIVE_TASK_ID);
        } else if (taskList.size() == 0) {
            throw new DukeException(NO_TASKS_FOUND);
        } else if (taskNumber > taskList.size()) {
            throw new DukeException(NO_SUCH_TASK_ID);
        }
        int index = taskNumber - 1;
        Task t = taskList.remove(index);
        Ui.prettify(String.format(TASK_DELETED_MESSAGE, t, getTotalTasksCount()));
    }

    public void updateData() throws DukeException {
        storage.updateData(taskList);
    }
 
    public void printTasks() {
        Ui.printTaskList(taskList);
    }
}
