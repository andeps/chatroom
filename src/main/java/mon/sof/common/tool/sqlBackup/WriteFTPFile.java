package mon.sof.common.tool.sqlBackup;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class WriteFTPFile {

    private static Logger log = LoggerFactory.getLogger(WriteFTPFile.class);

    /**
     * 本地上传文件到FTP服务器
     * 远程文件路径FTP
     * @throws IOException
     */
    public static void upload(String fileName, String ftpUserName, String ftpPassword,
                       String ftpHost, int ftpPort,InputStream in) {
        FTPClient ftpClient = null;
        log.info("开始上传文件到FTP.");
        try {
            ftpClient = FTPUtil.getFTPClient(ftpHost, ftpPassword,
                    ftpUserName, ftpPort);
            // 设置PassiveMode传输
            ftpClient.enterLocalPassiveMode();
            // 设置以二进制流的方式传输
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 对远程目录的处理
            /*String remoteFileName = ftpPath;
            if (ftpPath.contains("/")) {
                remoteFileName = ftpPath
                        .substring(ftpPath.lastIndexOf("/") + 1);
            }*/
            ftpClient.storeFile(fileName, in);

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                in.close();
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 把配置文件先写到本地的一个文件中取
     * @return
     */
    public boolean write(String fileName, String fileContext,
                         String writeTempFielPath) {
        try {
            log.info("开始写配置文件");
            File f = new File(writeTempFielPath + "/" + fileName);
            if(!f.exists()){
                if(!f.createNewFile()){
                    log.info("文件不存在，创建失败!");
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
            bw.write(fileContext.replaceAll("\n", "\r\n"));
            bw.flush();
            bw.close();
            return true;
        } catch (Exception e) {
            log.error("写文件没有成功");
            e.printStackTrace();
            return false;
        }
    }
}