package xy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xy.dto.CourseDTO;
import xy.dto.GradeDTO;
import xy.dto.PageHomeDTO;

import java.util.List;

@Mapper
public interface CourseMapper {
    List<Long> getCourseIdsByStatus(Integer status);

    CourseDTO getDetailById(Long courseId);

    List<CourseDTO> getCourseDetail(PageHomeDTO pageHomeDTO);

    Long getSumByUserId(Long userId);

    GradeDTO getUsualAndFinalByUserId(@Param("userId")Long userId);

    List<Double> getScoreByCourseId(@Param("courseId") Long courseId);
}

