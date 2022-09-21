package com.fijo.ebox.base.util.plat;

import com.fijo.ebox.base.constant.ExcelConstant;
import com.fijo.ebox.base.util.common.DownloadFileUtil;
import com.fijo.ebox.dto.ResultDto;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.UUID;

public class FileUtil {

    public static String uploadFile(MultipartFile multipartFile, String dirPath) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        String localFileName = UUID.randomUUID().toString() + fileSuffix;
        String filePath = dirPath + File.separator + localFileName;
        //    File localFile = new File(filePath);
        File imagePath = new File(dirPath);
        if (!imagePath.exists()) {
            imagePath.mkdirs();
        }
        //获取文件流
        InputStream in = multipartFile.getInputStream();
        //获取文件存放地址
        FileOutputStream os = new FileOutputStream(filePath);
        byte[] buffer = new byte[4 * 1024];
        int read;
        while ((read = in.read(buffer)) > 0) {
            //将文件写入存放地址
            os.write(buffer, 0, read);
        }
        os.close();
        in.close();
        //本地使用需要给绝对路径 否则报错; 测试环境可以相对路径
        //   multipartFile.transferTo(localFile);
        return localFileName;
    }

    //返回文件原始名 （应用于展示导入历史）
    public static String uploadFileOriginalName(MultipartFile multipartFile, String dirPath) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        String filePath = dirPath + File.separator + fileName;
        File imagePath = new File(dirPath);
        if (!imagePath.exists()) {
            imagePath.mkdirs();
        }
        //获取文件流
        InputStream in = multipartFile.getInputStream();
        //获取文件存放地址
        FileOutputStream os = new FileOutputStream(filePath);
        byte[] buffer = new byte[4 * 1024];
        int read;
        while ((read = in.read(buffer)) > 0) {
            //将文件写入存放地址
            os.write(buffer, 0, read);
        }
        os.close();
        in.close();
        //本地使用需要给绝对路径 否则报错; 测试环境可以相对路径
        //   multipartFile.transferTo(localFile);
        return fileName;
    }

    /**
     * @param response
     * @param fileName     文件名，例如：人员名单.xlsx
     * @param xssfWorkbook excel工作薄
     * @return
     * @throws Exception
     */
    public static String downloadExcel(HttpServletResponse response, String fileName, XSSFWorkbook xssfWorkbook) throws Exception {
        String suffix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        //1.生成excel的文件路径
        String filePath = ExcelConstant.FILE_PATH + UUID.randomUUID().toString() + suffix;
        //2.获取输出流对象
        FileOutputStream fos = new FileOutputStream(filePath);

        //3.写出文件,关闭流
        xssfWorkbook.write(fos);
        xssfWorkbook.close();
        fos.close();

        File file = new File(filePath);
        if (file.exists()) {//判断文件是否生成
            String name = new String(fileName.getBytes("utf-8"), "iso-8859-1"); //解决中文乱码问题
            response.setContentType("application/octet-stream");// 二进制流
            response.addHeader("Content-Disposition", "attachment;fileName=" + name);
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream outputStream = response.getOutputStream();
                response.setHeader("Content-Length", String.valueOf(fis.available()));
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                //将文件流成功添加到浏览器返回头里面之后删除文件
                Boolean flag = new File(filePath).delete();
                if (flag.equals(true)) {
                    return ResultDto.SUCCESS("下载成功");
                } else {
                    return ResultDto.ERROR("下载失败");
                }
            }
        }
        return ResultDto.ERROR("下载失败");
    }

    public static String downLoadFile(String downloadFilePath, String fileName, HttpServletResponse response) throws Exception {
        File file = new File(downloadFilePath);
        if (file.exists()) {
            response.setContentType("application/octet-stream");//  二进制流，不知道下载文件类型
            response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("utf-8"),"ISO8859-1"));

            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            OutputStream outputStream = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                outputStream = response.getOutputStream();
                response.setHeader("Content-Length", String.valueOf(fis.available()));
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                return "下载成功";
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (response.getOutputStream() != null) {
                        response.getOutputStream().flush();
                        response.getOutputStream().close();
                    }
                    if (outputStream != null) {
                        outputStream.flush();
                        outputStream.close();
                    }
                    if (bis != null) {
                        bis.close();
                    }
                    if (fis != null) {
                        fis.close();
                    }
                } catch (Exception e) {
                    throw e;
                }
            }
        }
        return "下载失败";
    }

    /**
     * 删除某个文件夹下的所有文件夹和文件
     *
     * @param delpath String
     * @return boolean
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static boolean deletefile(String delpath) throws Exception {
        try {
            File file = new File(delpath);
            // 当且仅当此抽象路径名表示的文件存在且 是一个目录时，返回 true
            if (!file.isDirectory()) {
                file.delete();
            } else if (file.isDirectory()) {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File delfile = new File(delpath + "\\" + filelist[i]);
                    if (!delfile.isDirectory()) {
                        delfile.delete();
                        System.out.println(delfile.getAbsolutePath() + "删除文件成功");
                    } else if (delfile.isDirectory()) {
                        deletefile(delpath + "\\" + filelist[i]);
                    }
                }
                System.out.println(file.getAbsolutePath() + "删除成功");
                file.delete();
            }

        } catch (FileNotFoundException e) {
            System.out.println("deletefile() Exception:" + e.getMessage());
        }
        return true;
    }

    public static boolean deleteFolder(String path) {
        File file = new File(path);
        boolean delete;
        if (file.isDirectory()) {
            String[] children = file.list();
            if (children.length > 0) {
                /**递归删除目录中的子目录下*/
                for (int i = 0; i < children.length; i++) {
                    boolean success = deleteFolder(file.getPath() + "/" + children[i]);
                    if (!success) {
                        return false;
                    }
                }
            }
            delete = file.delete();
        } else {
            delete = file.delete();
        }
        return delete;
    }

    public static String downLoadFileToLocation(String fileName, String downloadFilePath, HttpServletResponse response) throws Exception {
        //将附件下载到项目本地
        String officefile = "officefile/";
        URL url = new URL(downloadFilePath);
        String uuid = UUID.randomUUID().toString();
        String officefileName = uuid + fileName;

        DownloadFileUtil.downloadFile(url, officefile, officefileName);

        File file = new File(officefile + officefileName);
        if (file.exists()) {
            response.setContentType("application/octet-stream");//  二进制流，不知道下载文件类型
            response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("utf-8"),"ISO8859-1"));

            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            OutputStream outputStream = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                outputStream = response.getOutputStream();
                response.setHeader("Content-Length", String.valueOf(fis.available()));
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }

                return "下载成功";
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (response.getOutputStream() != null) {
                        response.getOutputStream().flush();
                        response.getOutputStream().close();
                    }
                    if (outputStream != null) {
                        outputStream.flush();
                        outputStream.close();
                    }
                    if (bis != null) {
                        bis.close();
                    }
                    if (fis != null) {
                        fis.close();
                    }
                    File file1 = new File(officefile + officefileName);
                    file1.delete();
                } catch (Exception e) {
                    throw e;
                }
            }
        }
        return "下载失败";
    }





}
