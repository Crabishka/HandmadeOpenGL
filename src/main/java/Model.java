import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Model {

    Vertex[] vertices;
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

    public void sort() {
        Arrays.sort(surfaces, (o1, o2) -> {
            double minY1 = Math.min(o1.vertices[0].y, Math.min(o1.vertices[2].y, o1.vertices[1].y));
            double minY2 = Math.min(o2.vertices[0].y, Math.min(o2.vertices[2].y, o2.vertices[1].y));
            if (minY1 > minY2) return -1;
            else if (minY1 < minY2) return 1;
            else return 0;
        });
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
