package xy.controller.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xy.result.Result;
import xy.service.UtilsService;

@RequestMapping("/common/utils")
@RestController
@Slf4j
@RequiredArgsConstructor
public class UtilController {
    private final UtilsService utilsService;

    @PostMapping("/ai/talk")
    public Result AITalk(@RequestParam String question){
        return Result.success();
    }
}
