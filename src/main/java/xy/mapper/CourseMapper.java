package xy.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xy.dto.CourseDTO;
import xy.dto.GradeDTO;
import xy.dto.PageHomeDTO;

import java.util.List;

@Mapper
public interface CourseMapper {
    List<Long> getCourseIdsByStatus(Integer status);

    CourseDTO getDetailById(Long courseId);
    Long getSumByUserId(Long userId);

    GradeDTO getUsualAndFinalByUserId(@Param("userId")Long userId);

    List<Double> getScoreByCourseId(@Param("courseId") Long courseId);

    Page<CourseDTO> getCourseDetail(PageHomeDTO pageHomeDTO);

    List<CourseDTO> pageQuery(PageHomeDTO pageHomeDTO);
}

