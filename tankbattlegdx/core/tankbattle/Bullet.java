/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tankbattle;

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author nathaniel
 */
public class Bullet implements Entity {

  public static final int TEXTURE_WIDTH = 32;
  public static final int TEXTURE_HEIGHT = 32;
  public static final int TEXTURE_OFFSET = 16;
  public static final int BOUNDS_WIDTH = 4;
  public static final int BOUNDS_HEIGHT = 4;
  public static final int BOUNDS_OFFSET = 2;
  final TextureRegion BULLET_TEXTURE;
  final Animation EXPLODE;
  final TankGame game;
  final SpriteBatch batch;
  Vector2 position;
  Vector2 velocity;
  float rotation = 360f;
  Rectangle bounds;
  Rectangle projectedBounds;
  boolean visible = true;
  boolean active = true;
  boolean hit = false;
  float animTime = 0;
  Tank owner;

  public Bullet(TankGame game, Tank owner, float x, float y) {
    this.owner = owner;
    this.game = game;
    batch = game.spriteBatch;
    position = new Vector2(x, y);
    velocity = new Vector2(0, 0);
    bounds = new Rectangle(x, y, BOUNDS_WIDTH, BOUNDS_HEIGHT);
    projectedBounds = new Rectangle(x, y, BOUNDS_WIDTH, BOUNDS_HEIGHT);
    BULLET_TEXTURE = Resources.TEXTURES_BULLET[0];
    EXPLODE = new Animation(0.2f, Resources.TEXTURES_EXPLOSION);
  }

  @Override
  public boolean isVisible() {
    return visible;
  }

  @Override
  public boolean isActive() {
    return active;
  }

  @Override
  public void update(float delta) {
    position.add(velocity);
    bounds.x = position.x - BOUNDS_OFFSET;
    bounds.y = position.y - BOUNDS_OFFSET;
  }

  @Override
  public void render(GL10 gl, float delta) {
    if (hit) {
      animTime += delta;
      TextureRegion region = EXPLODE.getKeyFrame(animTime);
      batch.draw(region, position.x - TEXTURE_OFFSET, position.y - TEXTURE_OFFSET, TEXTURE_OFFSET, TEXTURE_OFFSET, TEXTURE_WIDTH, TEXTURE_HEIGHT, 1, 1, rotation);
      if (EXPLODE.isAnimationFinished(animTime)) {
        visible = false;
        game.removeEntity(this);
      }
    } else {
      batch.draw(BULLET_TEXTURE, position.x - TEXTURE_OFFSET, position.y - TEXTURE_OFFSET, TEXTURE_OFFSET, TEXTURE_OFFSET, TEXTURE_WIDTH, TEXTURE_HEIGHT, 1, 1, rotation);
    }
  }

  @Override
  public Rectangle getBounds() {
    return bounds;
  }

  @Override
  public Rectangle getProjectedBounds() {
    projectedBounds.x = bounds.x + velocity.x;
    projectedBounds.y = bounds.y + velocity.y;
    return projectedBounds;
  }

  @Override
  public void collidedWith(Entity otherEntity) {
    if (otherEntity instanceof Block) {
      hit = true;
      active = false;
    }
    if (otherEntity instanceof Tank) {
      Tank t = (Tank) otherEntity;
      if (t != owner) {
        hit = true;
        active = false;
      }
    }
  }

  @Override
  public void willCollideWith(Entity otherEntity) {
    if (otherEntity == null) {
      hit = true;
      active = false;
    }
  }

  @Override
  public Vector2 getPosition() {
    return position;
  }

  @Override
  public Vector2 getVelocity() {
    return velocity;
  }
}
