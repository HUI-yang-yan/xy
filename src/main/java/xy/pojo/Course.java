package xy.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    private Long id;                 // 课程ID
    private String courseCode;      // 课程编号，如 CS101
    private String courseName;      // 课程名称
    private Long teacherId;         // 任课教师ID（逻辑外键）
    private Double credit;          // 学分
    private Integer totalHours;     // 总课时数
    private String college;         // 开课学院
    private String semester;        // 开课学期
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}
