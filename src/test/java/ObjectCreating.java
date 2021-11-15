import java.io.IOException;

import junit.framework.TestCase;

public class ObjectCreating extends TestCase {
    public void testReadFromFile() throws IOException, SurfaceException {
        ObjectParsingService.readFromFile("src/main/resources/model2.obj");
    }
}
