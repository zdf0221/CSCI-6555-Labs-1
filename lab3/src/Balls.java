public class Balls {
    int number;
    float[][][] Matrices;
    Ball[] balls;

    //coefficient of collision: velocity after collision would reduce to 0.8 of original volecity
    float energyLoss = 0.8f;
    // time increase by 0.03
    float timeIncrease = 0.03f;
    // gravity acceleration simulation: velocity along -y axis would in crease 2.0 units in every 0.03 time increasement
    float[] acceleration = { 0, -2.0f, 0 };

    public Balls(int number){
        this.number = number;
        balls = new Ball[number];
        for (int i = 0; i < number ; i++) {
            balls[i] = new Ball(GlobalVariables.position[i],GlobalVariables.velocity[i]);
        }
    }

    //detect if the ball reach other balls
    public void ballCollision(int index)
    {
        Ball current = balls[index];
        // detect collision between current ball and all the other balls
        for (int i = index + 1; i < number; i++)
        {
            Ball next = balls[i];
            if (current.collision(next))
            {
                // calculate the x_axis for current ball
                float[] x_axis = new float[3];
                for (int j = 0; j < 3; j++)
                {
                    x_axis[j] = next.position[j] - current.position[j];
                }
                Function.vectorNormal(x_axis);

                //collision
                float[] d1 = new float[3];
                float[] velocityChange1 = new float[3];
                float u1 = Function.vectorDot(x_axis,current.velocity);

                for (int j = 0; j < 3; j++)
                {
                    d1[j] = u1*x_axis[j];
                    velocityChange1[j] = current.velocity[j] - d1[j];
                }

                // calculate the x_axis for the other ball
                for (int j = 0; j < 3; j++)
                {
                    x_axis[j] = current.position[j] - next.position[j];
                }
                Function.vectorNormal(x_axis);

                // collision
                float[] d2 = new float[3];
                float[] velocityChange2 = new float[3];
                float u2 = Function.vectorDot(x_axis,next.velocity);
                for (int j = 0; j < 3; j++)
                {
                    d2[j] = u2*x_axis[j];
                    velocityChange2[j] = next.velocity[j] - d2[j];
                }

                // calculate the new velocity
                float[] newVelocity1 = new float[3];
                float[] newVelocity2 = new float[3];

                for (int j = 0; j < 3; j++)
                {
                    newVelocity1[j] = (d1[j] + d2[j] - (d1[j] - d2[j]))*0.5f + velocityChange1[j];
                    newVelocity2[j] = (d1[j] + d2[j] - (d2[j] - d1[j]))*0.5f + velocityChange2[j];
                }
                current.setVelocity(newVelocity1);
                next.setVelocity(newVelocity2);
        }
        }
    }
    // detect if the ball reach the ground
    public void groundCollision(int index)
    {
        Ball current = balls[index];
        if (current.position[1]<0.5f) // Collision
        {
            // velocity in y axis changes direction and decrease by e,
            // velocity in x,z axises remains the same
            current.velocity[1] = -energyLoss*current.velocity[1];
        }
    }

    // detect if the ball reach the wall
    public void backWallCollision(int index)
    {
        Ball current = balls[index];
        if (current.position[2]<-30f||current.position[2]>30f) // Collision
        {
            // velocity in z axis changes direction and decrease by e,
            // velocity in x,y axises remains the same
            current.velocity[2] = -energyLoss*current.velocity[2];
        }
    }
    public void sideWallCollision(int index)
    {
        Ball current = balls[index];
        if (current.position[0]<-30f||current.position[0]>30f) // Collision
        {
            // velocity in x axis changes direction and decrease by e,
            // velocity in y,z axises remains the same
            current.velocity[0] = -energyLoss*current.velocity[0];
        }
    }



    public void ballAnimator(int index){
        Ball current = balls[index];
        groundCollision(index);
        backWallCollision(index);
        sideWallCollision(index);
        ballCollision(index);

        float[] newVelocity = new float[3];
        float[] newPosition = new float[3];
        for (int i = 0; i<3; i++){
            newVelocity[i] = current.velocity[i]+ acceleration[i] * timeIncrease;
            newPosition[i] = current.position[i]+current.velocity[i]* timeIncrease;
        }
        current.setVelocity(newVelocity);
        current.setPosition(newPosition);
    }

}
