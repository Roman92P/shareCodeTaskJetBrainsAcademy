package platform.service;

import platform.model.Code;

import java.util.List;
import java.util.UUID;

public interface CodeService {

   Code getCodeById(UUID id);

    Code saveNewCodeSnippet(Code code);

    List<Code> get10LatestAddedCodes();

    void removeCodeSnippet(Code code);

    Code applyMinusOneForCodeSnippet(Code code);
    Code applyCurrentCodeSecondsRemain(Code code);
}
