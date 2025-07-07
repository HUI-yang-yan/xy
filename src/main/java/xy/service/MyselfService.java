package xy.service;

import xy.dto.MyselfDTO;
import xy.dto.StudentBasisDTO;

public interface MyselfService {
    void updateMyself(StudentBasisDTO studentBasisDTO);

    void updateMiCode(MyselfDTO myselfDTO);
}
