/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tankbattle;

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author nathaniel
 */
public class Tank implements Entity {

  public static final int TEXTURE_WIDTH = 32;
  public static final int TEXTURE_HEIGHT = 32;
  public static final int TEXTURE_OFFSET = 16;
  public static final int BOUNDS_WIDTH = 32;
  public static final int BOUNDS_HEIGHT = 32;
  public static final int BOUNDS_OFFSET = 16;
  final TankGame game;
  final SpriteBatch batch;
  final Animation MOVE;
  final Animation FIRE;
  final Animation STAND;
  final Animation EXPLODE;
  Vector2 position;
  Vector2 velocity;
  int hp = 5;
  float rotation = 360f;
  float animTime = 0;
  Animation animation;
  Rectangle bounds;
  Rectangle projectedBounds;
  boolean visible = true;
  boolean active = true;
  final int id;
  boolean conflictedSpace = false;

  public Tank(TankGame game, int id, float x, float y) {
    this.id = id;
    this.game = game;
    batch = game.spriteBatch;
    position = new Vector2(x, y);
    velocity = new Vector2(0, 0);
    bounds = new Rectangle(x, y, BOUNDS_WIDTH, BOUNDS_HEIGHT);
    projectedBounds = new Rectangle(x, y, BOUNDS_WIDTH, BOUNDS_HEIGHT);

    MOVE = new Animation(0.2f, Resources.TEXTURES_TANK_MOVE);
    MOVE.setPlayMode(Animation.LOOP);
    FIRE = new Animation(0.2f, Resources.TEXTURES_TANK_FIRE);
    STAND = new Animation(0.2f, Resources.TEXTURES_TANK_FIRE[2]);
    EXPLODE = new Animation(0.2f, Resources.TEXTURES_EXPLOSION);
    animation = STAND;
  }

  public void moveUp() {
    if (active && animation != FIRE) {
      animation = MOVE;
      rotation = 360f;
      velocity.set(0, 1);
    }
  }

  public void moveRight() {
    if (active && animation != FIRE) {
      animation = MOVE;
      rotation = 270f;
      velocity.set(1, 0);
    }

  }

  public void moveDown() {
    if (active && animation != FIRE) {
      animation = MOVE;
      rotation = 180f;
      velocity.set(0, -1);
    }

  }

  public void moveLeft() {
    if (active && animation != FIRE) {
      animation = MOVE;
      rotation = 90f;
      velocity.set(-1, 0);
    }
  }

  public void shoot() {
    if (active && animation != FIRE) {
      animTime = 0;
      animation = FIRE;
      velocity.set(0, 0);
      Bullet b = new Bullet(game, this, position.x, position.y);
      b.rotation = rotation;
      if (rotation == 360f) {
        b.velocity.set(0, 2);
      } else if (rotation == 270f) {
        b.velocity.set(2, 0);
      } else if (rotation == 180f) {
        b.velocity.set(0, -2);
      } else {
        b.velocity.set(-2, 0);
      }
      game.addEntity(b);
    }
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
    animTime += delta;
    TextureRegion region = animation.getKeyFrame(animTime);
    batch.draw(region, position.x - TEXTURE_OFFSET, position.y - TEXTURE_OFFSET, TEXTURE_OFFSET, TEXTURE_OFFSET, TEXTURE_WIDTH, TEXTURE_HEIGHT, 1, 1, rotation);
    if (animation == FIRE) {
      if (animation.isAnimationFinished(animTime)) {
        animation = STAND;
      }
    } else if (animation == EXPLODE) {
      if (animation.isAnimationFinished(animTime)) {
        visible = false;
        game.removeEntity(this);
      }
    } else {
      animation = STAND;
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
  public void willCollideWith(Entity otherEntity) {
    if (otherEntity instanceof Block) {
      velocity.set(0, 0);
    }
    if (otherEntity instanceof Tank) {
      velocity.set(0, 0);
    }
    if (otherEntity == null) {
      velocity.set(0, 0);
    }
  }

  @Override
  public void collidedWith(Entity otherEntity) {
    if (otherEntity instanceof Bullet) {
      Bullet bullet = (Bullet) otherEntity;
      if (bullet.owner != this) {
        System.out.println("coollide");
        hp--;
        if (hp <= 0) {
          active = false;
          animation = EXPLODE;
        }
      }
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
