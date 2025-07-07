package xy.service;

import xy.dto.LoginDTO;
import xy.pojo.Student;
import xy.vo.LoginVO;

public interface LoginService {
    /**
     * 登录校验
     *
     * @return
     */
    LoginVO login(LoginDTO loginDTO);

    Boolean register(Student student);
}
