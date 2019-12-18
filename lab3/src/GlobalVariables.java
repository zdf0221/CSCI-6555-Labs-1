public class GlobalVariables {

    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;


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

    // Original Ball Position
    public static final float[][] position = {
            { -3.0f, 7.0f, 0.6f },
            { 9.0f, 8.5f, 1.0f },
            { 4.0f, 7.2f, 0.7f },
            { -4.5f, 6.8f, 0.8f },
            { 3.0f, 8.6f, 0.0f },
            { 5.0f, 9.8f, 0.1f },
            { -4.0f, 9.0f, 0.1f },
            { 4.0f, 12.0f, 0.5f },
            { 0.0f, 8.2f, 0.0f },
            { 1.0f, 7.6f, 0.5f } ,
            { -2.0f, 8.0f, 0.7f },
            { 10.0f, 9.5f, 1.1f },
            { 5.0f, 8.2f, 0.8f },
            { -3.5f, 7.8f, 0.9f },
            { 4.0f, 9.6f, 0.1f },
            { 6.0f, 10.8f, 0.2f },
            { -3.0f, 10.0f, 0.2f },
            { 5.0f, 13.0f, 0.6f },
            { 1.0f, 9.2f, 0.1f },
            { 2.0f, 8.6f, 0.6f }
    };

    // Total number of balls
    public static final int number = position.length;

    // Original Ball Velocity in X, Y, Z axis
    public static final float[][] velocity = {
            { 1.5f, 0, 0 },
            { -1, 0, 0 },
            { -1, 0, 0 },
            { 1.2f, 0, 0 },
            { -1, 0, 0 },
            { 0.8f, 0, 0 },
            { -1, 0, 0 },
            { 0.5f, 0, 0 },
            { -1, 0, 0 },
            { 0.7f, 0, 0 },
            { 0.6f, 0, 0 },
            { -1, 0, 0 },
            { -1, 0, 0 },
            { 0.3f, 0, 0 },
            { -1, 0, 0 },
            { 0.4f, 0, 0 },
            { -1, 0, 0 },
            { 0.6f, 0, 0 },
            { -1, 0, 0 },
            { 0.7f, 0, 0 }};

}
