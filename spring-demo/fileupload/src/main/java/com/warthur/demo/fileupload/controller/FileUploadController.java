package com.warthur.demo.fileupload.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * Created by warthur on 17/3/26.
 */
@Controller
public class FileUploadController {

    @Value("${web.upload-path}")
    private String uploadPath;

    @GetMapping("/file")
    public String file() {
        return "file";
    }

    @GetMapping("/multifile")
    public String multifile() {
        return "multifile";
    }

    /**
     * 单文件上传
     * @param files List<MultipartFile>
     * @return String
     */
    @PostMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") List<MultipartFile> files) {

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                BufferedOutputStream outputStream;
                try {
                    String filePath = ClassUtils.getDefaultClassLoader().getResource(uploadPath).getPath() + file.getOriginalFilename();

                    outputStream = new BufferedOutputStream(new FileOutputStream(new
                            File(filePath)));
                    outputStream.write(file.getBytes());
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return "上传成功";
    }

}
