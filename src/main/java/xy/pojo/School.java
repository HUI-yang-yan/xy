package xy.pojo;


import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class School {
    private String SchoolName;
    private String SchoolAddress;
    private String SchoolCity;
    private String SchoolState;
    private String SchoolZip;
    private String SchoolPhone;
    private String SchoolEmail;
    private Long SchoolId;
}
