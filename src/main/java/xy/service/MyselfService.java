package xy.service;

import xy.dto.MyselfDTO;
import xy.dto.StudentBasisDTO;

public interface MyselfService {
    void updateMyself(StudentBasisDTO studentBasisDTO);

    void sendMiCode(String email);

    void updateMiCode(MyselfDTO myselfDTO);
}
