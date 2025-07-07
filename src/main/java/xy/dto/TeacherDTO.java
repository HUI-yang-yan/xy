package xy.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherDTO {
    private Long id;
    private String name;
    private Byte gender; // 0:未知，1:男，2:女
    private String title;
    private LocalDateTime hireDate;
    private Byte status; // 0:离职，1:在职
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String remarks; //	备注字段
}
