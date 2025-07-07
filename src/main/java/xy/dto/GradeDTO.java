package xy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GradeDTO {
    private Double usualScore;
    private Double finalScore;  // ✅ 注意这里是小写 f
}
