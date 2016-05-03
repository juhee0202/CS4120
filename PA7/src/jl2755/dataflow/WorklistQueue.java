package jl2755.dataflow;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * A wrapper class for our worklist algorithm.
 * It has an underlying queue and use a set to
 * check if an object has already been added
 * to the queue.
 * 
 * @param <T>
 */
public class WorklistQueue<T> {
	private Queue<T> underlyingQueue;
	private Set<T> underlyingSet;
	
	/**
	 * Initializes the fields
	 */
	public WorklistQueue() {
		underlyingQueue = new LinkedList<T>();
		underlyingSet = new HashSet<T>();
	}
	
	/**
	 * Checks if arg is already in the queue (by
	 * checking the set) and conditionally
	 * adds the arg to the queue.
	 * 
	 * @param arg
	 */
	public void push(T arg) {
		if (!underlyingSet.contains(arg)) {
			underlyingQueue.add(arg);
			underlyingSet.add(arg);
		}
	}
	
	/**
	 * Returns the first thing that's polled
	 * from the underlyingQueue. Also removes
	 * it from the underlyingSet.
	 * 
	 * @return the polled object from the underlyingQueue.
	 */
	public T poll() {
		T temp = underlyingQueue.poll();
		underlyingSet.remove(temp);
		return temp;
	}
	
	/** 
	 * @return whether the queue/set is empty.
	 */
	public boolean isEmpty() {
		return underlyingSet.isEmpty();
	}
	
	public void addAll(Collection<T> argSet) {
		Iterator<T> tempIterator = argSet.iterator();
		while (tempIterator.hasNext()) {
			push(tempIterator.next());
		}
	}
}
