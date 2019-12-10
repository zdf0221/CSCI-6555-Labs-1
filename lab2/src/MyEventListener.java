import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.fixedfunc.GLLightingFunc;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

public class MyEventListener implements GLEventListener {
    private GLU glu = new GLU();
    private GLUT glut = new GLUT();
    private Torso torso = new Torso();
    private LeftLeg leftLeg = new LeftLeg(torso);
    private RightLeg rightLeg = new RightLeg(torso);

    private float t = 0.0f;
    private int pointIndex = 0;
    private void Enable(GLAutoDrawable drawable){
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
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

        // light source attributes
        float[] LightAmbient = { 1f, 0f, 0f, 1.0f };
        float[] LightDiffuse = { 1f, 0f, 0f, 1.0f };
        float[] LightSpecular = { 0.0f, 0.0f, 0.0f, 1.0f };
        float[] LightPosition = { 5.0f, 5.0f, 5.0f, 1.0f };


        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, LightAmbient,0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, LightDiffuse,0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, LightSpecular,0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, LightPosition,0);

        // surface material attributes
        float[] material_Ka = {1.0f, 1.0f, 0.0f, 1.0f };
        float[] material_Kd = { 0.43f, 0.47f, 0.54f, 1.0f };
        float[] material_Ks = { 0.33f, 0.33f, 0.52f, 1.0f };
        float[] material_Ke = { 0.1f, 0.0f, 0.1f, 1.0f };
        float material_Se = 10;

        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, material_Ka,0);
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, material_Kd,0);
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, material_Ks,0);
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_EMISSION, material_Ke,0);
        gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, material_Se);

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        Enable(drawable);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();

        torso.setT(t);
        torso.setPointIndex(pointIndex);
        gl.glLoadMatrixf(torso.toOneDMatrix(), 0);
        gl.glScalef(0.5f,2.0f,1f);
        glut.glutSolidCube(1.0f);//render

        leftLeg.setT(t);
        gl.glLoadMatrixf(leftLeg.toOneDMatrix(), 0);
        gl.glScalef(0.3f,2.0f,0.3f);
        glut.glutSolidCube(1.0f);//render

        rightLeg.setT(t);
        gl.glLoadMatrixf(rightLeg.toOneDMatrix(), 0);
        gl.glScalef(0.3f,2.0f,0.3f);
        glut.glutSolidCube(1.0f);//render


        gl.glFlush();

        //timer
        t = t + 0.005f;
        if (t >= 1.0f)
        {
            t = 0.0f;
            if (pointIndex < GlobalVariables.pointNum - 1)
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
        glu.gluPerspective(70.0, (float)width/(float)height, 1.0, 30.0);
        //glu.gluPerspective(45.0, (float)width/(float)height, 1.0, 2000.0);
    }
}