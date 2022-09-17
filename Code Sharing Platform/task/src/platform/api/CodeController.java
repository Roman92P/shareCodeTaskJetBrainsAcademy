package platform.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.model.Code;
import platform.service.AddDateToResponseService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/api/code")
public class CodeController {

    private final Code code;
    private AddDateToResponseService addDateToResponseService;

    public CodeController(Code code, AddDateToResponseService addDateToResponseService) {
        this.code = code;
        this.addDateToResponseService = addDateToResponseService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Code> getCodeEndpoint() {
        code.setDate(addDateToResponseService.addCurrentDateToResponse());
        return ResponseEntity.ok(code);
    }

    @PostMapping(path = "/new", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity addNewCodeSnippet(@RequestBody Code code) {
        this.code.setCode(code.getCode());
        return ResponseEntity.ok("{}");
    }
}
