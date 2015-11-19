/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nac.tankbattle;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.Client;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import nac.tankbattle.net.InputMessage;
import nac.tankbattle.net.InputMessageType;
import nac.tankbattle.net.TankMessage;
import nac.tankbattle.net.TankMessageType;

public class TankGame implements ApplicationListener {

  public static final int WIDTH = 480;
  public static final int HEIGHT = 480;
  SpriteBatch spriteBatch;
  OrthographicCamera camera;
  TiledMap map;
  OrthogonalTiledMapRenderer mapRenderer;
  ShapeRenderer shapeRenderer;
  List<Entity> entityList = new ArrayList<>();
  List<Entity> entityRemoveList = new ArrayList<>();
  boolean debug = false;
  Rectangle mapBounds;
  Tank me;
  Map<Integer, Tank> tankMap = new ConcurrentHashMap<>();
  RemoteInput remoteInput = new RemoteInput();
  private Server server = null;
  private Client client = null;
  private String host;
  private boolean runAsServer = false;

  public TankGame(boolean runAsServer, String host) {
    this.host = host;
    this.runAsServer = runAsServer;

    if (runAsServer) {
      //server
      server = new Server();
      Kryo kryo = server.getKryo();
      kryo.register(InputMessage.class);
      kryo.register(TankMessage.class);
      server.start();
      try {
        server.bind(54555, 54777);
      } catch (IOException ex) {
        Logger.getLogger(TankGame.class.getName()).log(Level.SEVERE, null, ex);
      }

      Listener listener = new Listener() {
        @Override
        public void received(Connection connection, Object object) {
          if (object instanceof InputMessage) {
            server.sendToAllExceptUDP(connection.getID(), object);
          } else if (object instanceof TankMessage) {
            TankMessage pm = (TankMessage) object;
            if (pm.type == TankMessageType.ENTER) {
              for (Tank tank : tankMap.values()) {
                connection.sendUDP(createJoinedAlreadyMessage(tank));
              }
              if (connection.getID() != me.id) {
                connection.sendUDP(connection.sendUDP(createJoinedAlreadyMessage(me)));
              }
              server.sendToAllExceptUDP(connection.getID(), object);
            }
          }
        }

        @Override
        public void disconnected(Connection connection) {
          TankMessage tm = new TankMessage();
          tm.type = TankMessageType.EXIT;
          tm.id = connection.getID();
          server.sendToAllExceptUDP(connection.getID(), tm);
        }
      };
      //listener = new Listener.LagListener(500, 1000, listener);
      server.addListener(listener);
    }

    //client
    client = new Client();
    Kryo kryo = client.getKryo();
    kryo.register(InputMessage.class);
    kryo.register(TankMessage.class);
    client.start();
    try {
      client.connect(5000, host, 54555, 54777);
    } catch (IOException ex) {
      Logger.getLogger(TankGame.class.getName()).log(Level.SEVERE, null, ex);
    }
    client.addListener(new Listener() {
      @Override
      public void received(Connection connection, Object object) {
        if (object instanceof InputMessage) {
          InputMessage im = (InputMessage) object;
          if (im.type == InputMessageType.KEY_DOWN) {
            remoteInput.setKeyDown(im.id, im.keyCode);
          } else if (im.type == InputMessageType.KEY_UP) {
            remoteInput.setKeyUp(im.id, im.keyCode);
          }
        } else if (object instanceof TankMessage) {

          TankMessage pm = (TankMessage) object;
          Tank t;
          switch (pm.type) {
            case TankMessageType.ENTER:
              t = new Tank(TankGame.this, pm.id, 200, 200);
              tankMap.put(pm.id, t);
              addTank(t);
              break;
            case TankMessageType.JOINED_ALREADY:
              t = new Tank(TankGame.this, pm.id, pm.x, pm.y);
              t.velocity.set(pm.dx, pm.dy);
              t.active = pm.active;
              t.visible = pm.visible;
              t.rotation = pm.rotation;
              tankMap.put(pm.id, t);
              addTank(t);
              break;
            case TankMessageType.EXIT:
              t = tankMap.remove(pm.id);
              removeEntity(t);
              break;
          }

        }
      }
    });
  }

  @Override
  public void create() {
    spriteBatch = new SpriteBatch();
    camera = new OrthographicCamera(WIDTH, HEIGHT);
    map = new TmxMapLoader().load("data/map.tmx");
    TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("background");
    mapBounds = new Rectangle(0, 0, layer.getWidth() * layer.getTileWidth(), layer.getHeight() * layer.getTileHeight());
    mapRenderer = new OrthogonalTiledMapRenderer(map);
    shapeRenderer = new ShapeRenderer();
    List<Block> blocks = MapUtil.getBlocks(this, map);
    for (Block block : blocks) {
      addEntity(block);
    }

    Gdx.input.setInputProcessor(new InputAdapter() {
      @Override
      public boolean keyUp(int keycode) {
        if (keycode == Keys.A || keycode == Keys.D || keycode == Keys.S || keycode == Keys.W
                || keycode == Keys.SPACE) {
          InputMessage im = new InputMessage();
          im.type = InputMessageType.KEY_UP;
          im.keyCode = keycode;
          im.id = client.getID();
          client.sendUDP(im);
        }
        return true;
      }

      @Override
      public boolean keyDown(int keycode) {
        if (keycode == Keys.A || keycode == Keys.D || keycode == Keys.S || keycode == Keys.W
                || keycode == Keys.SPACE) {
          InputMessage im = new InputMessage();
          im.type = InputMessageType.KEY_DOWN;
          im.keyCode = keycode;
          im.id = client.getID();
          client.sendUDP(im);
        }
        return true;
      }
    });

    me = new Tank(this, client.getID(), 200, 200);
    addTank(me);
    TankMessage pm = new TankMessage();
    pm.id = client.getID();
    pm.type = TankMessageType.ENTER;
    client.sendUDP(pm);
  }

