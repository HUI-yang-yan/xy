package xy.service;

import xy.dto.AwardDTO;

import java.util.List;

public interface AwardService {
    List<AwardDTO> getAward(int year, int level);
}
