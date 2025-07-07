package xy.controller.teacher.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xy.pojo.File;
import xy.result.Result;
import xy.service.CourseService;
import xy.service.FileService;

import java.io.IOException;

@RequestMapping
@RestController("teacherCourse")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final FileService fileService;

    @PostMapping("/file/upload")
    public Result uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return Result.error("上传失败，文件为空");
        }

        try {
            File file = new File();
            file.setName(multipartFile.getOriginalFilename());
            file.setType(multipartFile.getContentType());
            file.setContent(multipartFile.getBytes());

            fileService.saveFile(file); // 调用 service 保存文件到数据库（或文件系统）

            return Result.success("上传成功，文件ID为：" + file.getId());
        } catch (IOException e) {
            return Result.error("上传失败：" + e.getMessage());
        }
    }
}
