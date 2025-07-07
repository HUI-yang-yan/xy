package xy.pojo;


import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    private Long id;                // 学生ID（主键）
    private String studentNumber;   // 学号 / 登录账号
    private String name;            // 姓名
    private String gender;          // 性别（M/F）
    private Integer age;            // 年龄
    private String college;         // 所属学院
    private String major;           // 专业
    private String phone;           // 手机号
    private String email;           // 邮箱
    private String password;        // 密码
    private Integer status;         // 状态（1-正常，0-禁用）
    private LocalDateTime createTime;  // 创建时间
    private LocalDateTime updateTime;  // 更新时间
}
