import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpUtil {
  static Logger logger = Logger.getLogger(SftpUtil.class.getName());

  private static Map<String, Object> connect(String url, String user, String password) {
    Session session = null;
    ChannelSftp channelSftp = null;
    Map<String, Object> connection = new HashMap<>();

    JSch jsch = new JSch();
    try {
      session = jsch.getSession(user, url);
      session.setPassword(password);
      Properties config = new Properties();
      config.put("StrictHostKeyChecking", "no");
      session.setConfig(config);
      session.connect();
      channelSftp = (ChannelSftp) session.openChannel("sftp");
      channelSftp.connect();
    } catch (JSchException e) {
      logger.log(Level.SEVERE, "SFTP 접속 실패", e);
    }
    connection.put("session", session);
    connection.put("channelSftp", channelSftp);
    return connection;
  }

  // 단일 파일 업로드
  public static boolean upload(String url, String user, String password, String remoteDir, File uploadFile) {
    Map<String, Object> connection = connect(url, user, password);
    ChannelSftp channelSftp = (ChannelSftp) connection.get("channelSftp");
    FileInputStream in = null;
    try { // 파일을 가져와서 inputStream에 넣고 저장경로를 찾아 put
      in = new FileInputStream(uploadFile);
      channelSftp.cd(remoteDir);
      channelSftp.put(in, uploadFile.getName());
    } catch (SftpException se) {
      logger.log(Level.WARNING, "파일업로드 실패", se);
      return false;
    } catch (FileNotFoundException fe) {
      logger.log(Level.WARNING, "파일을 찾을 수 없습니다.", fe);
      return false;
    } finally {
      try {
        in.close();
        disconnect(connection);
      } catch (IOException ioe) {
        logger.log(Level.WARNING, "in.close() 실패", ioe);
      }
    }
    return true;
  }

  public static InputStream download(String url, String user, String password, String dir, String fileNm) {
    Map<String, Object> connection = connect(url, user, password);
    ChannelSftp channelSftp = (ChannelSftp) connection.get("channelSftp");
    InputStream in = null;
    try {
      channelSftp.cd(dir);
      in = channelSftp.get(fileNm);
    } catch (SftpException se) {
      logger.log(Level.WARNING, "파일다운로드 실패", se);
    } finally {
      disconnect(connection);
    }
    return in;
  }

  private static void disconnect(Map<String, Object> connection) {
    ChannelSftp channelSftp = (ChannelSftp) connection.get("channelSftp");
    Session session = (Session) connection.get("session");
    if (channelSftp != null && channelSftp.isConnected()) {
      channelSftp.disconnect();
    }
    if (session != null && session.isConnected()) {
      session.disconnect();
    }
  }
}
