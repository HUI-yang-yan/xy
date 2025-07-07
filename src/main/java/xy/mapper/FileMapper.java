package xy.mapper;

import org.apache.ibatis.annotations.Mapper;
import xy.dto.FileDTO;

import java.util.List;

@Mapper
public interface FileMapper {
    List<FileDTO> getFilesByTeacherIdAndCourseId(Long teacherId, Long courseId);
}
