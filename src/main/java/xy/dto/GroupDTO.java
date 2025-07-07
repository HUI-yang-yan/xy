package xy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {
    private Long id;           // 小组ID
    private Long courseId;          // 所属课程ID
    private String groupName;       // 小组名称
    private String groupDescription;// 小组描述
    private Long leaderId;          // 组长ID（学生ID）
    private Integer status;         // 状态：0正常，1解散
    private LocalDateTime createTime; // 创建时间
    private List<StudentBasisDTO> studentBasisDTOS;
}