  public void addEntity(Entity add) {
    entityList.add(add);
  }

  public void addTank(Tank tank) {
    entityList.add(tank);
  }

  public void removeEntity(Entity entity) {
    entityRemoveList.add(entity);
  }

  @Override
  public void render() {
    //clear
    Graphics graphics = Gdx.graphics;
    float delta = graphics.getDeltaTime();
    GL20 gl = graphics.getGL20();
    gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    //camera
    camera.position.set(me.position.x, me.position.y, 0);
    camera.update();

    //map
    mapRenderer.setView(camera);
    mapRenderer.render();

    //input logic
    Input input = Gdx.input;
    if (input.isKeyPressed(Keys.A)) {
      me.moveLeft();
    }
    if (input.isKeyPressed(Keys.W)) {
      me.moveUp();
    }
    if (input.isKeyPressed(Keys.D)) {
      me.moveRight();
    }
    if (input.isKeyPressed(Keys.S)) {
      me.moveDown();
    }
    if (input.isKeyPressed(Keys.SPACE)) {
      me.shoot();
    }

    for (Tank t : tankMap.values()) {
      if (remoteInput.isKeyPressed(t.id, Keys.A)) {
        t.moveLeft();
      }
      if (remoteInput.isKeyPressed(t.id, Keys.W)) {
        t.moveUp();
      }
      if (remoteInput.isKeyPressed(t.id, Keys.D)) {
        t.moveRight();
      }
      if (remoteInput.isKeyPressed(t.id, Keys.S)) {
        t.moveDown();
      }
      if (remoteInput.isKeyPressed(t.id, Keys.SPACE)) {
        t.shoot();
      }
    }

    //update
    for (int i = 0, len = entityList.size(); i < len; i++) {
      Entity entity1 = entityList.get(i);
      Rectangle entProjBounds1 = entity1.getProjectedBounds();
      Rectangle entBounds1 = entity1.getBounds();
      for (int j = i + 1; j < entityList.size(); j++) {
        Entity entity2 = entityList.get(j);
        Rectangle entProjBounds2 = entity2.getProjectedBounds();
        Rectangle entBounds2 = entity2.getBounds();

        if (entity1.isActive() && entity2.isActive()) {
          if (Intersector.intersectRectangles(entProjBounds1, entProjBounds2, new Rectangle())) {
            entity1.willCollideWith(entity2);
            entity2.willCollideWith(entity1);
          }
        }
        if (entity1.isActive() && entity2.isActive()) {
          if (Intersector.intersectRectangles(entBounds1, entBounds2, new Rectangle())) {
            entity1.collidedWith(entity2);
            entity2.collidedWith(entity1);
          }
        }
      }
      if (entity1.isActive()) {
        if (entProjBounds1.x < 0 || (entProjBounds1.x + entProjBounds1.width) > mapBounds.width
                || entProjBounds1.y < 0 || (entProjBounds1.y + entProjBounds1.height) > mapBounds.height) {
          entity1.willCollideWith(null);
        }
      }
      if (entity1.isActive()) {
        entity1.update(delta);
      }
    }

    //remove
    entityList.removeAll(entityRemoveList);
    entityRemoveList.clear();

    //render
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();

    for (Entity entity : entityList) {
      if (entity.isVisible()) {
        entity.render(gl, delta);
      }
    }
    spriteBatch.end();

    //remove
    entityList.removeAll(entityRemoveList);
    entityRemoveList.clear();

    //debug
    if (debug) {
      shapeRenderer.setProjectionMatrix(camera.combined);
      shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
      for (Entity entity : entityList) {
        if (entity.isVisible()) {
          shapeRenderer.setColor(Color.RED);
          Rectangle rect = entity.getBounds();
          shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
          shapeRenderer.setColor(Color.BLUE);
          rect = entity.getProjectedBounds();
          shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
          shapeRenderer.setColor(Color.BLACK);
          Vector2 pos = entity.getPosition();
          shapeRenderer.circle(pos.x, pos.y, 5);
        }
      }
      shapeRenderer.end();
    }
  }

  private TankMessage createJoinedAlreadyMessage(Tank tt) {
    TankMessage pm1 = new TankMessage();
    pm1.id = tt.id;
    pm1.type = TankMessageType.JOINED_ALREADY;
    pm1.active = tt.active;
    pm1.visible = tt.visible;
    pm1.rotation = tt.rotation;
    pm1.x = tt.position.x;
    pm1.y = tt.position.y;
    pm1.dx = tt.velocity.x;
    pm1.dy = tt.velocity.y;
    return pm1;
  }

  @Override
  public void resize(int width, int height) {
    spriteBatch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
  }

  @Override
  public void pause() {
  }

  @Override
  public void resume() {
  }

  @Override
  public void dispose() {
  }
}
