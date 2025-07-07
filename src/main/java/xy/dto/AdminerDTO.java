package xy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminerDTO {
    private Long id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String IdCardNumber;
}
