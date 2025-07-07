package xy.service.Impl;

import org.springframework.stereotype.Service;
import xy.pojo.File;
import xy.service.FileService;

import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public Optional<File> getFileById(Long id) {
        return Optional.empty();
    }

    @Override
    public void saveFile(File file) {

    }
}
