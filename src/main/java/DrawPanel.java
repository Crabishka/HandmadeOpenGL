import javax.swing.*;
import java.awt.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        int n = 2;
        Model model = null;
        switch (n) {
            case 1:{
                model = ObjectParsingService.readFromFile("src/main/resources/car.obj");
                break;
            }
            case 2:{
                model = ObjectParsingService.readFromFile("src/main/resources/cube.obj");
                break;
            }
            case 3:{
                model = ObjectParsingService.readFromFile("src/main/resources/african_head.obj");
                break;
            }
        }

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
        drawAxes(g);
        drawModels(g, listOfModel);

    }

    public void drawModels(Graphics2D graphics2D, List<Model> models) {
        graphics2D.setStroke(new BasicStroke(1));
        graphics2D.setColor(Color.BLACK);
        Vertex lightDir = new Vertex(0, 0, -1);
        for (Model model : models) {
            //      model.sort();
            for (int i = 0; i < model.surfaces.length; i++) {

                Surface surface = model.surfaces[i];
                double offsetX = axes.getWidth() / 2.;
                double offsetY = axes.getHeight() / 2.;
                int x1 = (int) (surface.vertices[0].getX() * width / 2 + offsetX);
                int x2 = (int) (surface.vertices[1].getX() * width / 2 + offsetX);
                int x3 = (int) (surface.vertices[2].getX() * width / 2 + offsetX);
                int y1 = (int) (offsetY - surface.vertices[0].getY() * height / 2);
                int y2 = (int) (offsetY - surface.vertices[1].getY() * height / 2);
                int y3 = (int) (offsetY - surface.vertices[2].getY() * height / 2);

                Polygon polygon = new Polygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);
                Vertex n = vectorMultiply(vectorSub(surface.vertices[2], surface.vertices[0]), vectorSub(surface.vertices[1], surface.vertices[0]));
                vectorNormalize(n);
                double intensive = scalarMultiply(n, lightDir);

//                Random random = new Random();
//                graphics2D.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
//                graphics2D.fill(polygon);
//                graphics2D.drawPolygon(polygon);

                if (intensive > -0.001) {
                    graphics2D.setColor(new Color((int) (intensive * 255), (int) (intensive * 255), (int) (intensive * 255), 255));
                    graphics2D.fill(polygon);
                    graphics2D.drawPolygon(polygon);
                }


            }
        }
    }

    public Vertex vectorSub(Vertex a, Vertex b) {
        return new Vertex(a.x - b.x, a.y - b.y, a.z - b.z);
    }

    public Vertex vectorMultiply(Vertex a, Vertex b) {
        double tmpX = a.y * b.z - a.z * b.y;
        double tmpY = -(a.x * b.z - a.z * b.x);
        double tmpZ = a.x * b.y - a.y * b.x;
        return new Vertex(tmpX, tmpY, tmpZ);
    }

    public void vectorNormalize(Vertex a) {
        double length = Math.sqrt(a.x * a.x + a.y * a.y + a.z * a.z);
        length = 1 / length;
        a.setX(a.getX() * length);
        a.setY(a.getY() * length);
        a.setZ(a.getZ() * length);
    }

    public double scalarMultiply(Vertex a, Vertex b) {
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }

    public void drawAxes(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawLine(0, height / 2, width, height / 2);
        graphics2D.drawLine(width / 2, 0, width / 2, height);
    }


    public void clearGraphics2D(Graphics2D gr) {
        gr.setColor(Color.BLACK);
    }


}
