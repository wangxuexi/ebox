package com.fijo.ebox.modular.sy.pojo;
import lombok.Data;

@Data
public class FileResult {
  private String isFile;//是否上传文件
  private String modular;//模块名
  private String entityTypeCode;//实体类型编码
  private String fileName; //名称
  private String fileNewName; //文件存放服务器后生成的名字
  private String filePath; //文件保存路径
  private String fileClass; //文件类型
}
