public class Quaternion {
    public float w,x,y,z; //quaternion
    public float px,py,pz;//position
    public Quaternion(float w,float x,float y,float z,float px,float py,float pz){
        //w,x,y,z represent quaternion
        //x,y,z represent the position in world Cartesian System
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
        this.px = px;
        this.py = py;
        this.pz = pz;
        this.Normalization();//get a normal quaternion
    }

    public Quaternion(float[] m){
        assert (m.length==7):"Not a valid quaternion";
        this.w = m[0];
        this.x = m[1];
        this.y = m[2];
        this.z = m[3];
        this.px = m[4];
        this.py = m[5];
        this.pz = m[6];
        this.Normalization();
    }
    public float[] getPosition(){
        return new float[] {px,py,pz};
    }

    public float[] getPoint(){
        return new float[] {w,x,y,z,px,py,pz};
    }

    // Unit Quaternion
    public void Normalization(){
        float magnitude = (float)Math.sqrt(w*w+x*x+y*y+z*z);
        //avoid divide 0
        if(magnitude!=0){
            w = w/magnitude;
            x = x/magnitude;
            y = y/magnitude;
            z = z/magnitude;
        }
    }

    //get the rotation matrix R and transition matrix T, return T*R
    public float[][] toMatrix(){
        float[][] rotation = {
                {1-2*y*y-2*z*z,2*x*y-2*w*z,2*x*z+2*w*y,0},
                {2*x*y+2*w*z,1-2*x*x-2*z*z,2*y*z-2*w*x,0},
                {2*x*z-2*w*y,2*y*z+2*w*x,1-2*x*x-2*y*y,0},
                {0,0,0,1}
        };
        float[][] T = {
                {1,0,0,px},
                {0,1,0,py},
                {0,0,1,pz},
                {0,0,0,1}
        };

        return Function.multiplyMatrix(T,rotation);
    }

    //convert 2D matrix to 1D
    public float[] toOneDMatrix(){
        float[][] matrix = toMatrix();
        return Function.convertMatrix(matrix);
    }

}
