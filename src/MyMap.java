import java.util.ArrayList;

public class MyMap<K,V> implements MapInterface<K,V>
{
    private ArrayList<MapEnt<K,V>> set;

    public MyMap()
    {
        set= new ArrayList<>();
    }
    public void clear()
    {
        set.clear();
    }
    public boolean containsKey(K key)
    {
        for (MapEnt<K,V> ent : set)
        {
            if(ent.getKey().equals(key))
                return true;
        }
        return false;
    }
    public boolean containsValue(V value)
    {
        for(MapEnt<K,V> ent : set)
            if (ent.getValue().equals(value))
                return true;
        return false;
    }
    public MySet<MapEnt<K,V>> entrySet()
    {
        MySet<MapEnt<K,V>> entries= new MySet<>();
        for(MapEnt<K,V> ent : set)
        {
            entries.add(ent);
        }
        return entries;
    }

    public V get(K o)
    {
        for(MapEnt<K,V> ent : set)
        {
            if(ent.getKey().equals(o))
                return ent.getValue();
        }
        return null;
    }

    public boolean isEmpty()
    {
        return set.isEmpty();
    }

    public V put(K key, V value)
    {
        for (MapEnt<K,V> ent : set)
        {
            if(ent.getKey().equals(key))
            {
                V old= ent.getValue();
                ent.setValue(value);
                return old;
            }
        }
        set.add(new MapEnt<K,V>(key,value));
        return null;
    }

    public int size()
    {
        return set.size();
    }

    public MySet<K> keySet()
    {
        MySet<K> keys= new MySet<>();
        for(MapEnt<K,V> ent : set)
        {
            keys.add(ent.getKey());
        }
        return keys;
    }

    public ArrayList<V> values()
    {
        ArrayList<V> list= new ArrayList<V>();
        for (MapEnt<K,V> ent : set)
        {
            list.add(ent.getValue());
        }
        return list;
    }

    public V remove(K key)
    {
        for (MapEnt<K,V> ent : set)
        {
            if(ent.getKey().equals(key))
            {
                V old= ent.getValue();
                set.remove(ent);
                return old;
            }
        }
        return null;
    }
}
