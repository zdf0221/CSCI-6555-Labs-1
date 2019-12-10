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

    // Original Boid Position
    public static final float[][] position = {
            { -3.0f, 7.0f, -5.6f },
            { 9.0f, 8.5f, -4.0f },
            { 4.0f, 7.2f, -5.7f },
            { -4.5f, 6.8f, -5.8f },
            { 3.0f, 8.6f, -5.0f },
            { 5.0f, 9.8f, -4.9f },
            { -4.0f, 9.0f, -4.9f },
            { 4.0f, 12.0f, -4.5f },
            { 0.0f, 8.2f, -5.0f },
            { 1.0f, 7.6f, -4.5f },
            { -2.0f, 8.0f, -4.3f },
            { 10.0f, 9.5f, -3.9f },
            { 5.0f, 8.2f, -4.2f },
            { -3.5f, 7.8f, -4.1f },
            { 4.0f, 9.6f, -4.9f },
            { 6.0f, 10.8f, -4.8f },
            { -3.0f, 10.0f, -4.8f },
            { 5.0f, 13.0f, -4.4f },
            { 1.0f, 9.2f, -4.9f },
            { 2.0f, 8.6f, -4.4f } };

    // Total number of boids
    public static final int number = position.length;

    // Original Boid Velocity in X, Y, Z axis
//    public static final float[][] velocity = {
//            { 1.5f, 0, 0 },
//            { -1, 0, 0 },
//            { -1, 0, 0 },
//            { 1.2f, 0, 0 },
//            { -1, 0, 0 },
//            { 0.8f, 0, 0 },
//            { -1, 0, 0 },
//            { 0.5f, 0, 0 },
//            { -1, 0, 0 },
//            { 0.7f, 0, 0 },
//            { 0.6f, 0, 0 },
//            { -1, 0, 0 },
//            { -1, 0, 0 },
//            { 0.3f, 0, 0 },
//            { -1, 0, 0 },
//            { 0.4f, 0, 0 },
//            { -1, 0, 0 },
//            { 0.6f, 0, 0 },
//            { -1, 0, 0 },
//            { 0.7f, 0, 0 }};

    // 7 Points represented in Euler Angle
    //the first 3 numbers represent x_angle, y_angle, z_angle in Euler angle
    // and the rest 3 numbers represent the position x,y,z in world Cartesian System
    public static final float[][] points = {
            { 90, 0, 45, -3, 7.0f, -8f },
            { 70, 20, 65, -8, -2, -9f },
            { 50, 40, 85, -5, 6, -10 },
            { 30, 60, 105, 5, -8, -10 },
            { 50, 40, 85, 3, -10, -5 },
            { 70, 20, 65, -3, -14, -5 },
            { 90, 0, 45, 1, -18, -3 }
    };

    public static final int pointsNumber = points.length;

}
