/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nac.tankbattle;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nathaniel
 */
public class RemoteInput {

  private Map<Integer, HashMap<Integer, Boolean>> inputMap = new HashMap<>();

  public RemoteInput() {
  }

  void setKeyUp(int id, int keycode) {
    HashMap<Integer, Boolean> conn = inputMap.get(id);
    if (conn == null) {
      conn = new HashMap<>();
      inputMap.put(id, conn);
    }
    conn.put(keycode, false);
  }

  void setKeyDown(int id, int keycode) {
    HashMap<Integer, Boolean> conn = inputMap.get(id);
    if (conn == null) {
      conn = new HashMap<>();;
      inputMap.put(id, conn);
    }
    conn.put(keycode, true);
  }

  public boolean isKeyPressed(int id, int keycode) {
    Boolean result;
    HashMap<Integer, Boolean> conn = inputMap.get(id);
    if (conn == null) {
      conn = new HashMap<>();
      inputMap.put(id, conn);
    }
    result = conn.get(keycode);
    if(result == null){
      return false;
    }
    return result;
  }
}
