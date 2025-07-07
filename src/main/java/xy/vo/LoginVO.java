package xy.vo;


import lombok.*;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginVO {
    Long id;
    String name;
    String password;
    String salt;
    Boolean ifSuccess;
}
