package com.fijo.ebox.util;

import com.fijo.ebox.base.util.common.DateUtils;
import com.fijo.ebox.config.FileConfig;
import com.fijo.ebox.model.FileParameter;
import com.fijo.ebox.model.FileUploadNew;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;


@RestController
@RequestMapping("/UploadFileUtil")
@Slf4j
public class UploadFileUtil {

    @Autowired
    private FileConfig fileConfig;

    @Autowired
    private WxApiUtil wxApiUtil;



    //附件上传
    public FileUploadNew resultUploadNew(MultipartFile file, FileUploadNew o) throws Exception{
        FileUploadNew upload=new FileUploadNew();
        try {
            String ftpPath= fileConfig.getFtpPath();
            String ftpHost = fileConfig.getFtpHost();
            int ftpPort = Integer.parseInt(fileConfig.getFtpPort());
            String ftpUserName = fileConfig.getFtpUserName();
            String ftpPassword = fileConfig.getFtpPassword();
            String workingPath="";
            String saveName ="";
            //得到处理之后的文件对象
            FileParameter parameter= UploadHelper.getParameterNew(file,o);
            workingPath=parameter.getWorkingPath();//新建一个文件 +租户编码+组织编码+模块名+文件类型
            saveName=parameter.getFileNewName();//生成后的文件名称
            InputStream fileInputStream =file.getInputStream();
            if (UploadFtpHelper.upload(ftpHost, ftpPort, ftpUserName, ftpPassword, workingPath, fileInputStream, saveName)){
                //如果上传成功则在数据库新增一条记录
                upload.setCreateTime(DateUtils.getNowDateYMDHMS());
                upload.setEnabled(true);
                upload.setRemoved(false);
                upload.setTenant(o.getTenant());//租户编码
                upload.setOrgCode(o.getOrgCode());//组织编码
                upload.setModular(o.getModular());//模块名
                String fName = parameter.getFileName().trim();
                upload.setFileName(fName.substring(fName.lastIndexOf("\\")+1));//原始文件名
                upload.setFilePath(ftpPath+parameter.getWorkingPath()+"/"+parameter.getFileNewName());//文件保存路径
                upload.setFileNewName(parameter.getFileNewName());//生成的文件名
                upload.setFileClass(o.getFileClass());//parameter.getFileSuffix()
                if (file.getSize() > 0){
                    upload.setFileSize(file.getSize());
                }
                upload.setDesc(parameter.getFileSuffix());
                // createTo(upload);
            }
            return upload;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return upload;
    }


}
