package ir.ac.kntu.resources;

import java.io.*;
import java.util.ArrayList;

public class User implements Serializable {

    private String name;

    private int maxScore;

    private int maxLevelReached;

    private static ArrayList<User> users;

    @Serial
    private static final long serialVersionUID = 42L;

    public User(String name){
        this.name = name;
        maxLevelReached = 0;
        maxScore = 0;
    }

    public static ArrayList<User> loadUsers() throws IOException {
        ArrayList<User> users = new ArrayList<>();
        File file = new File("Users.info");
        try(FileInputStream fileInputStream = new FileInputStream(file);
              ObjectInputStream input = new ObjectInputStream(fileInputStream)) {
            while (true) {
                try {
                    User user = (User) input.readObject();
                    users.add(user);
                } catch (EOFException e) {
                    break;
                } catch (Exception e) {
                    System.out.println("Something went wrong");
                }
            }
        } catch (IOException e) {
            System.out.println("No data for users have been saved");
        }
        return users;
    }

    public static void saveUsers() throws IOException {
        File file = new File("Users.info");
        try(FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream output = new ObjectOutputStream(fileOutputStream)) {
            for (User user : users) {
                try {
                    output.writeObject(user);
                } catch (IOException e){
                    System.out.println("Something went wrong with saving data!");
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong with saving data!");
        }
    }

    public static User loadUser(String userName) throws IOException {
        users = loadUsers();
        for (User user : users) {
            if (user.getName().equals(userName)){
                return user;
            }
        }
        User newUser = new User(userName);
        users.add(newUser);
        saveUsers();
        return newUser;
    }

    public String getName() {
        return name;
    }

    public void setMaxLevelReached(int maxLevelReached) {
        this.maxLevelReached = maxLevelReached;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public int getMaxLevelReached() {
        return maxLevelReached;
    }

    public String toString(){
        return name+"-"+maxLevelReached+"-"+maxScore;
    }
}
