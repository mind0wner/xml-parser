package Parser;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalTime;

@XmlRootElement
public class Train {
    private String from;
    private String to;
    private LocalDate depDate;
    private LocalTime depTime;


    public Train(String from, String to, LocalDate depDate, LocalTime depTime) {
        this.from = from;
        this.to = to;
        this.depDate = depDate;
        this.depTime = depTime;
    }
    @XmlElement
    public String getFrom() {
        return from;
    }
    @XmlElement
    public String getTo() {
        return to;
    }
    @XmlElement
    public LocalDate getDepDate() {
        return depDate;
    }
    @XmlElement
    public LocalTime getDepTime() {
        return depTime;
    }

    @Override
    public String toString() {
        return "Train{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", depDate=" + depDate +
                ", depTime=" + depTime +
                '}';
    }
}