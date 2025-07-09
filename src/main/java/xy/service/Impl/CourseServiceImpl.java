package xy.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xy.context.BaseContext;
import xy.dto.*;
import xy.mapper.*;
import xy.result.PageResult;
import xy.result.Result;
import xy.service.CourseService;
import xy.vo.GroupVO;

import java.util.*;

import static xy.context.RedisContext.*;


@Service
@Slf4j
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final StringRedisTemplate redisTemplate;

    private final TeacherMapper teacherMapper;

    private final CourseMapper courseMapper;

    private final StudentMapper studentMapper;

    private final TaskMapper taskMapper;

    private final FileMapper fileMapper;

    private final GroupMapper groupMapper;


    @Override
    public StudentBasisDTO getStudentBasis(Long userId) {
        return studentMapper.getById(userId);
    }

    @Override
    @Transactional
    public PageResult pageQueryCourse(PageHomeDTO pageHomeDTO) {
/*
            Integer offset = ( (pageHomeDTO.getPageNo() - 1) * pageHomeDTO.getPageSize());
        List<CourseDTO> list = null;
        try {
            list = courseMapper.getCourseDetail((pageHomeDTO.getPageSize()),offset,pageHomeDTO.getName());
        } catch (Exception e) {
            new RuntimeException(e);e.printStackTrace(); // 直接打印到控制台
            // 或者用日志打印
            log.error("出现异常", e);
        }
        System.out.println("hhhh");
*/
        PageHelper.startPage(pageHomeDTO.getPageNo(), pageHomeDTO.getPageSize());
        List<CourseDTO> list  = courseMapper.pageQuery(pageHomeDTO);
        for (CourseDTO courseDTO : list) {
            insertTeacherName(courseDTO);
        }

        PageInfo<CourseDTO> pageInfo = new PageInfo<>(list);

        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public CourseDTO getCourseContextByCourseId(Long courseId) {
        StudentBasisDTO studentBasisDTO = studentMapper.getById(courseId);
        CourseDTO courseDTO = courseMapper.getDetailById(courseId);
        return insertTeacherName(courseDTO);
    }

    private CourseDTO insertTeacherName(CourseDTO courseDTO) {
        TeacherDTO byTeacherId = teacherMapper.getByTeacherId(courseDTO.getTeacherId());
        courseDTO.setTeacherName(byTeacherId.getName());
        return courseDTO;
    }

    @Override
    public List<CourseDTO> getCourseByStatus(Integer status) {
        List<Long> courseIds = courseMapper.getCourseIdsByStatus(status);

        List<CourseDTO> list = new ArrayList<>();
        if (courseIds != null) {
            courseIds.forEach(courseId -> {
                CourseDTO courseDTO = courseMapper.getDetailById(courseId);
                insertTeacherName(courseDTO);
                list.add(courseDTO);
            });
        }
        return list;
    }

    @Override
    public Long getCourseNumberByUserId(Long userId) {
        return courseMapper.getSumByUserId(userId);
    }

    @Override
    public List<TaskDTO> getTaskDetailById(Long courseId) {
        return taskMapper.getTaskDetailById(courseId);
    }

    @Override
    public List<FileDTO> getFilesByTeacherIdAndCourseId(Long teacherId, Long courseId) {
        return fileMapper.getFilesByTeacherIdAndCourseId(teacherId, courseId);
    }


    @Override
    public void addGroupByGroupId(Long groupId, String remark) {
        try {
            Long userId = BaseContext.getCurrentId();
            int s = groupMapper.getGroupMemberByUserId(userId);
            if (s == 0) {
                groupMapper.JoinGroupByGroupId(groupId, remark,userId);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<GroupDTO> getGroupList(Long courseId) {
        return groupMapper.getGroupList(courseId);
    }

    @Override
    @Transactional
    public Result<Map<String,String>> getGrade() {
        Long userId = BaseContext.getCurrentId();
        String Usual = redisTemplate.opsForValue().get(userUsualKey + userId);
        String Final = redisTemplate.opsForValue().get(userFinalKey + userId);
        if (Usual == null || Final == null) {
            //1.从数据库查询数据
            GradeDTO gradeDTO = courseMapper.getUsualAndFinalByUserId(userId);
            System.out.println(gradeDTO);
            System.out.println(userId);
            if(gradeDTO.getFinalScore() == null || gradeDTO.getUsualScore() == null) {
                return Result.error("教师未上传成绩");
            }
            //2.2 .将数据写入到redis
            redisTemplate.opsForValue().set(userUsualKey + userId, String.valueOf(gradeDTO.getUsualScore()));
            redisTemplate.opsForValue().set(userFinalKey + userId, String.valueOf(gradeDTO.getFinalScore()));
            Usual = String.valueOf(gradeDTO.getUsualScore());
            Final = String.valueOf(gradeDTO.getFinalScore());
        }
        Map<String, String> map = new HashMap<>();
        map.put("Usual", Usual);
        map.put("Final", Final);
        //3.返回
        return Result.success(map);
    }

    @Override
    public Result getRank() {

        // 获取前100名（或分页也可）
        Set<ZSetOperations.TypedTuple<String>> topSet = redisTemplate.opsForZSet().reverseRangeWithScores(redisKey, 0, 99);

        List<RankDTO> rankList = new ArrayList<>();
        int rank = 1;
        if (topSet != null) {
            for (ZSetOperations.TypedTuple<String> tuple : topSet) {
                RankDTO dto = new RankDTO();
                dto.setRank(rank++);
                dto.setName(tuple.getValue()); // 学生姓名
                dto.setScore(tuple.getScore());
                rankList.add(dto);
            }
        }

        return Result.success(rankList);
    }

    @Override
    public Result getSbRank(String studentName,Long courseId) {
        String redisKey = "rank:courseId:"+courseId+":2025:";
        // 查询名次（从0开始）
        Long rank = redisTemplate.opsForZSet().reverseRank(redisKey, studentName);
        // 查询分数
        Double score = redisTemplate.opsForZSet().score(redisKey, studentName);

        if(score == null) {
            List<Double> scoreList = courseMapper.getScoreByCourseId(courseId);
            //写入redis
            for (Double sc : scoreList) {
                redisTemplate.opsForZSet().add(redisKey, studentName, sc);
            }
            rank = redisTemplate.opsForZSet().reverseRank(redisKey, studentName);
            score = redisTemplate.opsForZSet().score(redisKey, studentName);
        }

        // 判断是否存在
        if (rank == null || score == null) {
            return Result.error("该学生未上榜");
        }

        // 封装结果
        RankDTO dto = RankDTO.builder().name(studentName).score(score).rank((int) (rank + 1)).build();
        return Result.success(dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addGroup(GroupDTO groupDTO) {
        GroupVO groupVO = GroupVO.builder()
                .groupName(groupDTO.getGroupName())
                .courseId(groupDTO.getCourseId()).build();

        //查询数据库是否存在这样的数据
        System.out.println(groupVO);

        int ifExist = groupMapper.getGroupVO(groupVO);
        if (ifExist > 0) {
            return false;
        }

        // 先插入小组，获得自增id
        groupMapper.addGroup(groupDTO);

        Long groupId = groupDTO.getId();
        if (groupId == null) {
            throw new RuntimeException("插入小组失败，未获取到ID");
        }

        List<StudentBasisDTO> studentBasisDTOS = groupDTO.getStudentBasisDTOS();
        if (studentBasisDTOS != null && !studentBasisDTOS.isEmpty()) {
            // 批量插入成员
            groupMapper.addGroupMember(studentBasisDTOS, groupId);
        }
        return true;
    }
}
