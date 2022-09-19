package platform.service;

import org.springframework.stereotype.Service;
import platform.model.Code;
import platform.repo.CodeRepo;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CodeServiceImpl implements CodeService {

    private final CodeRepo codeRepo;
    private final AddDateToResponseService addDateToResponseService;


    public CodeServiceImpl(CodeRepo codeRepo, AddDateToResponseService addDateToResponseService) {
        this.codeRepo = codeRepo;
        this.addDateToResponseService = addDateToResponseService;
    }

    @Override
    public Code getCodeById(UUID id) {
        Optional<Code> codeById = codeRepo.findById(id);
        if (codeById.isEmpty()) {
            throw new EntityNotFoundException();
        }
        Code code = codeById.get();
        if (codeById.get().isApplyTimeRule()) {
            code = applyCurrentCodeSecondsRemain(code);
        }
        if (code.isApplyViewsRule())
            code = applyMinusOneForCodeSnippet(code);
        validateIfCodeSnippetShouldBeRemoved(code);
        return code;
    }

    private void validateIfCodeSnippetShouldBeRemoved(Code code) {
        if (code.isApplyViewsRule()) {
            if (code.getViews() < 0) {
                removeCodeSnippet(code);
                throw new EntityNotFoundException();
            }
        }
        if (code.isApplyTimeRule()) {
            String date = code.getDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
            LocalDateTime dateTime1 = dateTime.plusSeconds(code.getTime());
            if (dateTime1.isBefore(LocalDateTime.now())) {
                removeCodeSnippet(code);
                throw new EntityNotFoundException();
            }
        }
    }

    @Override
    public Code saveNewCodeSnippet(Code code) {
        String dateTime = addDateToResponseService.addCurrentDateToResponse();
        code.setDate(dateTime);
        code.setApplyViewsRule(code.getViews() > 0);
        code.setApplyTimeRule(code.getTime() > 0);
        return codeRepo.save(code);
    }

    @Override
    public List<Code> get10LatestAddedCodes() {
        Iterable<Code> all = codeRepo.findAll();
        List<Code> collect = StreamSupport.stream(all.spliterator(), false)
                .collect(Collectors.toList());
        Collections.reverse(collect);
        List<Code> collect1 = collect.stream().filter(code -> !code.isApplyTimeRule() && !code.isApplyViewsRule()).collect(Collectors.toList());
        List<Code> collectRes = collect1.stream().limit(10).collect(Collectors.toList());
        return collectRes;
    }

    @Override
    public Code applyMinusOneForCodeSnippet(Code code) {
        code.setViews(code.getViews() - 1);
        return codeRepo.save(code);
    }

    @Override
    public Code applyCurrentCodeSecondsRemain(Code code) {
        String date = code.getDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        LocalDateTime now = LocalDateTime.now();
        long l = now.toEpochSecond(ZoneOffset.UTC);
        long l1 = dateTime.toEpochSecond(ZoneOffset.UTC);
        long result = l - l1;
        code.setTime(code.getTime() - (int) result);
        return codeRepo.save(code);
    }

    @Override
    public void removeCodeSnippet(Code code) {
        codeRepo.delete(code);
    }
}
