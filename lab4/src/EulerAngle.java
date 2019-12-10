public class EulerAngle {


    public float a,b,c; //rotate angle a by X axes, angle b by Y axes, angle c by Z axes
    public float x,y,z; //position

    public EulerAngle(float a, float b, float c, float x, float y, float z){
        //a,b,c represent x_angle, y_angle, z_angle in Euler angle
        //x,y,z represent the position in world Cartesian System
        this.a = a;
        this.b = b;
        this.c = c;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public EulerAngle(float[] m){
        assert (m.length==6):"Not a valid euler angle";
        this.a = m[0];
        this.b = m[1];
        this.c = m[2];
        this.x = m[3];
        this.y = m[4];
        this.z = m[5];
    }
    public float[] getPoint(){
        return new float[] {a,b,c,x,y,z};
    }

    // get the rotation matrix R and transition matrix T, return T*R
    public float[][] toMatrix(){
        //if rotate about z first, then y, then x
        float[][] Rz = {
                {cos(c),-sin(c),0,0},
                {sin(c),cos(c),0,0},
                {0,0,1,0},
                {0,0,0,1}
        };
        float[][] Ry = {
                {cos(b),0,sin(b),0},
                {0,1,0,0},
                {-sin(b),0,cos(b),0},
                {0,0,0,1}
        };
        float[][] Rx = {
                {1,0,0,0},
                {0,cos(a),-sin(a),0},
                {0,sin(a),cos(a),0},
                {0,0,0,1}
        };

        float[][] T = {
                {1,0,0,x},
                {0,1,0,y},
                {0,0,1,z},
                {0,0,0,1}
        };

        float[][]R =  Function.multiplyMatrix(Rx,Ry);
        R = Function.multiplyMatrix(R,Rz);
        return Function.multiplyMatrix(T,R);
    }

    //convert 2D matrix to 1D
    public float[] toOneDMatrix(){
        float[][] matrix = toMatrix();
        return Function.convertMatrix(matrix);
    }

    //convert euler angle to quaternion (from wikipedia)
    public Quaternion toQuaternion(){
        float a = this.a/2;
        float b = this.b/2;
        float c = this.c/2;
        float w = cos(c)*cos(b)*cos(a) + sin(c)*sin(b)*sin(a); //w
        float x = sin(c)*cos(b)*cos(a) - cos(c)*sin(b)*sin(a); //x
        float y = cos(c)*sin(b)*cos(a) + sin(c)*cos(b)*sin(a); //y
        float z = cos(c)*cos(b)*sin(a) - sin(c)*sin(b)*cos(a); //z

        return new Quaternion(w,x,y,z,this.x,this.y,this.z);
    }

    public float cos(float angle){
        return (float)Math.cos(angle);
    }
    public float sin(float angle){
        return (float)Math.sin(angle);
    }
}
