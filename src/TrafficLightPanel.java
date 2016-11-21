import javax.swing.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class TrafficLightPanel extends JPanel
{
    private boolean _running;

    private final int _radius = 15;

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension( ( int ) ( 1.1 * _radius ), ( int ) ( 1.1 * _radius ) );
    }

    @Override
    public void paintComponent( final Graphics g )
    {

        final BufferedImage buffer = new BufferedImage( _radius * 2, _radius * 2, BufferedImage.TYPE_INT_ARGB );
        final Graphics2D g2d = buffer.createGraphics();

        final RenderingHints rh = new RenderingHints( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        rh.put( RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE );
        rh.put( RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY );
        g2d.setRenderingHints( rh );

        final Ellipse2D circle = new Ellipse2D.Float( 0, 0, _radius, _radius );
        final Shape clip = g2d.getClip();
        g2d.setClip( circle );
        final AffineTransform at = g2d.getTransform();

        g2d.setTransform( AffineTransform.getRotateInstance( Math.toRadians( 45 ), _radius / 2, _radius / 2 ) );

        g2d.setColor( _running ? Color.GREEN : Color.RED );
        g2d.fill( circle );

        g2d.setColor( Color.black );

        g2d.setTransform( at );
        g2d.setClip( clip );
        g2d.setClip( null );
        g2d.setStroke( new BasicStroke( 1.0f ) );
        g2d.draw( circle );
        g2d.dispose();
        g.drawImage( buffer, 0, 0, this );
    }

    void setRunning( final boolean running )
    {
        _running = running;
        repaint();
    }
}
