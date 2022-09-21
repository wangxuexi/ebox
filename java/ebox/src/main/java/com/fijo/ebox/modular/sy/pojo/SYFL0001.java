package com.fijo.ebox.modular.sy.pojo;
import com.fijo.ebox.base.pojo.FijoBasePojo;
import lombok.Data;
/**
*SYS_FILE_UPLOAD 附件表
*SYFL0001
**/
@Data
public class SYFL0001 extends FijoBasePojo {
  private Long id; //
  private String modular; //模块
  private String orgCode; //组织编码
  private String entityTypeCode; //实体类型编码
  private Long objectId; //业务实体ID
  private String fileName; //名称
  private String fileNewName; //文件存放服务器后生成的名字
  private String filePath; //文件保存路径
  private String fileClass; //文件类型
  private Long fileSize; //尺寸
  private String fileDesc; //附件描述
  private String fileUsecase; //附件用途
  private String attr1; //扩展字段1 附件类型分类，将此附件与其它同业务实体的附件做区分处理
  private String attr2; //扩展字段2 导入数据所属年月
  private String attr3; //扩展字段3
  private String orderByClause; //排序规则
  private String startDate;//开始时间
}
