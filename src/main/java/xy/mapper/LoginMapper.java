package xy.mapper;

import org.apache.ibatis.annotations.Mapper;
import xy.pojo.Student;
import xy.vo.LoginVO;

@Mapper
public interface LoginMapper {


    LoginVO getByUserNum(Long userNum);

    int insert(Student student);
}
