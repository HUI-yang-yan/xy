package xy.service;

import xy.dto.SchoolDTO;

import java.util.List;

public interface SchoolService {
    List<SchoolDTO> getUnion();

    List<SchoolDTO> getAllSchool();
    
}
