package xy.controller.student;

import com.mysql.cj.protocol.x.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xy.dto.PageNoticeDTO;
import xy.result.PageResult;
import xy.result.Result;
import xy.service.NoticeService;

import java.util.List;

@Slf4j
@RequestMapping("/student/notice")
@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/top")
    public Result<List<Notice>> getTopNotice() {
        log.info("开始查询数据库是否有置顶的通知");
        List<Notice> noticeList = noticeService.getTopNotice();
        return Result.success(noticeList);
    }

    @GetMapping("/list")
    public Result<PageResult> getNoticeList(@RequestParam PageNoticeDTO pageNoticeDTO){
        log.info("开始分页查询notice:{}",pageNoticeDTO);
        PageResult pageResult = noticeService.PageQuery(pageNoticeDTO);
        return Result.success(pageResult);
    }
}
