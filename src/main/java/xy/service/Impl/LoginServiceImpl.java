package xy.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xy.dto.LoginDTO;
import xy.mapper.LoginMapper;
import xy.pojo.Student;
import xy.service.LoginService;
import xy.vo.LoginVO;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginMapper loginMapper;


    @Override
    @Transactional
    public LoginVO login(LoginDTO loginDTO) {
        Long userNum = loginDTO.getUserNum();
        String password = loginDTO.getPassword();
        LoginVO loginVO = loginMapper.getByUserNum(userNum);
        if (loginVO == null||!password.equals(loginVO.getPassword())) {
            return LoginVO.builder()
                    .ifSuccess(false)
                    .build();
        }
        return LoginVO.builder()
                .ifSuccess(true)
                .id(loginVO.getId())
                .password(loginVO.getPassword())
                .name(loginVO.getName())
                .build();
    }

    @Override
    public Boolean register(Student student) {
        int s = loginMapper.insert(student);
        return s <= 1;
    }
}
