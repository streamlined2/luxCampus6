package org.training.campus.queue;

public interface Queue<E> extends Iterable<E> {
	void enqueue(E e);

	E dequeue();

	boolean isEmpty();

	boolean contains(E e);

	void clear();

	int size();

	E peek();
}
