import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.fixedfunc.GLLightingFunc;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

public class MyEventListener implements GLEventListener {
    private GLU glu = new GLU();
    private GLUT glut = new GLUT();
    private Balls balls = new Balls(GlobalVariables.number);
    private float t = 0.0f;
    private int pointIndex = 0;
    private void Enable(GLAutoDrawable drawable){
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glClearDepth(1.0);

        gl.glEnable( GL2.GL_DEPTH_TEST );
        //gl.glDepthFunc(GL2.GL_LESS); // default
        gl.glShadeModel(GL2.GL_SMOOTH);
        //gl.glEnable(GL2.GL_TEXTURE_2D);
        //gl.glEnable(GL2.GL_CULL_FACE);
        //gl.glEnable(GLLightingFunc.GL_LIGHTING);
        //gl.glEnable(GLLightingFunc.GL_LIGHT0);
        //gl.glEnable(GL2.GL_AUTO_NORMAL);
        //gl.glEnable(GLLightingFunc.GL_NORMALIZE);
        //gl.glFrontFace(GL2.GL_CW);
        //gl.glCullFace(GL2.GL_BACK); // default
        //gl.glMaterialf(GL2.GL_FRONT, GLLightingFunc.GL_SHININESS, 64.0f);
        //gl.glTexParameterf(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);


    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        Enable(drawable);


        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();

        glu.gluLookAt(30, 30, -30,0.0, 0.0, 0.0,0.0, 1.0, 0.0);


        //render the ground
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3f(1.0f,0f,0f);
        for (float x = -30; x <= 30; x += 5.0f)
        {
            gl.glVertex3f(x, 0, -30); gl.glVertex3f(x, 0, 30);
        }
        for (float z = -30; z <= 30; z += 5.0f)
        {
            gl.glVertex3f(-30, 0, z); gl.glVertex3f(30, 0, z);
        }

        //render the back wall
        gl.glColor3f(0f,1.0f,0f);
        for (float y = 0; y <= 100; y += 0.25f)
        {
            gl.glVertex3f(-30,y,-30); gl.glVertex3f(30, y, -30);
        }
        for (float x = -30; x <= 30; x += 5.0f)
        {
            gl.glVertex3f(x, 0, -30); gl.glVertex3f(x, 150, -30);
        }

        //render the front wall
        gl.glColor3f(1f,1.0f,1f);
        for (float y = 0; y <= 100; y += 5f)
        {
            gl.glVertex3f(-30,y,30); gl.glVertex3f(30, y, 30);
        }
        for (float x = -30; x <= 30; x += 5.0f)
        {
            gl.glVertex3f(x, 0, 30); gl.glVertex3f(x, 150, 30);
        }

        //render the left wall
        gl.glColor3f(0.0f,0f,1f);

        for (float y = 0; y <= 100; y += 5f)
        {
            gl.glVertex3f(-30,y,-30); gl.glVertex3f(-30, y, 30);
        }
        for (float z = -30; z <= 30; z += 5.0f)
        {
            gl.glVertex3f(-30, 0, z); gl.glVertex3f(-30, 100, z);
        }

        //render the right wall
        for (float y = 0; y <= 100; y += 5f)
        {
            gl.glVertex3f(30,y,-30); gl.glVertex3f(30, y, 30);
        }
        for (float z = -30; z <= 30; z += 5.0f)
        {
            gl.glVertex3f(30, 0, z); gl.glVertex3f(30, 100, z);
        }

        gl.glEnd();



        gl.glEnable(GLLightingFunc.GL_LIGHTING);
        gl.glEnable(GLLightingFunc.GL_LIGHT0);
        // light source attributes
        /*
        float[] LightAmbient = { 0.5f, 0f, 0.f, 1.0f };
        float[] LightDiffuse = { 0.4f, 0.4f, 0.4f, 1.0f };
        float[] LightSpecular = { 0.5f, 0.5f, 0.5f, 1.0f };
        float[] LightPosition = { 5.0f, 5.0f, 5.0f, 1.0f };


        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, LightAmbient,0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, LightDiffuse,0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, LightSpecular,0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, LightPosition,0);

        */
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


        //render the balls
        for (int i = 0; i < balls.number; i++) {
            gl.glPushMatrix();
            balls.ballAnimator(i);
            gl.glMultMatrixf(balls.balls[i].toOneDMatrix(),0);
            glut.glutSolidSphere(1, 50, 50);
            gl.glPopMatrix();
        }


        gl.glFlush();
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
        glu.gluPerspective(65.0, (float)width/(float)height, 1.0, 100.0);

    }
}