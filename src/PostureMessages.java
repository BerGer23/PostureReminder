
public class PostureMessages
{
    private String _takeBreak;

    private String _takeBreakTitle;

    private String _sitUp;

    private String _title;

    private String _start;

    private String _stop;

    private String _timeLeft;

    public String getTakeBreak()
    {
        return _takeBreak;
    }

    public void setTakeBreak( final String takeBreak )
    {
        _takeBreak = takeBreak;
    }

    public String getSitUp()
    {
        return _sitUp;
    }

    public void setSitUp( final String sitUp )
    {
        _sitUp = sitUp;
    }

    public String getTitle()
    {
        return _title;
    }

    public void setTitle( final String title )
    {
        _title = title;
    }

    public String getStart()
    {
        return _start;
    }

    public void setStart( final String start )
    {
        _start = start;
    }

    public String getStop()
    {
        return _stop;
    }

    public void setStop( final String stop )
    {
        _stop = stop;
    }

    public String getTakeBreakTitle()
    {
        return _takeBreakTitle;
    }

    public void setTakeBreakTitle( final String takeBreakTitle )
    {
        _takeBreakTitle = takeBreakTitle;
    }

    public String getTimeLeft()
    {
        return _timeLeft;
    }

    public void setTimeLeft( final String timeLeft )
    {
        _timeLeft = timeLeft;
    }

    public static PostureMessages populateMessages()
    {
        final PostureMessages postureMessages = new PostureMessages();
        postureMessages.setTakeBreak( "Take a break!" );
        postureMessages.setTakeBreakTitle( "Please listen to this advice." );
        postureMessages.setSitUp( "Sit up, please!" );
        postureMessages.setTitle( "Posture Helper" );
        postureMessages.setStart( "Start" );
        postureMessages.setStop( "Stop" );
        postureMessages.setTimeLeft( "Time left: " );
        return postureMessages;
    }
}
