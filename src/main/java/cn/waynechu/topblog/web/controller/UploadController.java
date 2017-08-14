package cn.waynechu.topblog.web.controller;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import cn.waynechu.topblog.base.BaseController;

@Controller
@RequestMapping(value = "/upload")
public class UploadController extends BaseController {
    @RequestMapping(value = "/image", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String hello(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "editormd-image-file", required = true) MultipartFile attach) {
        try {
            String rootPath = request.getSession().getServletContext().getRealPath("/WEB-INF/resources/upload/");
            File filePath = new File(rootPath);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            File realFile = new File(rootPath + File.separator + attach.getOriginalFilename());
            FileUtils.copyInputStreamToFile(attach.getInputStream(), realFile);
            return "{\"success\": 1, \"message\":\"上传成功\",\"url\":\"/resources/upload/" + attach.getOriginalFilename()
                    + "\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"success\":0, \"message\":\"上传失败\"}";
    }
}
