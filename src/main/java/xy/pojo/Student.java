package xy.pojo;


import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    private Long StudentNumber;
    private String StudentName;
    private String StudentGender;
    private String IdCardNumber;
    
}
