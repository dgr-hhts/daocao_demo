package com.daocao.controller;

import com.daocao.dto.LoginDto;
import com.daocao.respose.ConResult;
import com.daocao.respose.Result;
import com.daocao.unils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/uploadimage")
    public Result upload(MultipartFile file) throws IOException {
        System.out.println("1");
        System.out.println(file.getOriginalFilename());
        System.out.println("2");
        String url = aliOSSUtils.upload(file);
        return new Result(200,"上传成功",url);
    }

    @GetMapping("test")
    public Result test(){
        return new Result(200,"test");
    }
}
