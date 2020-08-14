import java.io.File;
import java.util.Collection;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.RemoteAddCommand;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

/**
 * jgit maven
 * 
 * <pre>
 * <!-- https://mvnrepository.com/artifact/org.eclipse.jgit/org.eclipse.jgit -->
 * <dependency> 
 *   <groupId>org.eclipse.jgit</groupId>
 *   <artifactId>org.eclipse.jgit</artifactId>
 *   <version>5.8.1.202007141445-r</version> 
 * </dependency>
 * </pre>
 */

public class JgitUtil {

  private static String userId = "";
  private static String userPass = "";
  private static String userName = "";
  private static String userEmail = "";
  private static String hash = "origin/master";
  private static String url = "https://github.com/xxx.git";
  private static CredentialsProvider cp = new UsernamePasswordCredentialsProvider(userId, userPass);
  private static File localRepo = new File("d:\\test\\tmp");

  public static Git init() throws Exception {
    return Git.init().setDirectory(localRepo).call();
  }

  public static void remoteAdd(Git git) throws Exception {
    // add remote repo:
    RemoteAddCommand remoteAddCommand = git.remoteAdd();
    remoteAddCommand.setName("origin");
    remoteAddCommand.setUri(new URIish(url));
    // you can add more settings here if needed
    remoteAddCommand.call();
  }

  public static void push(Git git) throws Exception {
    // push to remote:
    PushCommand pushCommand = git.push();
    pushCommand.setCredentialsProvider(cp);
    pushCommand.setForce(true);
    // you can add more settings here if needed
    pushCommand.call();
  }

  public static void add(Git git, String filePattern) throws Exception {
    git.add().addFilepattern(filePattern).call();
  }

  public static void rm(Git git, String filePattern) throws Exception {
    git.rm().addFilepattern(filePattern).call();
  }

  public static void commit(Git git, String msg) throws Exception {
    // Now, we do the commit with a message
    git.commit()//
        .setAuthor(userName, userEmail)//
        .setMessage(msg)//
        .call();
  }

  public static void pull(Git git) throws Exception {
    PullCommand pull = git.pull();
    pull.call();
  }

  public static void lsRemote(Git git) throws Exception {
    Collection<Ref> remoteRefs = git.lsRemote().setCredentialsProvider(cp).setRemote("origin").setTags(false).setHeads(true).call();
    for (Ref ref : remoteRefs) {
      System.out.println(ref.getName() + " -> " + ref.getObjectId().name());
    }
  }

  public static void checkOut() throws Exception {
    Git gitRepo = Git.cloneRepository().setURI(url) // remote 주소
        .setDirectory(localRepo) // 다운받을 로컬의 위치
        .setNoCheckout(true)//
        .setCredentialsProvider(cp) // 인증 정보
        .call();
    gitRepo.checkout().setStartPoint(hash) // origin/branch_name
        //.addPath("not thing") // 다운받을 대상 경로
        .call();

    gitRepo.getRepository().close();
  }
}
