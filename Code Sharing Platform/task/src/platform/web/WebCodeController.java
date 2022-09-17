package platform.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import platform.model.Code;
import platform.service.AddDateToResponseService;

@Controller
public class WebCodeController {

    private final Code code;
    private final AddDateToResponseService addDateToResponseService;

    public WebCodeController(Code code, AddDateToResponseService addDateToResponseService) {
        this.code = code;
        this.addDateToResponseService = addDateToResponseService;
    }

    @RequestMapping("/code")
    public String returnHtmlPageWithCodeExample(Model model) {
        String code = this.code.getCode();
        model.addAttribute("code", code);
        model.addAttribute("loadDate", addDateToResponseService.addCurrentDateToResponse());
        return "code";
    }

}
