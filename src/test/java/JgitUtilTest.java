
import org.eclipse.jgit.api.Git;

public class JgitUtilTest {
  public static void main(String[] args) throws Exception {
    JgitUtil.checkOut();
//    Git git = Git.open(localRepo);
    Git git = JgitUtil.init();
    JgitUtil.remoteAdd(git);
    JgitUtil.pull(git);
    JgitUtil.add(git, "404.md");
    JgitUtil.rm(git, "ReadMe.md");
    JgitUtil.commit(git, "8888");
    JgitUtil.push(git);
  }
}
