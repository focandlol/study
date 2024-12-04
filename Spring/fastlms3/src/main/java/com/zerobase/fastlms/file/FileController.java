package com.zerobase.fastlms.file;

import com.zerobase.fastlms.util.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FileController {

    private final FileUtils fileUtils;

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource imageDownload(@PathVariable String filename) throws MalformedURLException {
        UrlResource urlResource;
        log.info("filename____________________________________________________________={}",filename);


        urlResource = new UrlResource("file:" + fileUtils.getFullPath(filename));
        log.info("urlresource = {}",urlResource);

        return urlResource;


    }
}
