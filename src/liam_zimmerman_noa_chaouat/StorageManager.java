package liam_zimmerman_noa_chaouat;

import java.io.*;

public class StorageManager {

    public static college_manager loadManager(String filename) {
        File file = new File(filename);

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                return (college_manager) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                return null;
            }
        }

        return null;
    }

    public static boolean saveManager(college_manager manager, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(manager);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
