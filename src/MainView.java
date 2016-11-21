import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class MainView
{
    private Timer _takeBreakTimer;

    private final JFrame _mainFrame = new JFrame();

    private final JButton _startStopButton;

    private final PostureMessages _postureMessages;

    private static final long SECONDS_TIMES_MILLI = 60 * 1000;

    public MainView( final PostureMessages postureMessages )
    {
        _postureMessages = postureMessages;
        _mainFrame.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
        _mainFrame.setTitle( postureMessages.getTitle() );

        _startStopButton = new JButton( postureMessages.getStart() );
        _startStopButton.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( final ActionEvent e )
            {
                toggleTimer();
            }
        } );
        _startStopButton.setVerticalTextPosition( AbstractButton.BOTTOM );
        _startStopButton.setHorizontalTextPosition( AbstractButton.CENTER );
        _mainFrame.add( _startStopButton );
        _mainFrame.setBounds( 2300, 0, 200, 200 );
//        _mainFrame.pack();
        _mainFrame.setVisible( true );
    }

    private void toggleTimer()
    {
        if ( _startStopButton.getText().equals( _postureMessages.getStop() ) )
        {
            _takeBreakTimer.cancel();
            _startStopButton.setText( _postureMessages.getStart() );
        }
        else
        {
            _takeBreakTimer = new Timer();
            _takeBreakTimer.schedule( new TimerTask()
            {
                @Override
                public void run()
                {
                    JOptionPane.showMessageDialog( null, _postureMessages.getTakeBreak(), _postureMessages.getTakeBreakTitle(), JOptionPane.INFORMATION_MESSAGE );
                    _startStopButton.setText( _postureMessages.getStart() );
                }
            }, 25 * SECONDS_TIMES_MILLI );
            _startStopButton.setText( _postureMessages.getStop() );
        }
    }
}
