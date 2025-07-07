package xy.service.Impl;

import org.springframework.stereotype.Service;
import xy.dto.AwardDTO;
import xy.service.AwardService;

import java.util.List;

@Service
public class AwardServiceImpl implements AwardService {
    @Override
    public List<AwardDTO> getAward(int year, int level) {
        return List.of();
    }
}
