package xy.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Announcement {
    private Long id;             // 通知ID
    private String title;        // 标题
    private String contentUrl;   // 本地HTML地址
    private Long authorId;       // 发布人ID
    private Date publishDate;    // 发布时间
    private Date expireDate;     // 过期时间
    private Integer status;      // 状态（1有效，0无效）
    private Date createdAt;      // 创建时间
    private Integer isTop;       // 是否置顶（1=置顶，0=不置顶）
    private Date updatedAt;      // 更新时间
}
