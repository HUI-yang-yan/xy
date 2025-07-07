package xy.controller.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import xy.context.BaseContext;
import xy.dto.*;
import xy.pojo.File;
import xy.result.PageResult;
import xy.result.Result;
import xy.service.CourseService;
import xy.service.FileService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/student/course")
@RestController("studentCourse")
@Slf4j
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    private final FileService fileService;


    /**
     * 查询排行榜信息
     * @return
     */
    @GetMapping("/Rank")
    public Result getRank(){
        log.info("查询排行榜信息");
        return courseService.getRank();
    }

    /**
     * 查询平时成绩和期末成绩
     *
     * @return
     */
    @GetMapping("/grade")
    public Result<Map<String,String>> getGrade() {
        log.info("查询平时成绩和期末成绩");
        return courseService.getGrade();
    }

    @PostMapping("/add/group")
    public Result AddGroup(@RequestBody GroupDTO groupDTO){
        log.info("新建小组:{}",groupDTO);
        Boolean ifSuccess = courseService.addGroup(groupDTO);
        if(ifSuccess){
            return Result.success();
        }
        return Result.error("小组名称已经存在,请更换名称!");
    }

    /**
     * 根据组号加入小组
     *
     * @param groupId
     * @return
     */
    @PostMapping("/group")
    public Result addGroup(@RequestParam Long groupId,@RequestParam String remark) {
        log.info("根据组号加入小组:{},{}", groupId,remark);
        courseService.addGroupByGroupId(groupId,remark);
        return Result.success();
    }

    /**
     * 根据课程号查询所有小组
     *
     * @param courseId
     * @return
     */
    @GetMapping("/group/list")
    public Result<List<GroupDTO>> getGroupList(@RequestParam Long courseId) {
        log.info("根据课程号查询:{}", courseId);
        List<GroupDTO> list = courseService.getGroupList(courseId);
        return Result.success(list);
    }

    @GetMapping("/files/{teacherId}/{courseId}")
    public Result<List<FileDTO>> getFilesByTeacherIdAndCourseId(@PathVariable Long teacherId, @PathVariable Long courseId) {
        log.info("根据 teacherId,courseId查询文件列表:{},{}", teacherId, courseId);
        List<FileDTO> fileDTOS = courseService.getFilesByTeacherIdAndCourseId(teacherId, courseId);
        return Result.success(fileDTOS);
    }

    /**
     * 根据id查询教师上传到文件
     *
     * @param id
     * @return
     */
    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getCourseFile(@PathVariable Long id) {
        log.info("根据id查询教师上传到文件id:{}", id);
        Optional<File> optional = fileService.getFileById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        File file = optional.get();

        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;

        if (file.getType() != null) {
            try {
                mediaType = MediaType.parseMediaType(file.getType());
            } catch (Exception e) {
                // 忽略异常，使用默认类型
            }
        }

        headers.setContentType(mediaType);
        headers.setContentDisposition(ContentDisposition.inline().filename(file.getName()).build());

        return new ResponseEntity<>(file.getContent(), headers, HttpStatus.OK);
    }


    /**
     * 返回学生基本信息
     *
     * @return
     */
    @GetMapping("/title")
    public Result<StudentBasisDTO> getStudentBasis() {
        log.info("开始获取学生基本信息:");
        Long userId = BaseContext.getCurrentId();
        StudentBasisDTO studentBasisDTO = courseService.getStudentBasis(userId);
        return Result.success(studentBasisDTO);
    }

    /**
     * 开始获取学生课程数量
     * @return
     */
    @GetMapping("/number")
    public Result<Long> getCourseNumber() {
        // todo 存入redis
        log.info("开始获取学生课程数量");

        Long userId = BaseContext.getCurrentId();
        Long courseNumber = courseService.getCourseNumberByUserId(userId);
        return Result.success(courseNumber);
    }

    /**
     * 课程分页查询
     *
     * @param pageHomeDTO
     * @return
     */
    @PostMapping("/list")
    public Result<PageResult> getCourseList(@RequestBody PageHomeDTO pageHomeDTO) {
        log.info("开始分页查询课程信息: {}", pageHomeDTO);
        PageResult pageResult = courseService.pageQueryCourse(pageHomeDTO);
        return Result.success(pageResult);
    }

    /**
     * 查询课程概况
     *
     * @param status
     * @return
     */
    @GetMapping("/{status}")
    public Result<List<CourseDTO>> getCourse(@PathVariable Integer status) {
        log.info("开始根据状态查询课程信息: {}", status);
        List<CourseDTO> list = courseService.getCourseByStatus(status);
        return Result.success(list);
    }

    /**
     * 查询课程内容详情
     *
     * @param courseId
     * @return
     */
    @GetMapping("/context")
    public Result<CourseDTO> getCourseDetail(@RequestParam Long courseId) {
        log.info("开始查询课程内容详情:{}", courseId);
        CourseDTO courseDTO = courseService.getCourseContextByCourseId(courseId);
        return Result.success(courseDTO);
    }


    /**
     * 根据id查询任务详情
     *
     * @param courseId 课程id
     * @return list
     */
    @GetMapping("/task")
    public Result<List<TaskDTO>> getTaskDetail(@RequestParam Long courseId) {
        log.info("开始根据课程id查询任务详情:{}", courseId);
        List<TaskDTO> list = courseService.getTaskDetailById(courseId);
        return Result.success(list);
    }

    /**
     * 开始查询{}的成绩
     * @param studentName
     * @return
     */
    @GetMapping("/sb/rank")
    public Result getSbRank(@RequestParam String studentName,@RequestParam Long courseId){
        log.info("开始查询{}的{}成绩", studentName,courseId);
        return courseService.getSbRank(studentName,courseId);
    }
}
