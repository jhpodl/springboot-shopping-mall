package com.shoppingmall.posts.controller.api;

import com.shoppingmall.posts.dto.resposne.FileResponseDto;
import com.shoppingmall.posts.service.FileHandlerHelper;
import com.shoppingmall.posts.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
@RestController
public class FileRestController {

    @Value("${upload.directory}")
    private String uploadPath;

    private final ResourceLoader resourceLoader;
    private final FileService fileService;
    private final FileHandlerHelper fileHandlerHelper;

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadSingleFile(@PathVariable("id") Long id) {
        try {
            FileResponseDto files = fileService.getFileById(id);
            String parentSubDir = fileHandlerHelper.getSubFileDirPathByDate();

            if (StringUtils.isBlank(files.getFilePath()) || files.getSaveName().length() == 0) {
                throw new IllegalArgumentException("file path or save name is blank");
            }

            File file = new File(uploadPath + File.separator + parentSubDir + File.separator + files.getSaveName());
            Resource resource = resourceLoader.getResource("file:" + file.getPath());
            InputStream inputStream = resource.getInputStream();

            ResponseEntity<Resource> response = ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + files.getOriginalName() + "\"")
                    .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(resource.contentLength()))
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM.toString())
                    .body(new InputStreamResource(inputStream));

            fileService.increaseDownloadCnt(id);
            return response;
        } catch (IllegalArgumentException e) {
            log.error("file path or save name is blank, id = {}", id, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (FileNotFoundException e) {
            log.error("file not found, id = {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IOException e) {
            log.error("error occurred while downloading file, id = {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/download/compress/{id}")
    public void downloadMultiZipFile(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        List<File> files = fileService.getFiles(id)
                .stream()
                .map(fileResponseDto -> new File(fileResponseDto.getFilePath()))
                .collect(Collectors.toList());

        if (files.isEmpty()) {
            throw new FileNotFoundException("file not found");
        }

        fileHandlerHelper.transferToZipFile(response.getOutputStream(), files);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/zip");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + "zipFile" + ".zip\"");
    }
}
