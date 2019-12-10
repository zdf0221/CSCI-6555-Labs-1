public class Boids {
    int number;
    float[][][] Matrices;
    Boid[] boids;
    Boid leaderBoid;


    public Boids(int number){
        this.number = number;
        boids = new Boid[number];
        for (int i = 0; i < number ; i++) {
            boids[i] = new Boid(GlobalVariables.position[i],new float[3]);
        }
        this.leaderBoid = new Boid();
    }

    //follow the leader
    public void r1(int index)
    {
        Boid current = boids[index];
        for (int i = 0; i < 3; i++)
        {
            current.r1[i] = (leaderBoid.position[i]-current.position[i]) / 1000;
        }
    }

    // Collision avoidance
    void r2(int index)
    {
        Boid current = boids[index];
        int count = 0;
        for (int i = 0; i < number; i++)
        {
            if (i != index)
            {
                Boid next = boids[i];
                if (current.distance(next)< 4)
                {
                    count++;
                    for (int j = 0; j < 3; j++)
                    {
                        current.r2[j] += -current.distance(next) / 1500;
                    }
                }
            }
        }
        for (int j = 0; j < 3; j++)
        {
            current.r2[j] = current.r2[j]/count;
        }
    }

    //velocity matching
    void r3(int index)
    {
        Boid current = boids[index];
        float[] totalVelocity = new float[3];
        float[] averageVelocity = new float[3];
        for (int i = 0; i < number; i++)
        {
            totalVelocity[0] += current.velocity[0];
            totalVelocity[1] += current.velocity[0];
            totalVelocity[2] += current.velocity[0];

            averageVelocity[0] = (totalVelocity[0] - current.velocity[0]) / (number - 1);
            averageVelocity[1] = (totalVelocity[1] - current.velocity[1]) / (number - 1);
            averageVelocity[2] = (totalVelocity[2] - current.velocity[2]) / (number - 1);
        }

        for (int i = 0; i < 3; i++)
        {
            current.r3[i] = (averageVelocity[i] - current.velocity[i]) / 2000;
        }
    }
    //Flock Centering
    void r4(int index)
    {
        Boid current = boids[index];
        float[] totalPosition = new float[3];
        float[] centerPosition = new float[3];

        //get the average centerPosition of boids
        for (int i = 0; i < number; i++)
        {
            totalPosition[0] += current.position[0];
            totalPosition[1] += current.position[1];
            totalPosition[2] += current.position[2];

            centerPosition[0] = (totalPosition[0] - current.position[0]) / (number - 1);
            centerPosition[1] = (totalPosition[1] - current.position[1]) / (number - 1);
            centerPosition[2] = (totalPosition[2] - current.position[2]) / (number - 1);
        }

        for (int i = 0; i < 3; i++)
        {
            current.r4[i] = (centerPosition[i] - current.position[i]) / 2500;
        }
    }



    public void boidAnimator(int index){
        Boid current = boids[index];
        r1(index);
        r2(index);
        r3(index);
        r4(index);
        float[] newVelocity = new float[3];
        float[] newPosition = new float[3];
        for (int j = 0; j < 3; j++)
        {
            newVelocity[j] = current.velocity[j] + current.r1[j] + current.r2[j] + current.r3[j] + current.r4[j];
            newPosition[j] = current.position[j] + newVelocity[j]*0.15f;
        }
        current.setVelocity(newVelocity);
        current.setPosition(newPosition);
    }

}
