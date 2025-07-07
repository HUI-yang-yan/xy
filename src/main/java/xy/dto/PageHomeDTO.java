package xy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageHomeDTO {
    Integer pageNo;
    Integer pageSize;
    String name; //模糊查询名字
}
