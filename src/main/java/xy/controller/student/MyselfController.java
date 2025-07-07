package xy.controller.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/update/micode")
    @Transactional
    public Result updateMiCode(@RequestBody MyselfDTO myselfDTO) {
        log.info("updateMiCode:{}",myselfDTO);
        myselfService.updateMiCode(myselfDTO);
        return Result.success();
    }

}
