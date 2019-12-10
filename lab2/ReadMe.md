Name: Chuchu Jin
====
Email: mayflywing@gwu.edu
-----
**Language: Java**
**External Libraries: JOGL**
# Solution
One torso and two legs.
Torso move through the trajectory and the interpolation using Catmull-Rom spline, legs link to torso and the angle change with time t.
# How to Use:
>**Change the trajectory in GlobalVariables.java**

*GlobalVariables.java*
```java
    //position x,y,z in world Cartesian System
    public static final float[][]position = {
            { 8, 0, -20 },  //point 1
            { -8, 0, -20 }, //point 2
            { -5, 0, -10 }, //point 3
            { 5, 0, -10 },	//point 4
            { 3, 0, -5 },	//point 5
            { -3,0,-5},		//point 6
            { 1, 0, -3 }  //point 7
    };
...
}
```