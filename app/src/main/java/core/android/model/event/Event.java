package core.android.model.event;


public class Event {

    public static final int MOCKUP = 1;

    private String message;
    private int type;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Event(String message, int type) {
        this.message = message;
        this.type = type;
    }

    public Event() {
    }
}
