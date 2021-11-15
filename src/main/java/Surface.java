public class Surface {
    Vertex[] vertices;
    Vertex[] textureVertices;
    Vertex[] normalVertices;

    public Surface(Vertex[] vertices, Vertex[] textureVertices, Vertex[] normalVertices) throws SurfaceException {
        if (vertices.length != 3 || textureVertices.length != 3 || normalVertices.length != 3){
            throw new SurfaceException("Can't create Surface");
        }
        this.vertices = vertices;
        this.textureVertices = textureVertices;
        this.normalVertices = normalVertices;
    }
}
