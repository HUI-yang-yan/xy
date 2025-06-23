package xy.exception;


import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@Data
public class ApiError {
    private int code;
    private String message;
    private Object data;

    // 构造方法、getter/setter
}
