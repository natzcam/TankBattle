/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nac.tankbattle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author nathaniel
 */
public class Resources {

  public static final int HEIGHT = 32;
  public static final int WIDTH = 32;
  public static final Texture TEXTURE = new Texture(Gdx.files.internal("data/tankbrigade-t.png"));
  
  public static final TextureRegion[] TEXTURES_EXPLOSION = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(1), get(1), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(2), get(1), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(3), get(1), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_TANK_MOVE = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(16), get(1), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(17), get(1), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(18), get(1), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(19), get(1), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(20), get(1), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(21), get(1), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(21), get(1), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_TANK_FIRE = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(15), get(3), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(15), get(2), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(15), get(1), WIDTH, HEIGHT)
  };
  
  public static final TextureRegion[] TEXTURES_BULLET = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(4), get(1), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(6), get(1), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(7), get(1), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(5), get(1), WIDTH, HEIGHT)
  };

  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_1_2 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(1), get(2), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(4), get(12), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(5), get(12), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(6), get(12), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_2_2 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(2), get(2), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(7), get(12), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(9), get(12), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(11), get(12), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_3_2 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(3), get(2), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(8), get(12), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(10), get(12), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(12), get(12), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_4_2 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(4), get(2), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(4), get(8), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(5), get(8), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(6), get(8), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_5_2 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(5), get(2), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(1), get(8), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(2), get(8), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(3), get(8), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_6_2 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(6), get(2), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(7), get(8), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(8), get(8), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(9), get(8), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_7_2 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(7), get(2), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(1), get(12), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(2), get(12), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(3), get(12), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_8_2 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(8), get(2), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(13), get(1), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(13), get(2), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(13), get(3), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_9_2 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(9), get(2), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(14), get(1), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(14), get(2), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(14), get(3), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_10_2 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(10), get(2), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(1), get(11), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(2), get(11), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(3), get(11), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_11_2 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(11), get(2), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(10), get(8), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(11), get(8), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(12), get(8), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_12_2 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(12), get(2), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(1), get(9), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(2), get(9), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(3), get(9), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_1_3 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(1), get(3), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(1), get(3), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(1), get(3), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(1), get(3), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_2_3 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(2), get(3), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(2), get(3), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(2), get(3), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(2), get(3), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_3_3 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(3), get(3), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(7), get(9), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(8), get(9), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(9), get(9), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_4_3 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(4), get(3), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(7), get(10), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(8), get(10), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(9), get(10), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_5_3 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(5), get(3), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(4), get(9), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(5), get(9), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(6), get(9), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_6_3 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(6), get(3), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(13), get(4), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(13), get(5), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(13), get(6), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_7_3 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(7), get(3), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(14), get(4), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(14), get(5), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(14), get(6), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_8_3 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(8), get(3), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(10), get(10), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(11), get(10), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(12), get(10), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_9_3 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(9), get(3), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(4), get(11), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(5), get(11), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(6), get(11), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_10_3 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(10), get(3), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(7), get(11), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(8), get(11), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(9), get(11), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_11_3 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(11), get(3), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(10), get(11), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(11), get(11), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(12), get(11), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_12_3 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(12), get(3), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(13), get(10), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(14), get(10), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(15), get(10), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_1_4 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(1), get(4), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(13), get(7), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(13), get(8), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(13), get(9), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_2_4 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(2), get(4), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(14), get(7), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(14), get(8), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(14), get(9), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_3_4 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(3), get(4), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(10), get(9), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(11), get(9), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(12), get(9), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_12_4 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(12), get(4), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(13), get(11), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(14), get(11), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(15), get(11), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_1_5 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(1), get(5), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(1), get(10), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(2), get(10), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(3), get(10), WIDTH, HEIGHT)
  };
  public static final TextureRegion[] TEXTURES_BLOCK_TYPE_2_5 = new TextureRegion[]{
    new TextureRegion(TEXTURE, get(2), get(5), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(4), get(10), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(5), get(10), WIDTH, HEIGHT),
    new TextureRegion(TEXTURE, get(6), get(10), WIDTH, HEIGHT)
  };
  
  private static int get(int loc) {
    return loc * 32 + (loc * 1);
  }
}
