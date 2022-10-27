/**
 * Singleton class that keeps track of data objects to be used in the lifetime of the application.
 * Provides Serialization and Deserialization implementations of data objects into files.
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-10-26
 */

package Models;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataStoreManager {
    /**
     * DataStoreManager singleton pattern instance.
     */
    protected static DataStoreManager instance = null;

    /**
     * Creates a new instance of DataStoreManager if there isn't one,
     * else returns the old created instance.
     *
     * @return Instance of DataStoreManager singleton.
     */
    public static DataStoreManager getInstance() {
        if (instance == null)
            instance = new DataStoreManager();
        return instance;
    }

    /**
     * Dictionary store that holds all serialized and deserialized objects
     *
     * <br> &#064;Key  Type.class e.g. Admin.class
     * <br> &#064;Value  ArrayList<Serializable> e.g. ArrayList<Admin>
     */
    private final Map<Class<? extends Serializable>, ArrayList<Serializable>> Store = new HashMap<>();

    private final String DataFolder = "\\Data\\";
    /**
     * Adds the given object to the ArrayList stored in the DataStore
     * <br>Example:
     * <pre>
     * {@code
     *      Admin obj = new Admin();
     *      DataStoreManager.getInstance().AddToStore(obj);
     * }
     * </pre>
     *
     * @param object object to be added to the dataStore
     */
    @SuppressWarnings("unchecked")
    public void AddToStore(Serializable object) {
        // Get class ArrayList that will store the object
        ArrayList<Serializable> list = (ArrayList<Serializable>)GetStore(object.getClass());
        if (list == null) {
            Store.put(object.getClass(), new ArrayList<>());
            list = (ArrayList<Serializable>) GetStore(object.getClass());
        }
        // Add to ArrayList
        list.add(object);

        // Serialize and save to disk
        Serializer(list, object.getClass());
    }

    /**
     * Given a Type class as the Key, retrieve the ArrayList Value from the DataStore dictionary
     * <br>Example:
     * <pre>
     * {@code
     * 		ArrayList<Admin> adminList = DataStoreManager.getInstance().GetStore(Admin.class);
     * }
     * </pre>
     *
     * @param Deserializable Type class of the ArrayList to be retrieved. e.g. Admin.class
     * @param <T>            Generic type that is Serializable
     * @return The ArrayList for the given class from the DataStore dictionary
     */
    @SuppressWarnings("unchecked")
    public <T extends Serializable> ArrayList<T> GetStore(Class<T> Deserializable) {
        return (ArrayList<T>) Store.get(Deserializable);
    }

    /**
     * Retrieve all Serialized datafiles from the project root folder,
     * and Deserialize the files into the DataStore dictionary
     * <br>Example:
     * <pre>
     * {@code
     *      DataStoreManager.getInstance().Load();
     * }
     * </pre>
     */
    @SuppressWarnings("unchecked")
    public void LoadAll() {
        List<String> fileList;
        // Get the root path of the project
        Path start = Paths.get(System.getProperty("user.dir"), DataFolder);
        // Try walking through the directory and subdirectory for serialized .dat files
        try (Stream<Path> stream = Files.walk(start, Integer.MAX_VALUE)) {
            fileList = stream
                    .map(String::valueOf)
                    .filter(file -> !new File(file).isDirectory() && file.lastIndexOf(".dat") > 0)
                    .map(file -> file.substring(start.toString().length() + 1))
                    .map(file -> file.substring(0, file.length() - 7).trim())
                    .sorted()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Deserialize all files retrieved
        for (String file : fileList) {
            try {
                Class<? extends Serializable> serializableClass = (Class<? extends Serializable>) Class.forName(file);
                // Deserialize the retrieved file class
                var deserializedArray = Deserializer(serializableClass);
                // Put it back into the dictionary
                Store.put(serializableClass, (ArrayList<Serializable>) deserializedArray);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Manual call to serialize all data stored in DataStore
     */
    public void SaveAll() {
        for (var entry : Store.entrySet()) {
            // Serialize and save to disk
            Serializer(entry.getValue(), entry.getKey());
        }
    }

    /**
     * Serializer method opens a FileOutputStream and ObjectOutputStream to write a Serializable object to file
     *
     * @param serializable Takes in an ArrayList<subClassName> Object to be serialized
     * @param subClassName Type class of the inner Object to be serialized
     * @param <T>          Generic type that is Serializable
     */
    private <T extends Serializable> void Serializer(ArrayList<?> serializable, Class<T> subClassName) {
        // Open file writers
        Path path = Paths.get(System.getProperty("user.dir"), DataFolder,subClassName.getName() + "_Db.dat");
        // Using try-with-resource to automatically clean up afterwards
        try (FileOutputStream fileOutputStream = new FileOutputStream(path.toString())){
            try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                // Try to write object to output
                objectOutputStream.writeObject(serializable);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deserializer method opes a FileInputStream and ObjectInputStream, and casts it back
     * to an ArrayList Object to be added back to the DataStore.
     *
     * @param subClassName Type class to be deserialized
     * @param <T>          Generic Type that is Serializable
     * @return Deserialized ArrayList<T> Object
     */
    @SuppressWarnings("unchecked")
    private <T extends Serializable> ArrayList<T> Deserializer(Class<T> subClassName){
        // Open file readers
        Path path = Paths.get(System.getProperty("user.dir"), DataFolder,subClassName.getName() + "_Db.dat");
        // Using try-with-resource to automatically clean up afterwards
        try (FileInputStream fileInputStream = new FileInputStream(path.toString())){
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                // Read object and cast back to ArrayList<T>
                Object obj= objectInputStream.readObject();
                if(obj instanceof ArrayList){
                    return (ArrayList<T>) obj;
                }
                return null;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}