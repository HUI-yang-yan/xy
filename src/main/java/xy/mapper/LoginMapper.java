package xy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xy.pojo.Student;
import xy.vo.LoginVO;

@Mapper
public interface LoginMapper {


    LoginVO getByUserNum(@Param("userNum") Long userNum);

    int insert(Student student);
}
