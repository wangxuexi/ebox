package com.fijo.ebox.base.util.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadFileUtil {
    /**
     *
     * @param theURL 目标文件地址
     * @param filePath 文件保存目录
     * @param fileName 文件保存名称
     * @throws IOException
     */
    public static void downloadFile(URL theURL, String filePath, String fileName) throws IOException {
        File dirFile = new File(filePath);
        if(!dirFile.exists()){//文件路径不存在时，自动创建目录
            dirFile.mkdir();
        }
        //从服务器上获取图片并保存
        URLConnection connection = theURL.openConnection();
        InputStream in = connection.getInputStream();
        FileOutputStream os = new FileOutputStream(filePath+fileName);
        byte[] buffer = new byte[4 * 1024];
        int read;
        while ((read = in.read(buffer)) > 0) {
            os.write(buffer, 0, read);
        }
        os.close();
        in.close();
    }
}
