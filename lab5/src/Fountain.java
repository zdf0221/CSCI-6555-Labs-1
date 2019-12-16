import java.util.ArrayList;
import java.util.LinkedList;

public class Fountain {
    final float pi = 3.14f;
    float timeStamp;
    float timeIncrease;
    float energyLoss;
    LinkedList<Particle> particles;
    float[] acceleration;
    int R = 2; //the radius of fountain pipe
    float VyMax = 20f; // the max speed of water
    int a = 30; //fountain spray angle


    public Fountain(){
        this.particles = new LinkedList<>();
        this.acceleration = new float[]{0,-9.81f,0};
        this.timeStamp = 0;
        this.timeIncrease = 0.3f;
        this.energyLoss = 0.1f;
    }

    public void addParticle(){
        float Px = rand(-R,R);
        float Pz = rand(-R,R);
        while(Px*Px+Pz+Pz>R*R){
            Px = rand(-R,R);
            Pz = rand(-R,R);
        }

        float r = (float)(Math.sqrt(Px*Px+Pz+Pz));
        float Vy = VyMax*(1-(r/R)*(r/R));
        float R1 = (float)Math.random();
        float R2 = (float)Math.random();
        float Ra = rand(-a/2,a/2);
        float Vx = (float)(Math.sin(R1*Ra)*Math.cos(R2*2*pi));
        float Vz = (float)(Math.sin(R1*Ra)*Math.sin(R2*2*pi));

        Particle p = new Particle(new float[]{Px,0f,Pz},new float[]{Vx,Vy,Vz},timeStamp);
        particles.add(p);
    }

    public void addSomeParticles(){
        int max = 100; int min = 50;
        int num = (int)(Math.random()*(max-min)+min);
        while(num>0){
            addParticle();
            num--;
        }
        timeStamp+=timeIncrease;
    }
    public void deleteParticle(){
        while(timeStamp-particles.peek().timeStamp>20){
            particles.pop();
        }
    }
    public void groundCollision(){
        for (Particle p:particles) {
            if(p.position[1]<1.5f) {
                // velocity in y axis changes direction and decrease by e,
                // velocity in x,z axises remains the same
                p.velocity[1] = -energyLoss * p.velocity[1];
            }
        }
    }

    public void particlesMove(){
        for (Particle p:particles) {
            float[] newVelocity = new float[3];
            float[] newPosition = new float[3];
            for (int i = 0; i<3; i++){
                newVelocity[i] = p.velocity[i]+ acceleration[i] * timeIncrease;
                newPosition[i] = p.position[i]+p.velocity[i]* timeIncrease;
            }
            p.setVelocity(newVelocity);
            p.setPosition(newPosition);
        }
    }

    public void fountainAnimator(){
        addSomeParticles();
        deleteParticle();
        particlesMove();
        groundCollision();
    }

    public float rand(int min,int max){
        return (float)(Math.random()*(max-min)+min);
    }
}
