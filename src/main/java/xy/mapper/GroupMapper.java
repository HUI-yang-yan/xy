package xy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xy.dto.GroupDTO;
import xy.dto.StudentBasisDTO;
import xy.vo.GroupVO;

import java.util.List;

@Mapper
public interface GroupMapper {
    void JoinGroupByGroupId(Long groupId,String remark,Long userId);

    List<GroupDTO> getGroupList(Long courseId);

    void addGroup(GroupDTO groupDTO);


    void addGroupMember(@Param("studentsBasis") List<StudentBasisDTO> studentsBasis, @Param("groupId") Long groupId);

    int getGroupVO(GroupVO groupVO);

    int getGroupMemberByUserId(@Param("userId") Long userId);
}
