package Parser;

public class Train {
    String from;
    String to;
    String depDate;
    String depTime;

    public Train(String from, String to, String depDate, String depTime) {
        this.from = from;
        this.to = to;
        this.depDate = depDate;
        this.depTime = depTime;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDepDate() {
        return depDate;
    }

    public String getDepTime() {
        return depTime;
    }
}