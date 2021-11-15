import javax.swing.*;
import java.awt.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {

    List<Model> listOfModel = new ArrayList<>();

    public List<Transformation> getTransformations() {
        return transformations;
    }

    List<Transformation> transformations = new ArrayList<>();

    Axes axes;
    int width;
    int height;


    DrawPanel() throws IOException, SurfaceException {
        Model model = ObjectParsingService.readFromFile("src/main/resources/african_head.obj");
        listOfModel.add(model);
        transformations.add(new Transformation(model));
        setSize(900, 900);
        this.width = 900;
        this.height = 900;
        axes = new Axes(this.getWidth(), this.getHeight());

    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        this.width = width;
        this.height = height;
    }


    public void paint(Graphics gr) {
        super.paint(gr);
        Graphics2D g = (Graphics2D) gr;
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, 900, 900);
        setSize(900, 900);
        drawModels(g, listOfModel);
        drawAxes(g);
    }

    public void drawModels(Graphics2D graphics2D, List<Model> models) {
        graphics2D.setStroke(new BasicStroke(1));
        graphics2D.setColor(Color.BLACK);
        for (Model model : models) {
            for (int i = 0; i < model.surfaces.length; i++) {
                Surface surface = model.surfaces[i];
                for (int j = 0; j < 3; j++) {
                    //   Vertex center = axes.center;
                    Vertex v0 = surface.vertices[j];
                    Vertex v1 = surface.vertices[(j + 1) % 3];
                    int width = axes.getWidth();
                    int height = axes.getHeight();
                    double offsetX = axes.getWidth() / 2;
                    double offsetY = axes.getHeight() / 2;
                    int x0 = (int) ((v0.getX()) * width / 2 + offsetX);
                    int y0 = (int) (offsetY - (v0.getY()) * height / 2);
                    int x1 = (int) ((v1.getX()) * width / 2 + offsetX);
                    int y1 = (int) (offsetY - (v1.getY()) * height / 2);
                    graphics2D.drawLine(x0, y0, x1, y1);
                }
            }
        }
    }


    public void drawAxes(Graphics2D graphics2D) {

    }


    public void clearGraphics2D(Graphics2D gr) {
        gr.setColor(Color.BLACK);
    }


}
