Name: Chuchu Jin
====
Email: mayflywing@gwu.edu
-----
**Language: Java**
**External Libraries: JOGL**
# Solution
Create one leader boid following the trajectory.
Create some ohter boids following the leader boid and using other three rules to determine behaviors: collision avoidance, velocity matching, flock centering.
Using weighted sum of those rules to get the final velocity.
# How to Use:
> Change the trajectory in **"GlobalVariables.java"**
```java
public static final float[][] points = {
        { 90, 0, 45, -3, 7.0f, -8f },
        { 70, 20, 65, -8, -2, -9f },
        { 50, 40, 85, -5, 6, -10 },
        { 30, 60, 105, 5, -8, -10 },
        { 50, 40, 85, 3, -10, -5 },
        { 70, 20, 65, -3, -14, -5 },
        { 90, 0, 45, 1, -18, -3 }
};
```

> Change the initial position of boids in **"GlobalVariables.java"**
*GlobalVariables.java*
```java
public static final float[][] position = {
        { -3.0f, 7.0f, -5.6f },
        { 9.0f, 8.5f, -4.0f },
        { 4.0f, 7.2f, -5.7f },
        { -4.5f, 6.8f, -5.8f },
        { 3.0f, 8.6f, -5.0f },
        { 5.0f, 9.8f, -4.9f },
        { -4.0f, 9.0f, -4.9f },
        { 4.0f, 12.0f, -4.5f },
        { 0.0f, 8.2f, -5.0f },
        { 1.0f, 7.6f, -4.5f },
        { -2.0f, 8.0f, -4.3f },
        { 10.0f, 9.5f, -3.9f },
        { 5.0f, 8.2f, -4.2f },
        { -3.5f, 7.8f, -4.1f },
        { 4.0f, 9.6f, -4.9f },
        { 6.0f, 10.8f, -4.8f },
        { -3.0f, 10.0f, -4.8f },
        { 5.0f, 13.0f, -4.4f },
        { 1.0f, 9.2f, -4.9f },
        { 2.0f, 8.6f, -4.4f } };
```

> Run **"Behavioral.java"**
