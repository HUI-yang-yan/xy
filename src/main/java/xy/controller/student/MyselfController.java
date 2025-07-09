package xy.controller.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import xy.dto.MyselfDTO;
import xy.dto.StudentBasisDTO;
import xy.result.Result;
import xy.service.MyselfService;

@RestController
@RequestMapping("/student/myself")
@RequiredArgsConstructor
@Slf4j
public class MyselfController {
    private final MyselfService myselfService;


    @PostMapping("/update")
    @Transactional
    public Result updateMyself(@RequestBody StudentBasisDTO studentBasisDTO) {
        log.info("updateMyself:{}",studentBasisDTO);
        myselfService.updateMyself(studentBasisDTO);
        return Result.success();
    }

    @PostMapping("/send/micode")
    public Result sendMiCode(@RequestParam String email){
        log.info("sendMiCode:{}",email);
        myselfService.sendMiCode(email);
        return Result.success();
    }

    @PostMapping("/update/code")
    public Result changeMiCode(@RequestBody MyselfDTO myselfDTO){
        log.info("修改隐私信息:{}",myselfDTO);
        myselfService.updateMiCode(myselfDTO);
        return Result.success();
    }

}
