
package com.mycompany.inventorymainscreen;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationService {
       private static List<User> users = new ArrayList<>();

  static {
    // Sample users
    users.add(new User("manager", "manager123", "inventory_manager"));
    users.add(new User("seller", "seller123", "seller"));
    users.add(new User("customer", "customer123", "customer"));
  }

  public static User authenticateUser(String username, String password) {
    for (User user : users) {
      if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
        return user;
      }
    }
    return null; // Authentication failed
  }
}
