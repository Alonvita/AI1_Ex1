import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * MyPriorityQueue Class.
 * <p>
 * This class will be implement a priority queue, defined by priorities given by an our function.
 */
public class MyPriorityQueue<E> {
    //---------- LOCAL CLASS VARIABLES ----------
    private PriorityQueue<QueueElement<E>> queue;

    //---------- INITIALIZER ----------

    /**
     * MyPriorityQueue().
     *
     * taken from https://stackoverflow.com/questions/4242023/comparator-with-double-type.
     */
    public MyPriorityQueue() {
        // comparator for the priority queue compares between the priorities of each state
        this.queue = new PriorityQueue<>(Comparator.comparingDouble(QueueElement::getPriority));
    }
//        (e1, e2) -> {
//        return Double.compare(e1.getPriority(), e2.getPriority());
//    }

    //---------- UTILITY FUNCTIONS ----------

    /**
     * add(E elem, double priority).
     *
     * @param elem     E -- a generic element
     * @param priority double -- the priority of this element.
     * @return true of item was added, false otherwise.
     */
    public boolean add(E elem, double priority) {
        QueueElement<E> queueElem = new QueueElement<>(elem, priority);
        return this.queue.add(queueElem);
    }

    /**
     * top().
     *
     * @return the item from the top of this queue.
     */
    public E top() {
        QueueElement<E> queueElem = this.queue.poll();
        return queueElem.getObj();
    }

    /**
     * contains(E elm).
     *
     * @param elem E -- an element.
     * @return true if element is in queue, or false otherwise.
     */
    public boolean contains(E elem) {
        // Local Variables
        QueueElement<E> queueElem = new QueueElement<>(elem, 0);

        return this.queue.contains(queueElem);
    }

    /**
     * isEmpty().
     *
     * @return true if the queue is empty, or false otherwise.
     */
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    /**
     * QueueElement<E> -- inner class.
     */
    private class QueueElement<E> {
        //---------- LOCAL CLASS VARIABLES ----------
        private E obj;
        private double priority;


        /**
         * QueueElement(E obj, double priority).
         *
         * @param obj      E -- an object.
         * @param priority double -- object's priority.
         */
        public QueueElement(E obj, double priority) {
            this.obj = obj;
            this.priority = priority;
        }

        //---------- GETTERS ----------

        /**
         * getObj().
         *
         * @return the object held by this QueueElement.
         */
        public E getObj() {
            return this.obj;
        }

        /**
         * getPriority().
         *
         * @return the priority of this queue.
         */
        public double getPriority() {
            return this.priority;
        }

        //---------- UTILITY ----------

        /**
         * hashCode().
         *
         * @return the hash code of this code.
         */
        @Override
        public int hashCode() {
            return this.obj.hashCode();
        }

        /**
         * equals().
         *
         * @param other Object -- another object.
         * @return true if objects are equal, or false otherwise.
         */
        @Override
        public boolean equals(Object other) {
            return this.obj.equals(other);
        }
    }
}