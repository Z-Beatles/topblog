package cn.waynechu.topblog.web.controller.admin;

import java.io.File;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.waynechu.topblog.util.RegexUtil;
import cn.waynechu.topblog.util.WebUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import cn.waynechu.topblog.base.BaseController;

@Controller
@RequestMapping(value = "/admin/upload")
public class UploadController extends BaseController {
    @RequestMapping(value = "/image", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String hello(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "editormd-image-file", required = true) MultipartFile attach) {
        try {
            String rootPath = request.getSession().getServletContext().getRealPath("/WEB-INF/resources/upload/");
            File filePath = new File(rootPath);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }

            String fileName = attach.getOriginalFilename();
            String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
            String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
            File realFile = new File(rootPath + File.separator + newFileName);
            FileUtils.copyInputStreamToFile(attach.getInputStream(), realFile);

            URL url = new URL(request.getRequestURL().toString());
            String prefix = "http://" + url.getHost() + ":" + url.getPort();
            return "{\"success\": 1, \"message\":\"上传成功\",\"url\":\"" + prefix + "/resources/upload/" + newFileName + "\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"success\":0, \"message\":\"上传失败\"}";
    }
}
