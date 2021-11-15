import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Model {

    Vertex[] vertices;
    Vertex[] normals;
    Surface[] surfaces;

    public Model(Vertex[] vertices, Surface[] surfaces) {
        this.vertices = vertices;
        this.surfaces = surfaces;
    }

    public Model(Model model) {
        this.vertices = model.getVertices().clone();
        this.surfaces = model.getSurfaces().clone();
    }


    public void setVertices(double[][] points) {
        for (int i = 0; i < points.length; i++) {
            vertices[i].setX(points[i][0]);
            vertices[i].setY(points[i][1]);
            vertices[i].setZ(points[i][2]);
        }


    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public void setVertices(Vertex[] vertices) {
        this.vertices = vertices;
    }

    public Surface[] getSurfaces() {
        return surfaces;
    }

    public void setSurfaces(Surface[] surfaces) {
        this.surfaces = surfaces;
    }


}
