package platform.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.model.Code;
import platform.model.CodeDto;
import platform.model.CodeIdDto;
import platform.service.AddDateToResponseService;
import platform.service.CodeService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/code")
public class CodeController {

    private final Code code;
    private final AddDateToResponseService addDateToResponseService;
    private final CodeService codeService;
    private final ObjectMapper mapper;

    public CodeController(Code code, AddDateToResponseService addDateToResponseService, CodeService codeService, ObjectMapper mapper) {
        this.code = code;
        this.addDateToResponseService = addDateToResponseService;
        this.codeService = codeService;
        this.mapper = mapper;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Code> getCodeEndpoint() {
        code.setDate(addDateToResponseService.addCurrentDateToResponse());
        return ResponseEntity.ok(code);
    }

    @PostMapping(path = "/new", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<CodeIdDto> addNewCodeSnippet(@RequestBody Code code) {
        Code savedCode = codeService.saveNewCodeSnippet(code);
        CodeIdDto codeIdDto = mapper.convertValue(savedCode, CodeIdDto.class);
        return ResponseEntity.ok(codeIdDto);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<CodeDto> getParticularCodeSnippet(@PathVariable UUID id) {
        Code codeById1 = codeService.getCodeById(id);
        CodeDto codeDto = mapper.convertValue(codeById1, CodeDto.class);
        return ResponseEntity.ok(codeDto);
    }

    @GetMapping(path = "/latest", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<CodeDto>> getTenLatestCodes() {
        List<Code> latestCodes = codeService.get10LatestAddedCodes();
        List<CodeDto> latest10CodeDtos = latestCodes.stream().map(c -> mapper.convertValue(c, CodeDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(latest10CodeDtos);
    }
}

