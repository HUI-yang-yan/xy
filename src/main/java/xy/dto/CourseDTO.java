package xy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private Long id;
    private String courseCode;      // 课程编号，如 CS101
    private String courseName;      // 课程名称
    private Long teacherId;         // 任课教师ID（逻辑外键）
    private String teacherName;         // 任课教师name
    private Double credit;          // 学分
    private Integer totalHours;     // 总课时数
    private String college;         // 开课学院
    private String semester;        // 开课学期
}
