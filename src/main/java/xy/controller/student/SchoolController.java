package xy.controller.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xy.dto.SchoolDTO;
import xy.pojo.School;
import xy.result.Result;
import xy.service.SchoolService;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/student/school")
@RestController
@Slf4j
@RequiredArgsConstructor
public class SchoolController {
    //页面应该有两个接口
    private final SchoolService schoolService;
    /**
     * 查询九所学校信息
     * @return
     */
    @GetMapping("/union")
    public Result<List<SchoolDTO>> getUnion() {
        log.info("开始查询联合学校");
        List<SchoolDTO> list = schoolService.getUnion();
        return Result.success(list);
    }

    @GetMapping("/list")
    public Result getAllSchool(){
        log.info("开始获取全部学校信息");
        List<SchoolDTO> list = schoolService.getAllSchool();
        return Result.success(list);
    }
}
