package xy.mapper;

import org.apache.ibatis.annotations.Mapper;
import xy.dto.StudentBasisDTO;

@Mapper
public interface StudentMapper {
    StudentBasisDTO getById(Long courseId);
}
