package xy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO {
    private Long id;
    private String taskTitle;
    private String taskDescription;
    private Byte taskType; // 0:作业、1:实验、2:项目、3:测验
    private LocalDateTime assignedDate;
    private LocalDateTime dueDate;
    private Byte status; // 0:未开始，1:进行中，2:已完成，3:已过期
    private BigDecimal maxScore;
    private BigDecimal passingScore;
    private String attachmentUrl;
    private Long teacherId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String remarks;
}
