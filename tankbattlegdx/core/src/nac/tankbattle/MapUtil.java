/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nac.tankbattle;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nathaniel
 */
public class MapUtil {

  public static List<Block> getBlocks(TankGame game, TiledMap map){
    List<Block> blocks = new ArrayList<>();
    TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("objects");
    System.out.println(layer);
    for(int i = 0; i < layer.getWidth(); i++){
      for(int j = 0; j < layer.getHeight(); j++){
        Cell cell = layer.getCell(i, j);
        
        if(cell != null){
           TiledMapTile tile = cell.getTile();
           tile.getId();
           System.out.println(tile.getId() +"cell:" + i + ":" + j + " " + cell);
        }
      }
    }
    blocks.add(new Block(game, Resources.TEXTURES_BLOCK_TYPE_1_2, 300, 300));
//    MapObjects objects = layer.getObjects();
//    for(int i = 0; i < objects.getNumObjects(); i++){
//      RectangleMapObject object = (RectangleMapObject)objects.getObject(i);
//      Field f = null;
//      try {
//        f = Resources.class.getDeclaredField((String)object.getProperties().get("texture"));
//      } catch (NoSuchFieldException | SecurityException ex) {
//        Logger.getLogger(MapUtil.class.getName()).log(Level.SEVERE, null, ex);
//      }
//      TextureRegion[] regions = null;
//      try {
//        regions = (TextureRegion[]) f.get(null);
//      } catch (IllegalArgumentException | IllegalAccessException ex) {
//        Logger.getLogger(MapUtil.class.getName()).log(Level.SEVERE, null, ex);
//      }
//      Block block = new Block(game, regions, object.getRectangle().x + 16, object.getRectangle().y + 16);
//      blocks.add(block);
//    }
    return blocks;
  }
}
