import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

/**
 * This class encapsulates a deadline
 * (an event that needs to be done before a specific time)
 * e.g., submit IP by 19/8/21 2359
 *
 * @author Teo Sin Yee
 * @version CS2103T AY21/22 Semester 1
 */
public class Deadline extends Task {
    private final String dueDate;

    /**
     * Instantiates a new Deadline task.
     *
     * @param name the subject of the task.
     * @param dueDate the due date.
     */
    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = parseDueDateToString(dueDate);
    }

    private String parseDueDateToString(String str) {
        DateFormat sdf;
        DateFormat reformattedSdf;
        Date date;
        if (str.split("\\s+").length == 2) {
            sdf = new SimpleDateFormat("yyyy-MM-dd hhmm");
            reformattedSdf = new SimpleDateFormat("dd MMM yyyy, h.mm aa");
        } else {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            reformattedSdf = new SimpleDateFormat("dd MMM yyyy");
        }
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            System.out.println("wrong date format");
            return str;
        }
        return reformattedSdf.format(date);
    }

    /**
     * String representation of a deadline.
     *
     * @return String representation of a deadline
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), dueDate);
    }
}
