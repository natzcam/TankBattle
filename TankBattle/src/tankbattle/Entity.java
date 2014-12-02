/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tankbattle;

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author nathaniel
 */
public interface Entity {
    public boolean isVisible();
    public boolean isActive();
    public void update(float delta);
    public void render(GL10 gl, float delta);
    public Rectangle getBounds();
    public Vector2 getPosition();
    public Vector2 getVelocity();
    public void collidedWith(Entity otherEntity);
    public Rectangle getProjectedBounds();
    public void willCollideWith(Entity otherEntity);
}
