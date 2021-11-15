import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectParsingService {
    /**
     * this class take .obj file and return class Model
     */
    public static Model readFromFile(String filePath) throws IOException, SurfaceException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String line;
        List<Vertex> vertexList = new ArrayList<>();
        List<Vertex> textureVertexList = new ArrayList<>();
        List<Vertex> normalVertexList = new ArrayList<>();
        List<Surface> surfaceList = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            if (line.equals("")) continue;
            if (line.startsWith("#")) continue;
            String[] tokens = line.split("\\s+");

            if (tokens[0].equals("v")) {
                vertexList.add(new Vertex(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3])));
            } else if (tokens[0].equals("vt")) {
                textureVertexList.add(new Vertex(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3])));
            } else if (tokens[0].equals("vn")) {
                normalVertexList.add(new Vertex(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3])));
            }
            else if (tokens[0].equals("f")) {
                Vertex[] vertices = new Vertex[3];
                Vertex[] textureVertices = new Vertex[3];
                Vertex[] normalVertices = new Vertex[3];
                int[][] params = new int[][]{  // {v1,vt1,vn1}, {v2,vt2,vn2}, {v3,vt3,vn3}
                        {0, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}
                };
                for (int i = 0; i < 3; i++) {
                    String[] group = tokens[i + 1].split("[/]");
                    for (int j = 0; j < group.length; j++) {
                        if (group[j].equals("")) params[i][j] = 0;
                        else  params[i][j] = Integer.parseInt(group[j]);
                    }
                    for (int j = group.length; j < 3; j++) {
                        params[i][j] = 0;
                    }
                }
                for (int i = 0; i < 3; i++) {
                    // counts from 1
                    if (params[i][0] != 0) vertices[i] = vertexList.get(params[i][0] - 1);
                    else vertices[i] = null;
                    if (params[i][1] != 0) textureVertices[i] = textureVertexList.get(params[i][1] - 1);
                    else textureVertices[i] = null;
                    if (params[i][2] != 0) normalVertices[i] = normalVertexList.get(params[i][2] - 1);
                    else normalVertices[i] = null;
                }
                surfaceList.add(new Surface(vertices, textureVertices, normalVertices));
            }
        }
        return new Model(vertexList.toArray(new Vertex[0]), surfaceList.toArray(new Surface[0]));
    }
}


