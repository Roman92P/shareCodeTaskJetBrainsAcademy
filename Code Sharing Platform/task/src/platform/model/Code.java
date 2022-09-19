package platform.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
public class Code implements Serializable, Comparable<Code> {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID Id;
    private String code;
    private String date;
    private int time;
    private int views;
    private boolean applyTimeRule;
    private boolean applyViewsRule;
    public Code(String code) {
        this.code = code;
    }


    @Override
    public int compareTo(Code o) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String date1 = this.getDate();
        String date2 = o.getDate();
        LocalDateTime parsedDate1 = LocalDateTime.parse(date1, formatter);
        LocalDateTime parsedDate2 = LocalDateTime.parse(date2, formatter);
        if (parsedDate2.isAfter(parsedDate1)) {
            return 1;
        } else  if (parsedDate2.isBefore(parsedDate1)) {
            return -1;
        }
        return 0;
    }
}