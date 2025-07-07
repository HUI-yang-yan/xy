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
public class TeachingInnovationAward {
    private Long id;                        // 主键ID
    private String awardName;              // 奖项名称
    private String awardLevel;             // 奖项级别（校级、省级、国家级等）
    private Long recipientId;              // 获奖人ID
    private Long innovationProjectId;     // 关联教学创新项目ID
    private String year;                    // 获奖年份
    private String description;            // 奖项描述或备注
    private String certificateUrl;         // 证书电子档地址
    private Long schoolId;                 // 所属学校ID
    private LocalDateTime createdAt;                // 创建时间
    private LocalDateTime updatedAt;                // 更新时间
}
