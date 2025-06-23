package xy.pojo;

import lombok.*;

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
}
