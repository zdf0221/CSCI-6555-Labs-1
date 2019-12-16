import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

public class MyEventListener implements GLEventListener {
    private GLU glu = new GLU();
    private GLUT glut = new GLUT();
    private Fountain fountain = new Fountain();

    private void Enable(GLAutoDrawable drawable){
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glClearDepth(1.0);

        gl.glEnable( GL2.GL_DEPTH_TEST );
        gl.glDepthFunc(GL2.GL_LESS); // default
        gl.glShadeModel(GL2.GL_SMOOTH);
//        gl.glEnable(GL2.GL_TEXTURE_2D);
//        gl.glEnable(GL2.GL_CULL_FACE);
//        gl.glEnable(GLLightingFunc.GL_LIGHTING);
//        gl.glEnable(GLLightingFunc.GL_LIGHT0);
//        gl.glEnable(GL2.GL_AUTO_NORMAL);
//        gl.glEnable(GLLightingFunc.GL_NORMALIZE);
//        gl.glFrontFace(GL2.GL_CW);
//        gl.glCullFace(GL2.GL_BACK); // default
//        gl.glMaterialf(GL2.GL_FRONT, GLLightingFunc.GL_SHININESS, 64.0f);
//        gl.glTexParameterf(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        Enable(drawable);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        glu.gluLookAt(0f, 30.0f, -30f,
                0.0, 0.0, 0.0,
                0.0, 1.0, 0.0);

        //render the ground
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3f(0.8f,0.8f,1.0f);
        for (float x = -100; x <= 100; x += 5.0f)
        {
            gl.glVertex3f(x, 0, -100); gl.glVertex3f(x, 0, 100);
        }
        for (float z = -100; z <= 100; z += 5.0f)
        {
            gl.glVertex3f(-100, 0, z); gl.glVertex3f(100, 0, z);
        }
        gl.glEnd();



        fountain.fountainAnimator();
        //render the particles
        gl.glColor3f(1.0f,1.0f,1.0f);

        for (int i = 0; i < fountain.particles.size(); i++) {
            gl.glPushMatrix();
            gl.glMultMatrixf(fountain.particles.get(i).toOneDMatrix(),0);
            glut.glutSolidSphere(0.5, 3, 3);
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
        glu.gluPerspective(100.0, (float)width/(float)height, 1.0f, 500.0);

    }
}