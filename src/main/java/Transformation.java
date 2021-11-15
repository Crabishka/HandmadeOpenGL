public class Transformation {
    // все преобразования - умножение матриц
    // А - ВСЕГДА матрица точек
    // B - ВСЕГДА матрица преобразования


    Model model;
    double[][] A;

    public Transformation(Model model) {
        this.model = model;
        this.A = getA();
    }


    private double[][] getA() {
        int length = model.getVertices().length;
        this.A = new double[length][];
        for (int i = 0; i < length; i++) {
            Vertex vertex = model.getVertices()[i];
            double[] vertexLine = new double[]{vertex.getX(), vertex.getY(), vertex.getZ(), 1};
            this.A[i] = vertexLine;
        }
        return this.A;
    }

    private void multiplyMatrix(double[][] B) {
        double[][] A = getA();
        double[][] res = new double[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                for (int k = 0; k < B.length; k++) {
                    res[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        model.setVertices(res);
    }


    public void scaleModel(double a, double e, double j) {
        double[][] B = new double[][]{
                {a, 0, 0, 0},
                {0, e, 0, 0},
                {0, 0, j, 0},
                {0, 0, 0, 1}};
        multiplyMatrix(B);
    }

    public void rotateModel(double angleX, double angleY, double angleZ) {  // can be optimized



        if (angleX != 0) {
            angleX = angleX*Math.PI/180.0;
            double[][] B = new double[][]{
                    {1, 0, 0, 0},
                    {0, Math.cos(angleX), Math.sin(angleX), 0},
                    {0, -Math.sin(angleX), Math.cos(angleX), 0},
                    {0, 0, 0, 1}
            };
            multiplyMatrix(B);
        }
        if (angleY != 0) {
            angleY = angleY*Math.PI/180.0;
            double[][] B = new double[][]{
                    {Math.cos(angleY), 0, -Math.sin(angleY), 0},
                    {0, 1, 0, 0},
                    {Math.sin(angleY), 0, Math.cos(angleY), 0},
                    {0, 0, 0, 1}
            };
            multiplyMatrix(B);
        }
        if (angleZ != 0) {
            angleZ = angleZ*Math.PI/180.0;
            double[][] B = new double[][]{
                    {Math.cos(angleZ), -Math.sin(angleZ), 0, 0},
                    {Math.sin(angleZ), Math.cos(angleZ),0 , 0},
                    {0, 0, 1, 0},
                    {0, 0, 0, 1}
            };
            multiplyMatrix(B);
        }

    }

    public void moveModel(double x, double y, double z) {
        double[][] B = new double[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {x, y, z, 1}
        };
        multiplyMatrix(B);
    }

}
