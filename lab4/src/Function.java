public class Function {

    //matrix multiplication
    public static float[][] multiplyMatrix(float[][] a,float[][]b) {
        assert (a[0].length == b.length) : "Matrix can't be multiplied";

        float[][] product = new float[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    product[i][j] += a[i][k] * b[k][j];
                }

            }
        }
        return product;
    }

    //convert 2D matrix to 1D
    public static float[] convertMatrix(float[][] m){
        float[] M = new float[m.length*m[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                M[i+j*m.length] = m[i][j];
            }
        }
        return M;
    }

    //vector normalization
    public static void vectorNormal(float[] v){
        assert (v.length==3):"invalid vector";
        float x = v[0];
        float y = v[1];
        float z = v[2];
        float magnitude = (float)Math.sqrt(x*x+y*y+z*z);
        //avoid divide 0
        if(magnitude!=0){
            x = x/magnitude;
            y = y/magnitude;
            z = z/magnitude;
        }
        v[0] = x;
        v[1] = y;
        v[2] = z;
    }

    //vector cross product
    public static float[] vectorCross(float[] a,float[] b){
        assert (a.length==3&&b.length==3):"invalid vector";
        float[] cross = new float[3];
        cross[0] = a[1]*b[2]-b[1]*a[2];
        cross[1] = a[2]*b[0]-b[2]*a[0];
        cross[2] = a[0]*b[1]-b[0]*a[1];

        return cross;
    }

    //vector dot product
    public static float vectorDot(float[] a,float[] b){
        assert (a.length==3&&b.length==3):"invalid vector";
        float dot = 0;
        for (int i = 0; i < a.length; i++) {
            dot+=a[i]*b[i];
        }
        return dot;
    }

    //display matrix
    public static void displayProduct(float[][] product) {
        System.out.println("Product matrix is: ");
        for(float[] row : product) {
            for (float column : row) {
                System.out.print(column + "    ");
            }
            System.out.println();
        }
    }

    // Blending Function : Q(t) = T*M*G, finding out the vector position of time t
    public static float[] blend(float[][] T,float[][] MS, float[][] G)
    {
        // B is the result of T*M
        float[][] B = multiplyMatrix(T,MS);

        // Generate the result of T*M*G
        float[][] Qt = multiplyMatrix(B,G);
        //displayProduct(Qt);
        return convertMatrix(Qt);
    }

    public static Quaternion qInterpolation(String splineType,float t,int pointIndex){
        float[][] MS;
        if(splineType.equals("Catmull-Rom Spline")){
            MS = GlobalVariables.CRSplineM;
        }
        else if(splineType.equals("B Spline")){
            MS = GlobalVariables.BSplineM;
        }
        else return null;
        float[][] T = {{t*t*t,t*t,t,1}};
        float[][] G = new float[4][7];

        for (int i = 0; i <G.length ; i++) {
            //4 points
            EulerAngle e = new EulerAngle(GlobalVariables.points[(pointIndex+i)%GlobalVariables.pointsNumber]);
            //convert points in euler angle to quaternion
            G[i] = e.toQuaternion().getPoint();
        }

        return new Quaternion(blend(T,MS,G));

    }

}
