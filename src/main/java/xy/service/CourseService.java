package xy.service;

import xy.dto.*;
import xy.result.PageResult;
import xy.result.Result;

import java.util.List;
import java.util.Map;


public interface CourseService {
    StudentBasisDTO getStudentBasis(Long userId);

    PageResult pageQueryCourse(PageHomeDTO pageHomeDTO);

    CourseDTO getCourseContextByCourseId(Long courseId);

    List<CourseDTO> getCourseByStatus(Integer status);

    Long getCourseNumberByUserId(Long userId);

    List<TaskDTO> getTaskDetailById(Long courseId);

    List<FileDTO> getFilesByTeacherIdAndCourseId(Long teacherId, Long courseId);

    void addGroupByGroupId(Long groupId,String remark);

    List<GroupDTO> getGroupList(Long courseId);

    Result<Map<String,String>> getGrade();

    Result getRank();

    Result getSbRank(String studentName,Long courseId);

    Boolean addGroup(GroupDTO groupDTO);
}
