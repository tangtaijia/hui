package com.hui.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class FileUtils
{
    /** 
     * 移动指定文件或文件夹(包括所有文件和子文件夹) 
     *  
     * @param fromDir 
     *            要移动的文件或文件夹 
     * @param toDir 
     *            目标文件夹 
     * @throws Exception 
     */  
    public static void MoveFolderAndFileWithSelf(String from, String to) throws Exception {  
        try {  
            File dir = new File(from);  
            // 目标  
            to +=  File.separator + dir.getName();  
            File moveDir = new File(to);  
            if(dir.isDirectory()){  
                if (!moveDir.exists()) {  
                    moveDir.mkdirs();  
                }  
            }else{  
                File tofile = new File(to);  
                dir.renameTo(tofile);  
                return;  
            }  
              
            //System.out.println("dir.isDirectory()"+dir.isDirectory());  
            //System.out.println("dir.isFile():"+dir.isFile());  
              
            // 文件一览  
            File[] files = dir.listFiles();  
            if (files == null)  
                return;  
  
            // 文件移动  
            for (int i = 0; i < files.length; i++) {  
                System.out.println("文件名："+files[i].getName());  
                if (files[i].isDirectory()) {  
                    MoveFolderAndFileWithSelf(files[i].getPath(), to);  
                    // 成功，删除原文件  
                    files[i].delete();  
                }  
                File moveFile = new File(moveDir.getPath() + File.separator + files[i].getName());  
                // 目标文件夹下存在的话，删除  
                if (moveFile.exists()) {  
                    moveFile.delete();  
                }  
                files[i].renameTo(moveFile);  
            }  
            dir.delete();  
        } catch (Exception e) {  
            throw e;  
        }  
    }  
      
    /** 
     * 复制单个文件(可更名复制) 
     * @param oldPathFile 准备复制的文件源 
     * @param newPathFile 拷贝到新绝对路径带文件名(注：目录路径需带文件名) 
     * @return 
     */  
    public static void CopySingleFile(String oldPathFile, String newPathFile) {  
        try {  
            int bytesum = 0;  
            int byteread = 0;  
            File oldfile = new File(oldPathFile);  
            if (oldfile.exists()) { //文件存在时  
                InputStream inStream = new FileInputStream(oldPathFile); //读入原文件  
                FileOutputStream fs = new FileOutputStream(newPathFile); 
                byte[] buffer = new byte[1444];  
                while ((byteread = inStream.read(buffer)) != -1) {  
                    bytesum += byteread; //字节数 文件大小  
                    //System.out.println(bytesum);  
                    fs.write(buffer, 0, byteread);  
                }  
                inStream.close();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    /**
     * 创建文件路径
     * <功能详细描述>
     * @param filePath
     * @see [类、类#方法、类#成员]
     */
    public static void mkDirs(String filePath)
    {
        File newFile = new File(filePath);
        if(!newFile.exists()) {
            newFile.mkdirs();
        }
    }
  
    public static void cutSingleFile(String oldPath,String newPath,String fileName) {
        mkDirs(newPath);
        CopySingleFile(oldPath+"/"+fileName, newPath+"/"+fileName) ;
        File tempFile =
            new File(oldPath,
                fileName);
        if (null != tempFile && tempFile.exists())
        {
            tempFile.delete();
        }
    }
    
    /** 
     * 复制单个文件(原名复制) 
     * @param oldPathFile 准备复制的文件源 
     * @param newPathFile 拷贝到新绝对路径带文件名(注：目录路径需带文件名) 
     * @return 
     */  
    public static void CopySingleFileTo(String oldPathFile, String targetPath) {  
        try {  
            int bytesum = 0;  
            int byteread = 0;  
            File oldfile = new File(oldPathFile);  
            String targetfile = targetPath + File.separator +  oldfile.getName();  
            if (oldfile.exists()) { //文件存在时  
                InputStream inStream = new FileInputStream(oldPathFile); //读入原文件  
                FileOutputStream fs = new FileOutputStream(targetfile);  
                byte[] buffer = new byte[1444];  
                while ((byteread = inStream.read(buffer)) != -1) {  
                    bytesum += byteread; //字节数 文件大小  
                    //System.out.println(bytesum);  
                    fs.write(buffer, 0, byteread);  
                }  
                inStream.close();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * 复制整个文件夹的内容(含自身) 
     * @param oldPath 准备拷贝的目录 
     * @param newPath 指定绝对路径的新目录 
     * @return 
     */  
    public static void copyFolderWithSelf(String oldPath, String newPath) {  
        try {  
            new File(newPath).mkdirs(); //如果文件夹不存在 则建立新文件夹  
            File dir = new File(oldPath);  
            // 目标  
            newPath +=  File.separator + dir.getName();  
            File moveDir = new File(newPath);  
            if(dir.isDirectory()){  
                if (!moveDir.exists()) {  
                    moveDir.mkdirs();  
                }  
            }  
            String[] file = dir.list();  
            File temp = null;  
            for (int i = 0; i < file.length; i++) {  
                if (oldPath.endsWith(File.separator)) {  
                    temp = new File(oldPath + file[i]);  
                } else {  
                    temp = new File(oldPath + File.separator + file[i]);  
                }  
                if (temp.isFile()) {  
                    FileInputStream input = new FileInputStream(temp);  
                    FileOutputStream output = new FileOutputStream(newPath +  
                            "/" +  
                            (temp.getName()).toString());  
                    byte[] b = new byte[1024 * 5];  
                    int len;  
                    while ((len = input.read(b)) != -1) {  
                        output.write(b, 0, len);  
                    }  
                    output.flush();  
                    output.close();  
                    input.close();  
                }  
                if (temp.isDirectory()) { //如果是子文件夹  
                    copyFolderWithSelf(oldPath + "/" + file[i], newPath);  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
  
    /** 
     * @param args 
     * @throws Exception  
     */  
    public static void main(String[] args) throws Exception {  
        // TODO Auto-generated method stub  
        FileUtils databean=new FileUtils();  
        //File fl=new File("F:\\111\\222\\333\\444\\");  
        //fl.mkdirs();//可以直接建立多级目录  
        //databean.MoveFolderAndFileWithSelf("F:\\JBuilder 2006 开发J2EE服务器应用程序.pdf","J:\\J\\");  
        //databean.MoveFolderAndFileWithSelf("F:\\test","J:\\J\\www");  
          
        databean.CopySingleFile("E:/apache-tomcat-6.0.35/webapps/hui/upload/temp/3d4b094e-f85b-4d31-abee-6f830c9f18b5.jpg","E:/apache-tomcat-6.0.35/webapps/hui/upload/question/original/3d4b094e-f85b-4d31-abee-6f830c9f18b5.jpg");  
        //databean.copyFolderWithSelf("F:\\test","J:\\J\\www");  
        System.out.println("完成");  
    } 
}
