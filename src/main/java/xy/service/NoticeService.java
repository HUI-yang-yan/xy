package xy.service;

import com.mysql.cj.protocol.x.Notice;
import xy.dto.PageNoticeDTO;
import xy.result.PageResult;

import java.util.List;

public interface NoticeService {
    List<Notice> getTopNotice();

    PageResult PageQuery(PageNoticeDTO pageNoticeDTO);
}
