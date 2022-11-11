package Models.Data;

import Models.DataStoreManager;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.UUID;

/**
 * SingleInstancedSerializable implements a Java Serializable to extend its functionality
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-11-11
 */
public abstract class SingleInstancedSerializable implements Serializable {
    private UUID uuid;

    /**
     * Constructor for a SingleInstancedSerializable assigns a randomly generated unique UUID
     */
    public SingleInstancedSerializable(){
        // Assign ID
        uuid = UUID.randomUUID();
    }

    /**
     * Overrides Java Serializable readObject()
     * @param ois ObjectInputStream
     * @throws IOException If object was unable to be read
     * @throws ClassNotFoundException If class was not found
     */
    public void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
    }

    /**
     * Overrides Java Serializable readResolve to retrieve a single instance
     * from DataStore ReferenceMap rather than creating new one, hence solving the de-sync problem from
     * serializing/de-serializing classes into multiple data files
     * @return A single instance of the object stored in the DataStore ReferenceMap
     */
    public Object readResolve()  {
        var referenceMap = DataStoreManager.getInstance().getReferenceMap();
        // If item with the same UUID has been deserialized, make them all refer to one same instance
        if(referenceMap.get(uuid) == null){
            referenceMap.put(this.uuid, this);
            return this;
        }else{
            return referenceMap.get(uuid);
        }
    }
}
