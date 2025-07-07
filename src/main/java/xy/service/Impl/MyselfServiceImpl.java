package xy.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import xy.context.BaseContext;
import xy.dto.MyselfDTO;
import xy.dto.StudentBasisDTO;
import xy.mapper.MyselfMapper;
import xy.pojo.Student;
import xy.service.MyselfService;

@Service
@RequiredArgsConstructor
public class MyselfServiceImpl implements MyselfService {
    private final MyselfMapper myselfMapper;


    @Override
    public void updateMyself(StudentBasisDTO studentBasisDTO) {
        studentBasisDTO.setId(BaseContext.getCurrentId());
        Student student = new Student();
        BeanUtils.copyProperties(studentBasisDTO, student);
        myselfMapper.updateMyself(student);
    }

    @Override
    public void updateMiCode(MyselfDTO myselfDTO) {
        myselfDTO.setId(BaseContext.getCurrentId());
        Student student = new Student();
        BeanUtils.copyProperties(myselfDTO, student);
        myselfMapper.updateMyself(student);
    }
}
