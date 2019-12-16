public class GlobalVariables {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;


    // The Catmull Rom Spline M Matrix
    public static final float a = 0.5f;
    public static final float[][] CRSplineM ={
            {-a,2.0f-a,a-2.0f,a},
            {2.0f*a,a-3.0f,3.0f-2.0f*a,-a},
            {-a,0.0f,a,0.0f},
            {0.0f,1.0f,0.0f,0.0f}
    };

    // The B Spline M Matrix
    public static final float[][] BSplineM={
            {-1.0f/6,3.0f/6,-3.0f/6,1.0f/6},
            {3.0f/6,-6.0f/6,3.0f/6,0.0f},
            {-3.0f/6,0.0f,3.0f/6,0.0f},
            {1.0f/6,4.0f/6,1.0f/6,0.0f}
        };


}
