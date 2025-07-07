package xy.controller.student;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xy.pojo.Student;
import xy.result.Result;

@RestController
@RequestMapping("/student/myself")
public class MyselfController {
    @PostMapping("/update")
    public Result updateMyself(@RequestBody final Student student) {
        // todo
        return Result.success(student);
    }
}
