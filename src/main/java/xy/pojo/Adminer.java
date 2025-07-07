package xy.pojo;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Adminer {
    private Long id;
    private String name;
    private String password;
    private String email;
    private String salt;
    private String phone;
    private String IdCardNumber;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String createBy;
    private String updateBy;
}
