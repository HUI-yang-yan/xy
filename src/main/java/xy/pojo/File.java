package xy.pojo;


import lombok.*;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class File {
    private Long id;

    private Long teacherId;

    private Long courseId;

    // 文件名，如 "report.pdf"
    private String name;

    // 文件后缀，如 "pdf", "pptx"
    private String suffix;

    // MIME 类型，如 "application/pdf"
    private String type;

    // 文件大小（单位：字节）
    private Long size;

    // 上传时间
    private java.sql.Timestamp uploadTime;

    // 文件内容（二进制）
    private byte[] content;
}

