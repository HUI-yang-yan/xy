package xy.dto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTO {
    Long userId;
    Long userNum;
    String password;
}
