import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class MainView
{
    private final PostureMessages _postureMessages;

    private final javax.swing.Timer _labelUpdateSwingTimer;

    private final JButton _startStopButton;

    private final JFormattedTextField _jFormattedTextField;

    private final TrafficLightPanel _trafficLightPanel;

    private final JLabel _timeLeftLabel = new JLabel();

    private static final int SECONDS_TIMES_MILLI = 60 * 1000;

    private static final long DEFAULT_INTERVAL = 25;

    private long _endTime;

    public MainView( final PostureMessages postureMessages )
    {
        _postureMessages = postureMessages;
        final JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
        mainFrame.setTitle( postureMessages.getTitle() );

        final NumberFormatter numberFormatter = new NumberFormatter( NumberFormat.getIntegerInstance() );
        numberFormatter.setValueClass( Long.class );
        numberFormatter.setAllowsInvalid( false );
        numberFormatter.setMinimum( 0L );

        _jFormattedTextField = new JFormattedTextField( numberFormatter );

        _jFormattedTextField.setValue( DEFAULT_INTERVAL );

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

        _trafficLightPanel = new TrafficLightPanel();

        _labelUpdateSwingTimer = new javax.swing.Timer( 1000, new ActionListener()
        {
            @Override
            public void actionPerformed( final ActionEvent e )
            {
                final long millis = _endTime - e.getWhen();
                if ( millis > 0 )
                {
                    _timeLeftLabel.setText( postureMessages.getTimeLeft() + String.format( "%02d min, %02d sec",
                                                                                           TimeUnit.MILLISECONDS.toMinutes( millis ),
                                                                                           TimeUnit.MILLISECONDS.toSeconds( millis ) -
                                                                                                   TimeUnit.MINUTES.toSeconds( TimeUnit.MILLISECONDS.toMinutes( millis ) )
                    ) );
                }
                else
                {
                    updateUI( false );
                    JOptionPane.showMessageDialog(
                            ( ( Supplier<JDialog> ) () ->
                            {
                                final JDialog dialog = new JDialog();
                                dialog.setAlwaysOnTop( true );
                                return dialog;
                            } ).get()
                            , _postureMessages.getTakeBreak(), _postureMessages.getTakeBreakTitle(), JOptionPane.INFORMATION_MESSAGE );
                }
            }
        } );
        _labelUpdateSwingTimer.setInitialDelay( 0 );

        _timeLeftLabel.setText( postureMessages.getTimeLeft() );

        final JPanel firstRow = new JPanel();
        firstRow.add( _jFormattedTextField );
        firstRow.add( _startStopButton );
        final JPanel secondRow = new JPanel();
        secondRow.add( _trafficLightPanel );
        secondRow.add( _timeLeftLabel );
        final JPanel wholePanel = new JPanel();
        wholePanel.setLayout( new BoxLayout( wholePanel, BoxLayout.Y_AXIS ) );
        wholePanel.add( firstRow );
        wholePanel.add( secondRow );
        mainFrame.add( wholePanel );
        mainFrame.setBounds( 2300, 0, 200, 200 );
        mainFrame.setMinimumSize( new Dimension( 200, 0 ) );
        mainFrame.pack();
        mainFrame.setVisible( true );
    }

    private void toggleTimer()
    {
        if ( _startStopButton.getText().equals( _postureMessages.getStop() ) )
        {
            updateUI( false );
        }
        else
        {
            _endTime = System.currentTimeMillis() + ( long ) _jFormattedTextField.getValue() * SECONDS_TIMES_MILLI;
            updateUI( true );
        }
    }

    private void updateUI( final boolean running )
    {
        if ( running )
        {
            _labelUpdateSwingTimer.restart();
        }
        else
        {
            _labelUpdateSwingTimer.stop();
        }
        _startStopButton.setText( running ? _postureMessages.getStop() : _postureMessages.getStart() );
        _trafficLightPanel.setRunning( running );
    }
}
