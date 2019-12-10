public class Torso {
    //Torso
    float[] torsoPosition; //position of torso
    float[] tangent; //torso facing tangent
    float[] biNormal;
    float[] normal;

    float t;
    int pointIndex;
    int loopIndex;
    float[][] Matrix;

    public Torso(){
        this.torsoPosition = new float[3];
        this.tangent = new float[3];
        this.normal = new float[3];
        this.biNormal = new float[3];
        this.t = 0.0f;
        this.pointIndex = 0;
        this.loopIndex = 0;
        this.Matrix = new float[4][4];
    }
    public void setTorsoPosition(float[] torsoPosition){
        this.torsoPosition = torsoPosition;
    }

    public void setTangent(float[] tangent) {
        this.tangent = tangent;
        Function.vectorNormal(this.tangent);
    }

    public void setNormal(float[] normal) {
        this.normal = normal;
        Function.vectorNormal(this.normal);
    }

    public void setBiNormal(float[] biNormal) {
        this.biNormal = biNormal;
        Function.vectorNormal(this.biNormal);
    }
    public void setT(float t){
        this.t = t;
    }
    public void setPointIndex(int pointIndex){
        this.pointIndex = pointIndex;
    }

    public void torsoInterpolate()
    {
        //using Catmull-Rom spline
        float[][] MS = GlobalVariables.CRSplineM;

        // Set up T matrix
        float[][] T = {{t*t*t,t*t,t,1}};

        // Set up Tangent T matrix
        float[][] TangentTMatrix = { {3*t*t, 2*t, 1, 0} };

        float[][] G = new float[4][3];

        for (int i = 0; i <G.length ; i++) {
            //4 points
            G[i] = GlobalVariables.position[(pointIndex+i)%GlobalVariables.pointNum];
        }

        setTorsoPosition(Function.blend(T, MS,G));
        setTangent(Function.blend(TangentTMatrix, MS,G));

        if (pointIndex == 0 && loopIndex == 0) // loop starts from the beginning
        {
            float[] TempVector = { 1, 0, 0 }; //Pick an arbitrary vector V
            Function.vectorNormal(TempVector);
            setNormal(Function.vectorCross(tangent,TempVector));
            setBiNormal(Function.vectorCross(normal,tangent));
            loopIndex++;
        }
        else // loop does not start from the beginning
        {
            setNormal(Function.vectorCross(tangent,biNormal));
            setBiNormal(Function.vectorCross(normal,tangent));
        }
    }

    public float[][] toMatrix(){
        this.torsoInterpolate();
        float px = torsoPosition[0];
        float py = torsoPosition[1];
        float pz = torsoPosition[2];

//        float[][] T = {
//                {1,0,0,px},
//                {0,1,0,py},
//                {0,0,1,pz},
//                {0,0,0,1}
//        };
        float[][] M = {
                {tangent[0],tangent[1],tangent[2],px},
                {normal[0],normal[1],normal[2],py},
                {biNormal[0],biNormal[1],biNormal[2],pz},
                {0,0,0,1}
        };

        this.Matrix = M;
        return M;
    }

    //convert 2D matrix to 1D
    public float[] toOneDMatrix(){
        this.toMatrix();
        return Function.convertMatrix(this.Matrix);
    }
}
