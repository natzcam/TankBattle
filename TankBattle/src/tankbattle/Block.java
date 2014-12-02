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
public class Block implements Entity {

  public static final int TEXTURE_WIDTH = 32;
  public static final int TEXTURE_HEIGHT = 32;
  public static final int TEXTURE_OFFSET = 16;
  public static final int BOUNDS_WIDTH = 32;
  public static final int BOUNDS_HEIGHT = 32;
  public static final int BOUNDS_OFFSET = 16;
  final TextureRegion BLOCK_NORMAL;
  final TextureRegion BLOCK_DAMAGED_1;
  final TextureRegion BLOCK_DAMAGED_2;
  final TextureRegion BLOCK_DAMAGED_3;
  final Animation EXPLODE;
  final TankGame game;
  final SpriteBatch spriteBatch;
  Vector2 position;
  Vector2 velocity;
  float rotation = 360f;
  float animTime = 0;
  int hp = 4;
  Rectangle bounds;
  Rectangle projectedBounds;
  boolean visible = true;
  boolean active = true;

  public Block(TankGame game, TextureRegion[] textures, float x, float y) {
    this.game = game;
    spriteBatch = game.spriteBatch;
    position = new Vector2(x, y);
    velocity = new Vector2(0, 0);
    bounds = new Rectangle(x, y, BOUNDS_WIDTH, BOUNDS_HEIGHT);
    projectedBounds = new Rectangle(x, y, BOUNDS_WIDTH, BOUNDS_HEIGHT);

    BLOCK_NORMAL = textures[0];
    BLOCK_DAMAGED_1 = textures[1];
    BLOCK_DAMAGED_2 = textures[2];
    BLOCK_DAMAGED_3 = textures[3];
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
    velocity.set(0, 0);
  }

  @Override
  public void render(GL10 gl, float delta) {
    if (hp <= 0) {
      animTime += delta;
      TextureRegion region = EXPLODE.getKeyFrame(animTime);
      spriteBatch.draw(region, position.x - TEXTURE_OFFSET, position.y - TEXTURE_OFFSET, TEXTURE_OFFSET, TEXTURE_OFFSET, TEXTURE_WIDTH, TEXTURE_HEIGHT, 1, 1, rotation);
      if (EXPLODE.isAnimationFinished(animTime)) {
        visible = false;
        game.removeEntity(this);
      }
    } else {
      switch (hp) {
        case 4:
          spriteBatch.draw(BLOCK_NORMAL, position.x - TEXTURE_OFFSET, position.y - TEXTURE_OFFSET, TEXTURE_OFFSET, TEXTURE_OFFSET, TEXTURE_WIDTH, TEXTURE_HEIGHT, 1, 1, rotation);
          break;
        case 3:
          spriteBatch.draw(BLOCK_DAMAGED_1, position.x - TEXTURE_OFFSET, position.y - TEXTURE_OFFSET, TEXTURE_OFFSET, TEXTURE_OFFSET, TEXTURE_WIDTH, TEXTURE_HEIGHT, 1, 1, rotation);
          break;
        case 2:
          spriteBatch.draw(BLOCK_DAMAGED_2, position.x - TEXTURE_OFFSET, position.y - TEXTURE_OFFSET, TEXTURE_OFFSET, TEXTURE_OFFSET, TEXTURE_WIDTH, TEXTURE_HEIGHT, 1, 1, rotation);
          break;
        case 1:
          spriteBatch.draw(BLOCK_DAMAGED_3, position.x - TEXTURE_OFFSET, position.y - TEXTURE_OFFSET, TEXTURE_OFFSET, TEXTURE_OFFSET, TEXTURE_WIDTH, TEXTURE_HEIGHT, 1, 1, rotation);
          break;
        default:
          spriteBatch.draw(BLOCK_NORMAL, position.x - TEXTURE_OFFSET, position.y - TEXTURE_OFFSET, TEXTURE_OFFSET, TEXTURE_OFFSET, TEXTURE_WIDTH, TEXTURE_HEIGHT, 1, 1, rotation);
      }
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
    if (otherEntity instanceof Bullet) {
      hp--;
      if (hp <= 0) {
        active = false;
      }
    }
  }

  @Override
  public void willCollideWith(Entity otherEntity) {
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
