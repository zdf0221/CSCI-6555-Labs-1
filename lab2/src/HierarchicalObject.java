import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;

public class HierarchicalObject {
    public static void main( String[] args ) {
        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get( GLProfile.GL2 );
        GLCapabilities capabilities = new GLCapabilities( profile );
        // The canvas
        final GLCanvas glcanvas = new GLCanvas( capabilities );
        MyEventListener myEventListener = new MyEventListener();
        glcanvas.addGLEventListener( myEventListener );
        glcanvas.setSize( GlobalVariables.WIDTH, GlobalVariables.HEIGHT );
        //creating frame

        final JFrame frame = new JFrame ( "Lab2 - Hierarchical Object" );
        frame.setLocationRelativeTo(null);//show the frame in the center of the screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//when close the frame, exit the program

        //adding canvas to it
        frame.getContentPane().add( glcanvas, BorderLayout.CENTER);
        frame.setSize( frame.getContentPane().getPreferredSize() );

        frame.setVisible( true );
        //Instantiating and Initiating Animator
        final FPSAnimator animator = new FPSAnimator( glcanvas, 60,true );
        animator.start();
    }//end of main
}
