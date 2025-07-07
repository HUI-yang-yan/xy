package xy.controller.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xy.dto.AwardDTO;
import xy.result.Result;
import xy.service.AwardService;

import java.util.List;

@RestController
@RequestMapping("/student/award")
@Slf4j
@RequiredArgsConstructor
public class AwardController {

    private final AwardService awardService;

    /**
     * 根据年份获取到奖项详情
     * @param year
     * @return
     */
    @GetMapping
    public Result getAward(@RequestParam int year, @RequestParam int level) {
        log.info("开始根据年份={},等级={}查询奖项", year, level);
        List<AwardDTO> list = awardService.getAward(year,level);
        return Result.success(list);
    }
}
