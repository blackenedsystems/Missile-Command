package com.blackenedsystems.games.missilecommand;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import java.awt.geom.Point2D;
import java.awt.*;

import com.blackenedsystems.games.missilecommand.mock.MockExplosion;
import com.blackenedsystems.games.missilecommand.mock.MockGraphics2D;

/**
 * Unit tests for the <code>Explosion</code> class.
 *
 * @author Alan Tibbetts
 * @since Feb 21, 2010, 10:54:40 PM
 */
public class ExplosionTest {

    private MockExplosion explosion;

    @Before
    public void setup() {
        explosion = new MockExplosion(new Point2D.Double(100,100), 30);
    }

    @Test
    public void explostionCreated() {
        assertEquals(0, explosion.getCurrentRadius());
        assertEquals(30, explosion.getMaximumBlastRadius());
        assertNull(explosion.getBounds());
    }

    @Test
    public void expandingExplosion() {
        explosion.animate();
        assertEquals(1, explosion.getCurrentRadius());
    }

    @Test
    public void contractingExplosion() {
        explosion.setCurrentRadius(20);
        explosion.setExpanding(false);

        explosion.animate();
        assertEquals(19, explosion.getCurrentRadius());
    }

    @Test
    public void completeExplostion() {
        while (!explosion.isComplete()) {
            explosion.animate();
        }
        assertEquals(0, explosion.getCurrentRadius());
    }

    @Test
    public void destroy() {
        assertFalse(explosion.isComplete());
        explosion.destroy();
        assertTrue(explosion.isComplete());
    }

    @Test
    public void testBounds() {
        MockGraphics2D mockGraphicsContext = new MockGraphics2D();

        for (int i=0; i<10; i++) {
            explosion.animate();
            explosion.draw(mockGraphicsContext);
        }

        Rectangle bounds = explosion.getBounds();
        assertNotNull(bounds);
    }
}
