package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ItemsGenerator<Type> implements Iterable<Type> {

    private List<Type> items;
    private int countOfItems;
    private Random random;

    public ItemsGenerator(List<Type> items) {
        this.items = items;
        countOfItems = items.size();
        random = new Random();
    }

    @Override
    public Iterator<Type> iterator() {
        Iterator<Type> iterator = new Iterator<Type>() {

            @Override
            public boolean hasNext() {
                return countOfItems > 0;
            }

            @Override
            public Type next() {
                int index = random.nextInt(countOfItems--);
                Type question = items.get(index);
                items.set(index, items.get(countOfItems));
                return question;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return iterator;
    }

    public int getCountOfItems() { return countOfItems; }

    public List<Type> getAllItems() { return new ArrayList<>(items); }
}