import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

public class NotificationPopup extends JFrame
{
    NotificationPopup( final String message, final String header ) throws HeadlessException
    {
        final JFrame frame = new JFrame();
        frame.setSize( 300, 125 );
        frame.setUndecorated( true );
        frame.setAlwaysOnTop( true );
        frame.setLayout( new GridBagLayout() );
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets( 5, 5, 5, 5 );
        constraints.fill = GridBagConstraints.BOTH;
        final JLabel headingLabel = new JLabel( header );
//        headingLabel.setIcon( headingIcon ); // --- use image icon you want to be as heading image.
        headingLabel.setOpaque( false );
//        frame.add( headingLabel, constraints );

        constraints.gridx++;
        constraints.weightx = 0f;
        constraints.weighty = 0f;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.NORTH;
        final JButton closeButton = new JButton( new AbstractAction( "x" )
        {
            @Override
            public void actionPerformed( final ActionEvent e )
            {
                frame.dispose();
            }
        } );
        closeButton.setMargin( new Insets( 1, 4, 1, 4 ) );
        closeButton.setFocusable( false );
        frame.add( closeButton, constraints );
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets( 5, 5, 5, 5 );
        constraints.fill = GridBagConstraints.BOTH;
        final JLabel messageLabel = new JLabel( "<HtMl>" + message );
        frame.add( messageLabel, constraints );
        frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        frame.setVisible( true );

        final Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets( frame.getGraphicsConfiguration() );// height of the task bar
        frame.setLocation( scrSize.width - frame.getWidth(), scrSize.height - toolHeight.bottom - frame.getHeight() );
        new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep( 5000 );
                    frame.dispose();
                }
                catch ( final InterruptedException e )
                {
                    e.printStackTrace();
                }
            }

            ;
        }.start();
    }
}
