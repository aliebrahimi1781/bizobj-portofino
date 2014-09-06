import java.io.File;
import java.net.URL;

import org.bizobj.jetty.ContextStarter;
import org.eclipse.jetty.util.resource.FileResource;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.resource.ResourceCollection;

/**
 * Start the test App.
 * @author root
 */
public class StartApp {

    public static void main(String[] args) throws Exception {
    	System.setProperty(ContextStarter.VAR_CTX_PATH, "pf");
    	
        //bizobj-portofino-test-StartApp.properties (As a place-holder) should be compiled into test-project/target/classes ...
    	URL holder = StartApp.class.getResource("/bizobj-portofino-test-StartApp.properties");
        File fh = new File(holder.toURI());
        //find the root of main and test project
        String root = fh.getParentFile().getParentFile().getParentFile().getParentFile().getCanonicalPath();
        //define the war resource
        Resource testWar = new FileResource(new File(root + "/test-project/src/main/webapp").toURI());
        Resource mainWar = new FileResource(new File(root + "/main-project/src/main/webapp").toURI());
        Resource rc = new ResourceCollection(testWar, mainWar);
        ContextStarter.startServer(rc, new File(root + "/test-project/src/main/webapp/WEB-INF/web.xml"));
    }
}
