package mon.sof.common.tool.sqlBackup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@PropertySource(value = {"classpath:config/proper/sql-backup.properties"})
@Configuration
public class SqlBackupTool {
    private static Logger log = LoggerFactory.getLogger(SqlBackupTool.class);

    @Value("${ip}")
    private String ip;
    @Value("${host}")
    private String host;
    @Value("${rootname}")
    private String rootname;
    @Value("${password}")
    private String password;
    @Value("${databaseName}")
    private String databaseName;
    @Value("${savePath}")
    private String savePath;
    @Value("${fileName}")
    private String fileName;

    @Value("${ftpHost}")
    private String ftpHost;
    @Value("${ftpPassword}")
    private String ftpPassword;
    @Value("${ftpUserName}")
    private String ftpUserName;
    @Value("${ftpPort}")
    private int ftpPort;



    /**
     * sql 数据库备份
     * @Author  zhangxiaomei
     * @Date    2019-11-28 10:33:48
     * @Param
     * @Return
     */
    public Boolean sqlBackupTack(){
         File file = new File(savePath);
        if(!file.exists()){
            file.mkdirs();
        }
        if(!savePath.endsWith(File.separator)){
            savePath = savePath + File.separator;
        }
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            String format = sd.format(new Date());
            String sqlFilePath = savePath + (fileName+format+".sql");
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(sqlFilePath), "utf8"));
            String sql = " mysqldump -h " + ip + " -P"+ host + " -u" + rootname + " -p"+ password + " --default-character-set=utf8 " +databaseName;
            log.debug("执行数据库语句+++："+sql);
            Process process = Runtime.getRuntime().exec(sql);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while((line = bufferedReader.readLine())!= null){
                printWriter.println(line);
            }
            printWriter.flush();
            try {
                if(process.waitFor() == 0){//0 表示线程正常终止。
                    File file1 = new File(sqlFilePath);
                    InputStream in = new FileInputStream(file1);
                    WriteFTPFile.upload(file1.getName(),ftpUserName,ftpPassword,ftpHost,ftpPort,in);
                    return true;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
