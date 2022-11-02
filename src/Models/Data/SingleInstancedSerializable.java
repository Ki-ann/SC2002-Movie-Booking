package Models.Data;

import Models.DataStoreManager;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.UUID;

public abstract class SingleInstancedSerializable implements Serializable {
    private UUID uuid;

    public SingleInstancedSerializable(){
        // Assign ID
        uuid = UUID.randomUUID();
    }

    public void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
    }

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
