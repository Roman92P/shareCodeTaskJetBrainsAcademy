package platform.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CodeDto {
    private String code;
    private String date;
    private int time;
    private int views;
}
