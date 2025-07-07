package xy.service;

import xy.pojo.File;

import java.util.Optional;

public interface FileService {

    Optional<File> getFileById(Long id);

    void saveFile(File file);
}
