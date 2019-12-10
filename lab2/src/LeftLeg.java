public class LeftLeg {
    //Left Leg
    float t;
    Torso torso;
    public LeftLeg(Torso torso){
        this.torso = torso;
    }
    public void setT(float t){
        this.t = t;
    }

    public float[][] toMatrix(){
        float[][] TAB = new float[4][4];

        float[][] T1 = {
            {1,0,0,0},
            {0,1,0,-0.95f},
            {0,0,1,0},
            {0,0,0,1}};

        float Angle = (float)(sin(4 * 3.14*t - 3.14 / 2)*3.14) / 4; // To animate rotation, change θ(t)
        float[][] T2={
                {cos(Angle),-sin(Angle),0,0},
                {sin(Angle),cos(Angle),0,0},
                {0,0,1,0},
                {0,0,0,1}
        };
        float[][] T3 = {
            {1,0,0,0},
            {0,1,0,-0.95f},
            {0,0,1,0.3f},
            {0,0,0,1}
    };

        TAB = Function.multiplyMatrix(torso.Matrix,T1);
        TAB = Function.multiplyMatrix(TAB,T2);
        TAB = Function.multiplyMatrix(TAB,T3);

        return TAB;

    }
    //convert 2D matrix to 1D
    public float[] toOneDMatrix(){
        float[][] matrix = toMatrix();
        return Function.convertMatrix(matrix);
    }

    public float cos(float angle){
        return (float)Math.cos(angle);
    }
    public float sin(float angle){
        return (float)Math.sin(angle);
    }
    public float sin(double angle){
        return (float)Math.sin(angle);
    }

}
