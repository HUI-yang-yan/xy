package xy.service.Impl;

import org.springframework.stereotype.Service;
import xy.dto.SchoolDTO;
import xy.service.SchoolService;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Override
    public List<SchoolDTO> getUnion() {
        return List.of();
    }

    @Override
    public List<SchoolDTO> getAllSchool() {
        return List.of();
    }
}
