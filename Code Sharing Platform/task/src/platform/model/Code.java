package platform.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class Code implements Serializable {

    @JsonProperty
    private String code;

    private String date;


    public Code(String code) {
        this.code = code;
    }
}
