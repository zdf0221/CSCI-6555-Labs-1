Name: Chuchu Jin
====
Email: mayflywing@gwu.edu
-----
**Language: Java**
**External Libraries: JOGL**
# Solution
The fountain sprays a random number of particles at every time stamp. Each particle has a random initial position and speed. When the particle exists for more than a certain time, it will die out, and the particle will bounce slightly when it hits the ground.
Use Poiseuille flow equation to simulate the fountain.
## To generate one particle:
### Step 1:
Randomly pick a position within the circumference of the fountain pipe.
### Step 2:
Initilize Vy with $$V_y = V_{max} \times [1-\frac{r}{R}] $$
while Vmax is a max speed that we manully set, r is the distance from this particle to the center of the fountain, R is the radius of the fountain pipe.
### Step 3:
Initilize Vx, Vz with
$$V_x=sin(R_1 \times Rand(\frac \alpha 2))\times cos(R_2 \times 2 \times Pi) $$
$$V_z=sin(R_1 \times Rand(\frac \alpha 2))\times sin(R_2 \times 2 \times Pi) $$
while R1 and R2 are random numbers between 0 and 1, they are different.
And $$\alpha$$ is thefountain spray angle.
Rand ($$\frac \alpha 2$$) means to generate random angle between $$-\frac \alpha 2$$ to $$\frac \alpha 2$$.
## To genetate the fountain
At every time stamp,
1. Generate a random number of particles.
2. Change the speed and position of existing particles
3. Bounces slightly if a paricle touches the ground
4. Particles die if they exist for a certain period of time

# How to Use:
> Change the fountain parameters in **"Fountain.java"**

```java
    int R = 2; //the radius of fountain pipe
    float VyMax = 20f; // the max speed of water
    int a = 30; //fountain spray angle
```

> Run **"ParticleSystem.java"**
