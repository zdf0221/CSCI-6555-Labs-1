import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.fixedfunc.GLLightingFunc;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

public class MyEventListener implements GLEventListener {
    private GLU glu = new GLU();
    private GLUT glut = new GLUT();
    private float t = 0.0f;
    private int pointIndex = 0;
    private void Enable(GLAutoDrawable drawable){
        final GL2 gl = drawable.getGL().getGL2();
        gl.glEnable( GL2.GL_DEPTH_TEST );
        gl.glDepthFunc(GL2.GL_LESS); // default
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.glEnable(GL2.GL_CULL_FACE);
        gl.glEnable(GLLightingFunc.GL_LIGHTING);
        gl.glEnable(GLLightingFunc.GL_LIGHT0);
        gl.glEnable(GL2.GL_AUTO_NORMAL);
        gl.glEnable(GLLightingFunc.GL_NORMALIZE);
        gl.glFrontFace(GL2.GL_CW);
        gl.glCullFace(GL2.GL_BACK); // default
        gl.glMaterialf(GL2.GL_FRONT, GLLightingFunc.GL_SHININESS, 64.0f);
        gl.glTexParameterf(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        Enable(drawable);

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();

        //EulerAngle m = Function.eInterpolation("B Spline",t,pointIndex); //Euler angle B Spline
        //EulerAngle m = Function.eInterpolation("Catmull-Rom Spline",t,pointIndex); //Euler angle Catmull Rom Spline
        //Quaternion m = Function.qInterpolation("B Spline",t,pointIndex);//Quaternion B Spline
        Quaternion m = Function.qInterpolation("Catmull-Rom Spline",t,pointIndex);//Quaternion Catmull Rom Spline
        if(m!=null) {
            gl.glLoadMatrixf(m.toOneDMatrix(), 0);
        }
        glut.glutSolidTeapot(1.0f);//render

        gl.glFlush();

        //timer
        t = t + 0.01f;
        if (t >= 1.0f)
        {
            t = 0.0f;
            if (pointIndex < GlobalVariables.pointNum - 4)
            {
                pointIndex++;
            }
            else
            {
                pointIndex = 0;
            }
        }

    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
        //method body
    }

    @Override
    public void init( GLAutoDrawable drawable ) {
        final GL2 gl = drawable.getGL().getGL2();

    }
    @Override
    public void reshape( GLAutoDrawable drawable, int x, int y, int width, int height ) {

        final GL2 gl = drawable.getGL().getGL2();

        // viewport
        gl.glViewport( 0, 0, width, height );

        // projection matrix
        gl.glMatrixMode( GL2.GL_PROJECTION );
        gl.glLoadIdentity();
        glu.gluPerspective(45.0, (float)width/(float)height, 1.0, 2000.0);

    }
}