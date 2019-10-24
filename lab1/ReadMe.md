Name: Chuchu Jin
====
Email: mayflywing@gwu.edu
-----
**Language: Java**
**External Libraries: JOGL**
# How to Use:
>*Please Change the comment symbol below to choose **EulerAngle/Quaternion** and **B Spline/Catmull Rom Spline***

*MyEventListener.java*
```java
public void display(GLAutoDrawable drawable) {
...
        //EulerAngle m = Function.eInterpolation("B Spline",t,pointIndex); //Euler angle B Spline
        //EulerAngle m = Function.eInterpolation("Catmull-Rom Spline",t,pointIndex); //Euler angle Catmull Rom Spline
        //Quaternion m = Function.qInterpolation("B Spline",t,pointIndex);//Quaternion B Spline
        Quaternion m = Function.qInterpolation("Catmull-Rom Spline",t,pointIndex);//Quaternion Catmull Rom Spline
        if(m!=null) {
            gl.glLoadMatrixf(m.toOneDMatrix(), 0);
        }
        glut.glutSolidTeapot(1.0f);//render
...
}
```



