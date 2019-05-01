package com.demo.action;

import com.demo.entity.impl.User;
import com.demo.service.impl.UserService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileAction implements BaseAction{
    private File file;
    private String fileFileName;
    private String fileContentType;
    private String selectedFile;
    private InputStream inputStream;
    private OutputStream outputStream;
    private Map<String,Object> msg;
    private UserService userService;

    public void setUserService(UserService userService){
        this.userService=userService;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getSelectedFile() {
        return selectedFile;
    }

    public void setSelectedFile(String selectedFile) {
        this.selectedFile = selectedFile;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public Map<String, Object> getMsg() {
        return msg;
    }

    public void setMsg(Map<String, Object> msg) {
        this.msg = msg;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public String downloadFile(){

        return "downloadfile";
    }

    public String downloadFileList(){
        msg=new HashMap<>();
        List<File> arrayList=new ArrayList<>();
        List<String> files=new ArrayList<>();
        Map<String,Object> session=ActionContext.getContext().getSession();
        String path=ServletActionContext.getServletContext().getRealPath("/upload");
        if (selectedFile!=null){
            path=ServletActionContext.getServletContext().getRealPath("/upload/"+selectedFile);
        }
        File filelists=new File(path);
        if (filelists.isDirectory()){
            File[] fileArray=filelists.listFiles();
            for (int i=0;i<fileArray.length;i++){
                arrayList.add(fileArray[i]);
                files.add(fileArray[i].getName());
            }
            msg.put("filelist",arrayList);
            session.put("filelist",arrayList);
            return "filelist";
        }else {
            try {
                inputStream=new FileInputStream(filelists);
                fileContentType=ServletActionContext.getServletContext().getMimeType(selectedFile);
                return "download";
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "error";
            }
        }
    }

    public String uploadFile(){
        msg=new HashMap<>();
        String path=ServletActionContext.getServletContext().getRealPath("/upload");
        System.out.println("File Save Path : "+path);
        File destFile=new File(path,fileFileName);

        try {
            FileUtils.copyFile(file,destFile);
            msg.put("resCode",1);
            msg.put("message","上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            msg.put("resCode",0);
            msg.put("message","上传失败");
            return "uploadfile";
        }
        return "uploadfile";
    }

    public String uploadHeadPhoto(){
        msg=new HashMap<>();
        //UserService service=new UserService();
        ActionContext actionContext=ActionContext.getContext();
        Map<String,Object> session=actionContext.getSession();
        User user= (User) session.getOrDefault("user",null);

        String path=ServletActionContext.getServletContext().getRealPath("/image");
        System.out.println("File Save Path : "+path);

        File destFile=new File(path,fileFileName);

        try {
            FileUtils.copyFile(file,destFile);
            msg.put("resCode",1);
            msg.put("message","上传成功");


            if (user!=null){
                user.setHeadpicture("http://localhost:8080/Demo2/image/"+fileFileName);
                int res=userService.update(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
            msg.put("resCode",0);
            msg.put("message","上传失败");
            return "uploadfile";
        }
        return "uploadfile";
    }
}
