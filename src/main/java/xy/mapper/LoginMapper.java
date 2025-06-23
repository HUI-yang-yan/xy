package xy.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    public String loginByPassword(String username) ;

    String getSaltByUsername(String username);
}
