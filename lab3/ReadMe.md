Name: Chuchu Jin
====
Email: mayflywing@gwu.edu
-----
**Language: Java**
**External Libraries: JOGL**
# Solution
Create some balls with initial positions and velocities, set up acceleration due to gravity, energy loss due to collision.
In each iteration, detect the collision, if the collision happens, change the velocity of that ball.
# How to Use:
> Change the initial state in "**GlobalVariables.java"**
```java
...
    public static final float[][] position ={};
	public static final float[][] velocity = {};
...
```

> Change the acceleration and energy loss in "**Balls.java"**
*GlobalVariables.java*
```java
float energyLoss = 0.8f;
float[] acceleration = { 0, -2.0f, 0 };
```
>Run **"PhysicsBased.java"**
