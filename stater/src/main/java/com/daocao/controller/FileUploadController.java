package com.daocao.controller;

import com.daocao.dto.LoginDto;
import com.daocao.respose.ConResult;
import com.daocao.respose.Result;
import com.daocao.unils.AliOSSUtils;
import com.daocao.unils.ImageCode;
import com.daocao.unils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Slf4j
@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/uploadimage")
    public Result upload(MultipartFile file) throws IOException {
        System.out.println("1");
        System.out.println(file.getOriginalFilename());
        System.out.println("2");
        String url = aliOSSUtils.upload(file);
        return new Result(200,"上传成功",url);
    }

    @GetMapping("code")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imagecode=new ImageCode();//图形验证码工具类
        BufferedImage img=imagecode.getImage();
        try {
            String code = imagecode.getValidateCode();//验证码
            redisUtil.set("code",code);
            response.setContentType("image/jpeg");
            imagecode.saveImage(img, response.getOutputStream());
        } catch (IOException e) {
            log.error("生成图片验证码异常", e);
        }
    }
}
