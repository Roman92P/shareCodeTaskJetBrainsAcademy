package platform.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import platform.model.Code;
import platform.service.AddDateToResponseService;
import platform.service.CodeService;

import java.util.List;
import java.util.UUID;

@Controller
public class WebCodeController {

    private final Code code;
    private final AddDateToResponseService addDateToResponseService;
    private final CodeService codeService;

    public WebCodeController(Code code, AddDateToResponseService addDateToResponseService, CodeService codeService) {
        this.code = code;
        this.addDateToResponseService = addDateToResponseService;
        this.codeService = codeService;
    }

    @RequestMapping("/code")
    public String returnHtmlPageWithCodeExample(Model model) {
        String code = this.code.getCode();
        model.addAttribute("code", code);
        model.addAttribute("loadDate", addDateToResponseService.addCurrentDateToResponse());
        return "code";
    }

    @RequestMapping("/code/new")
    public String getNewCodeForm() {
        return "code-new-form";
    }

    @RequestMapping("/code/{id}")
    public String getNCodeView(@PathVariable UUID id, Model model) {
        Code codeById = codeService.getCodeById(id);
        model.addAttribute("code", codeById);
        return "code";
    }

    @RequestMapping("/code/latest")
    public String getTenLatestCodesView(Model model) {
        List<Code> latestAddedCodes = codeService.get10LatestAddedCodes();
        model.addAttribute("codeList", latestAddedCodes);
        return "code-ten-latest";
    }


}
