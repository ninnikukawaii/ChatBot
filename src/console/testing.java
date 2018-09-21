package console;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


class IterableOfQuestion<Type> implements Iterable<Type> {

    private ArrayList<Type> arrayList;
    private int countOfQuestions;
    private Random random;

    public IterableOfQuestion(ArrayList<Type> newArray) {
        this.arrayList = newArray;
        this.countOfQuestions = newArray.size();
    }

    @Override
    public Iterator<Type> iterator() {
        Iterator<Type> it = new Iterator<Type>() {

            @Override
            public boolean hasNext() {
                return countOfQuestions > 0;
            }

            @Override
            public Type next() {
                random = new Random();
                int index = random.nextInt(countOfQuestions--);
                Type question = arrayList.get(index);
                arrayList.set(index, arrayList.get(countOfQuestions));
                return question;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}