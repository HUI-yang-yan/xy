package xy.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import xy.context.BaseContext;
import xy.dto.MyselfDTO;
import xy.dto.StudentBasisDTO;
import xy.mapper.MyselfMapper;
import xy.pojo.Student;
import xy.service.MyselfService;
import xy.utils.EmailUtil;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class MyselfServiceImpl implements MyselfService {

    private final MyselfMapper myselfMapper;

    private final EmailUtil emailUtil;

    private final StringRedisTemplate redisTemplate;

    @Override
    public void updateMyself(StudentBasisDTO studentBasisDTO) {
        studentBasisDTO.setId(BaseContext.getCurrentId());
        Student student = new Student();
        BeanUtils.copyProperties(studentBasisDTO, student);
        myselfMapper.updateMyself(student);
    }

    @Override
    public void sendMiCode(String email) {
        String KEY = "USER_CODE:";
        String code = emailUtil.sendVerificationCode(email);
        redisTemplate.opsForValue().set(KEY+email, code,5, TimeUnit.MINUTES);
    }

    @Override
    public void updateMiCode(MyselfDTO myselfDTO) {
        String code = myselfDTO.getCode();
        String realCode = redisTemplate.opsForValue().get("USER_CODE:" + myselfDTO.getEmail());
        System.out.println(realCode);
        if(code.equals(realCode)) {
            MyselfDTO originalInformationByEmail = myselfMapper.getOriginalInformationByEmail(myselfDTO.getEmail());
            myselfDTO.setId(originalInformationByEmail.getId());
            Student student = new Student();
            BeanUtils.copyProperties(myselfDTO, student);
            log.info("学生:{}",student);
            myselfMapper.updateMyself(student);
        }

    }

}
