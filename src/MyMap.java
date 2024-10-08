import java.util.ArrayList;

public class MyMap<K,V> implements MapInterface<K,V>
{
    private MySet<MapEnt<K,V>> set;

    public MyMap()
    {
        set= new MySet<MapEnt<K,V>>();
    }
    public void clear()
    {
        set.clear();
    }
    public boolean containsKey(K key)
    {
        while(set.iterator().hasNext())
        {
            if(set.iterator().next().getKey().equals(key))
                return true;
        }
        return false;
    }
    public boolean containsValue(V value)
    {
        while(set.iterator().hasNext())
        {
            if(set.iterator().next().getValue().equals(value))
                return true;
        }
        return false;
    }
    public MySet<MapEnt<K,V>> entrySet()
    {
        return set;
    }

    public V get(K o)
    {
        while(set.iterator().hasNext())
        {
            MapEnt<K,V> ent= set.iterator().next();
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
        set.iterator();
        while(set.iterator().hasNext())
        {
            if(set.iterator().next().getKey().equals(key))
            {
                V old= set.iterator().next().getValue();
                set.iterator().next().setValue(value);
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
        MySet<K> keys= new MySet<K>();
        while(set.iterator().hasNext())
        {
            keys.add(set.iterator().next().getKey());
        }
        return keys;
    }

    public ArrayList<V> values()
    {
        ArrayList<V> list= new ArrayList<V>();
        while(set.iterator().hasNext())
        {
            list.add(set.iterator().next().getValue());
        }
        return list;
    }

    public V remove(K key)
    {
        while(set.iterator().hasNext())
        {
            MapEnt<K,V> ent= set.iterator().next();
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
