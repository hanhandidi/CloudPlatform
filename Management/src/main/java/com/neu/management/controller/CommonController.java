package com.neu.management.controller;

import com.neu.management.model.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 公共控制器
 */

@RestController
public class CommonController {

    @Value("${web.upload-path}")
    private String path;

    @RequestMapping("upload")
    public Message upload(@RequestParam("file") MultipartFile file) {
        //System.out.println(file.getOriginalFilename());
        // 取原文件名
        String oldName = file.getOriginalFilename();
        // 取原文件扩展名
        String ext = oldName.substring(oldName.lastIndexOf("."));
        // 构造新文件名
        String newName = UUID.randomUUID().toString() + ext;

        Message uploadMessage = new Message();

        try {
            //构造输出流
            FileOutputStream fileOutputStream = new FileOutputStream(path + newName);
            //复制文件
            FileCopyUtils.copy(file.getInputStream(), fileOutputStream);

            uploadMessage.setCode(200);
            uploadMessage.setMessage("文件上传成功！");
            uploadMessage.setMessage(newName);

        } catch (IOException e) {
            e.printStackTrace();

            uploadMessage.setCode(201);
            uploadMessage.setMessage("文件上传失败！");
        }

        return uploadMessage;
    }
}
