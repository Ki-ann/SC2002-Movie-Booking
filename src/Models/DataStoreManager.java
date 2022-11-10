package Models;

import Models.Data.Seat;
import Models.Data.Cineplex;
import Models.Data.CoupleSeat;
import Models.Data.Enums.SeatType;
import Models.Data.Seat;
import Views.ConsoleIOManager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Singleton class that keeps track of data objects to be used in the lifetime of the application.
 * Provides Serialization and Deserialization implementations of data objects into files.
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-10-26
 */
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
     * Dictionary store that holds all serialized and deserialized objects.
     *
     * <br> &#064;Key  Type.class e.g. Admin.class
     * <br> &#064;Value  ArrayList<Serializable> e.g. ArrayList<Admin>
     */
    private final Map<Class<? extends Serializable>, ArrayList<Serializable>> store = new HashMap<>();

    /**
     * Reference Map creates a Dictionary of duplicate objects after deserialization and assigns them to reference a single one only.
     * @see Models.Data.SingleInstancedSerializable
     */
    private final Map<UUID, Object> referenceMap = new HashMap<>();

    /**
     * File path to store the serialized data files.
     */
    private final String dataFolder = "\\Data\\";
    /**
     * Adds the given object to the ArrayList stored in the DataStore
     * <br>Example:
     * <pre>
     * {@code
     *      Admin obj = new Admin();
     *      DataStoreManager.getInstance().addToStore(obj);
     * }
     * </pre>
     *
     * @param object object to be added to the dataStore
     */
    @SuppressWarnings("unchecked")
    public void addToStore(Serializable object) {
        // Get class ArrayList that will store the object
        ArrayList<Serializable> list = (ArrayList<Serializable>) getStore(object.getClass());

        // Add to ArrayList
        list.add(object);

        // Serialize and save to disk
        serializer(list, object.getClass());
    }

    /**
     * Removes the given object from the ArrayList stored in the DataStore.
     * <br>Example:
     * <pre>
     * {@code
     *      DataStoreManager.getInstance().removeFromStore(obj);
     * }
     * </pre>
     *
     * @param object object to be removed from the dataStore
     */
    @SuppressWarnings("unchecked")
    public void removeFromStore(Serializable object){
        // Get class ArrayList that will store the object
        ArrayList<Serializable> list = (ArrayList<Serializable>) getStore(object.getClass());

        // Not found, does not have a list
        if (list.size() == 0) {
            return;
        }
        // Remove from ArrayList
        list.remove(object);

        // Serialize and save to disk
        serializer(list, object.getClass());
    }
    /**
     * Given a Type class as the Key, retrieve the ArrayList Value from the DataStore dictionary.
     * <br>Example:
     * <pre>
     * {@code
     * 		ArrayList<Admin> adminList = DataStoreManager.getInstance().getStore(Admin.class);
     * }
     * </pre>
     *
     * @param Deserializable Type class of the ArrayList to be retrieved. e.g. Admin.class
     * @param <T>            Generic type that is Serializable
     * @return The ArrayList for the given class from the DataStore dictionary
     */
    @SuppressWarnings("unchecked")
    public <T extends Serializable> ArrayList<T> getStore(Class<T> Deserializable) {
        ArrayList<T> list = (ArrayList<T>) store.get(Deserializable);
        if (list == null) {
            list = new ArrayList<>();
            store.put(Deserializable, (ArrayList<Serializable>) list);
        }
        return list;
    }

    /**
     * Check whether any data files have been created
     * @return true if there are no data files created and the folder is empty
     */
    public boolean isEmptyDataFolder(){
        List<String> fileList;
        // Get the root path of the project
        Path start = Paths.get(System.getProperty("user.dir"), dataFolder);
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

        return fileList.isEmpty();
    }

    /**
     * Retrieve all Serialized datafiles from the project root folder,
     * and Deserialize the files into the DataStore dictionary.
     * <br>Example:
     * <pre>
     * {@code
     *      DataStoreManager.getInstance().Load();
     * }
     * </pre>
     */
    @SuppressWarnings("unchecked")
    public void loadAll() {
        List<String> fileList;
        // Get the root path of the project
        Path start = Paths.get(System.getProperty("user.dir"), dataFolder);
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
                var deserializedArray = deserializer(serializableClass);
                // Put it back into the dictionary
                store.put(serializableClass, (ArrayList<Serializable>) deserializedArray);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Manual call to serialize all data stored in DataStore.
     */
    public void saveAll() {
        for (var entry : store.entrySet()) {
            // Serialize and save to disk
            serializer(entry.getValue(), entry.getKey());
        }
    }

    /**
     * Manual call to save a single class only.
     * @param serializable object to be serialized.
     * @param <T> The generic type of the object to be serialized.
     */
    public <T extends Serializable> void save(Class<T> serializable) {
        var set = store.entrySet().stream().filter(entry->entry.getKey() == serializable).findFirst();
        // Serialize and save to disk
        set.ifPresent(classArrayListEntry -> serializer(classArrayListEntry.getValue(), classArrayListEntry.getKey()));
    }

    /**
     * Serializer method opens a FileOutputStream and ObjectOutputStream to write a Serializable object to file.
     *
     * @param serializable Takes in an ArrayList<subClassName> Object to be serialized
     * @param subClassName Type class of the inner Object to be serialized
     * @param <T>          Generic type that is Serializable
     */
    private <T extends Serializable> void serializer(ArrayList<?> serializable, Class<T> subClassName) {
        // Open file writers
        Path path = Paths.get(System.getProperty("user.dir"), dataFolder,subClassName.getName() + "_Db.dat");
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
    private <T extends Serializable> ArrayList<T> deserializer(Class<T> subClassName){
        // Open file readers
        Path path = Paths.get(System.getProperty("user.dir"), dataFolder,subClassName.getName() + "_Db.dat");
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

    /**
     * Converts a given seat string to a cinema seat layout.
     * Each row is delimited with a "."
     * 0 = Normal Seat Type
     * 1 = Special needs Seat Type
     * X = Prohibited Seat Type
     * C = Couple Seat Type
     *
     * <br>Example:
     * <pre>
     * {@code
     * A string of:  Will produce:
     * "00X0."+      [][]_[]
     * "00X0."+      [][]_[]
     * "XXXX"        _ _ _ _
     * }
     * </pre>
     *
     * @see SeatType
     * @param seatString
     * @return
     */
    public ArrayList<ArrayList<Seat>> parseLayout(String seatString){
        var layout = new ArrayList<ArrayList<Seat>>();
        StringReader reader = new StringReader(seatString);
        int k;
        try{
            layout.add(new ArrayList<>());
            int row =0;
            int col =0;
            while((k=reader.read())!=-1){
                switch ((char) k) {
                    case 'X' -> layout.get(row).add(new Seat(SeatType.PROHIBITED, row, col++));
                    case '0' -> layout.get(row).add(new Seat(SeatType.NORMAL, row, col++));
                    case '1' -> layout.get(row).add(new Seat(SeatType.SPECIAL_NEEDS, row, col++));
                    case 'C' ->{
                        CoupleSeat left = new CoupleSeat(row, col++);
                        CoupleSeat right = new CoupleSeat(row, col++);
                        left.setCouplePair(right);
                        right.setCouplePair(left);
                        layout.get(row).add(left);
                        layout.get(row).add(right);
                        // Skip 1 since we are adding two seats side by side
                        reader.skip(1);
                    }
                    case '.' -> {
                        col = 0;
                        ++row;
                        layout.add(new ArrayList<>());
                    }
                }
            }
        }catch (Exception exception){
            ConsoleIOManager.printLine("Error");
        }
        return layout;
    }

    /**
     * Gets the instance of the reference map for deserialized object tracking
     * @return current reference map
     */
    public Map<UUID, Object> getReferenceMap() {
        return referenceMap;
    }
}