package xy.mapper;

import org.apache.ibatis.annotations.Mapper;
import xy.dto.TeacherDTO;

@Mapper
public interface TeacherMapper {
    TeacherDTO getByTeacherId(Long teacherId);
}
