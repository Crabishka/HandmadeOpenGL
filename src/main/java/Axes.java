public class Axes {
    int width;
    int height;
    Vertex center;

    public void setCenter(Vertex center) {
        this.center = center;
    }

    public Vertex getCenter() {
        return center;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Axes(int width, int height) {
        this.width = width;
        this.height = height;
        center = new Vertex(width/2,height/2,0);
    }
}
