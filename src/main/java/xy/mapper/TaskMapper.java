package xy.mapper;

import org.apache.ibatis.annotations.Mapper;
import xy.dto.TaskDTO;

import java.util.List;

@Mapper
public interface TaskMapper {
    List<TaskDTO> getTaskDetailById(Long courseId);
}
