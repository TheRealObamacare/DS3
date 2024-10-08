import java.util.ArrayList;
import java.util.Iterator;
public class MySet<E> implements SetInterface<E>
{
    private ArrayList<E> set;
    public MySet()
    {
        set= new ArrayList<E>();
    }
    public boolean add(E o)
    {
        if(set.contains(o))
            return false;
        else
            return set.add(o);
    }
    public void clear()
    {
        set.clear();
    }
    public boolean contains(E o)
    {
        return set.contains(o);
    }
    public boolean isEmpty()
    {
        return set.isEmpty();
    }
    public Iterator<E> iterator()
    {
        return set.iterator();
    }
    public boolean remove(E o)
    {
        return set.remove(o);
    }
    public int size()
    {
        return set.size();
    }
}
