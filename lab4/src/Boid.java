public class Boid {
    float[] position;
    float[] velocity;
    float[][] Matrix = new float[4][4];
    float[] r1,r2,r3,r4;
    public Boid(){
    }
    public Boid(float[] position, float[] velocity){
        this.position = position;
        this.velocity = velocity;
        Matrix[0][0] = 1.0f;
        Matrix[1][1] = 1.0f;
        Matrix[2][2] = 1.0f;
        Matrix[3][3] = 1.0f;
        Matrix[0][3] = position[0];
        Matrix[1][3] = position[1];
        Matrix[2][3] = position[2];

        this.r1 = new float[3];
        this.r2 = new float[3];
        this.r3 = new float[3];
        this.r4 = new float[3];

    }
    public void setVelocity(float[] velocity){
        this.velocity = velocity;
    }

    public void setPosition(float[] position){
        this.position = position;
        Matrix[0][3] = position[0];
        Matrix[1][3] = position[1];
        Matrix[2][3] = position[2];
    }

    public float distance(Boid boid){
        float[] a = this.position;
        float[] b = boid.position;
        return (float)Math.sqrt((a[0]-b[0])*(a[0]-b[0])+(a[1]-b[1])*(a[1]-b[1])+(a[2]-b[2])*(a[2]-b[2]));
    }

    public float[][] toMatrix(){
        return this.Matrix;
    }
    //convert 2D matrix to 1D
    public float[] toOneDMatrix(){
        return Function.convertMatrix(this.Matrix);
    }

}
