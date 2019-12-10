import com.jogamp.opengl.math.Matrix4;

public class Ball {
    float[] position;
    float[] velocity;
    float[][] Matrix = new float[4][4];
    public Ball(float[] position,float[] velocity){
        this.position = position;
        this.velocity = velocity;
        Matrix[0][0] = 1.0f;
        Matrix[1][1] = 1.0f;
        Matrix[2][2] = 1.0f;
        Matrix[3][3] = 1.0f;
        Matrix[0][3] = position[0];
        Matrix[1][3] = position[1];
        Matrix[2][3] = position[2];
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

    public float distance(Ball ball){
        float[] a = this.position;
        float[] b = ball.position;
        return (float)Math.sqrt((a[0]-b[0])*(a[0]-b[0])+(a[1]-b[1])*(a[1]-b[1])+(a[2]-b[2])*(a[2]-b[2]));
    }
    public boolean collision(Ball ball){
        // r of ball is 0.5, when distance < 2r, ball collision happens.
        if(distance(ball)<1.0f){
            return true;
        }
        return false;
    }

    public float[][] toMatrix(){
        return this.Matrix;
    }
    //convert 2D matrix to 1D
    public float[] toOneDMatrix(){
        return Function.convertMatrix(this.Matrix);
    }

}
