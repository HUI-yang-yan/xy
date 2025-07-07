package xy.pojo;


import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class School {
    private Long id;                 // 主键ID
    private String name;            // 学校名称
    private String code;            // 学校代码（唯一）
    private String type;            // 学校类型（小学/初中/高中/大学）
    private String level;           // 办学层次（本科/研究生/中专等）
    private String nature;          // 学校性质（公立/私立）
    private String affiliation;     // 隶属单位
    private String province;        // 所在省份
    private String city;            // 所在城市
    private String district;        // 所在区/县
    private String address;         // 详细地址
    private String postcode;        // 邮政编码
    private String website;         // 官网地址
    private String phone;           // 联系电话
    private String email;           // 联系邮箱
    private String principal;       // 校长姓名
    private Integer foundedYear;    // 建校年份
    private String logoUrl;         // 学校Logo地址
    private String description;     // 学校简介
    private Integer isActive;       // 是否启用（1=启用，0=禁用）
    private LocalDateTime createdAt;         // 创建时间
    private LocalDateTime updatedAt;         // 更新时间
}
