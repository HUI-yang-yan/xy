package xy.dto;


import lombok.*;
import xy.pojo.Teacher;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AwardDTO {
    private Long id;
    private int year;
    private int awardLevel;
    private String subjectName;
    private String teacherName;
    private String awardName;
    private String college;
    private String description;
    private List<Teacher> list;
}
