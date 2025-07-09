package xy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xy.dto.MyselfDTO;
import xy.pojo.Student;

@Mapper
public interface MyselfMapper {
    void updateMyself(Student student);

    MyselfDTO getOriginalInformationById(@Param("userId") Long userId);

    MyselfDTO getOriginalInformationByEmail(@Param("email") String email);
}
