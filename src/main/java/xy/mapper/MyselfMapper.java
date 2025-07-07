package xy.mapper;

import org.apache.ibatis.annotations.Mapper;
import xy.pojo.Student;

@Mapper
public interface MyselfMapper {
    void updateMyself(Student student);
}
