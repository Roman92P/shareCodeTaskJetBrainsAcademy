package platform.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Roman Pashkov created on 17.09.2022 inside the package - platform.service
 */
@Service
public class AddDateToResponseService {

    public String addCurrentDateToResponse() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return now.format(dateTimeFormatter);
    }
}
