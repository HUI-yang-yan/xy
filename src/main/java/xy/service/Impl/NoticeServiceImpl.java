package xy.service.Impl;

import com.mysql.cj.protocol.x.Notice;
import org.springframework.stereotype.Service;
import xy.dto.PageNoticeDTO;
import xy.result.PageResult;
import xy.service.NoticeService;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Override
    public List<Notice> getTopNotice() {
        return List.of();
    }

    @Override
    public PageResult PageQuery(PageNoticeDTO pageNoticeDTO) {
        return null;
    }
}
