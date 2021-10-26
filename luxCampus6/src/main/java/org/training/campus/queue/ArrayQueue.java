package org.training.campus.queue;

import static java.lang.System.arraycopy;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class ArrayQueue<E> implements Queue<E> {
	static final int INITIAL_CAPACITY = 10;
	private Object[] buffer;
	private int head;
	private int tail;

	public ArrayQueue() {
		this(INITIAL_CAPACITY);
	}

	public ArrayQueue(int capacity) {
		if (capacity <= 0)
			throw new IllegalArgumentException("initial capacity should be positive value");
		buffer = new Object[capacity];
		head = 0;
		tail = 0;
	}

	private int getNewCapacity(int requestedCapacity) {
		return requestedCapacity * 2;
	}

	@Override
	public void enqueue(E e) {
		if (notEnoughCapacity()) {
			expandBuffer();
		} else if (rightBoundReached()) {
			shiftToStart();
		}
		buffer[tail++] = e;
	}

	private boolean rightBoundReached() {
		return tail >= buffer.length;
	}

	private boolean notEnoughCapacity() {
		return size() >= capacity();
	}

	private void expandBuffer() {
		Object[] newBuffer = new Object[getNewCapacity(capacity() + 1)];
		arraycopy(buffer, head, newBuffer, 0, size());
		tail = size();
		head = 0;
		buffer = newBuffer;
	}

	private void shiftToStart() {
		arraycopy(buffer, head, buffer, 0, size());
		tail = size();
		head = 0;
	}

	@Override
	public E dequeue() {
		if (isEmpty())
			throw new NoSuchElementException("queue is empty");
		return (E) buffer[head++];
	}

	@Override
	public boolean isEmpty() {
		return head >= tail;
	}

	@Override
	public boolean contains(E e) {
		for (E el : this) {
			if (Objects.equals(el, e))
				return true;
		}
		return false;
	}

	@Override
	public void clear() {
		head = 0;
		tail = 0;
	}

	@Override
	public int size() {
		return tail - head;
	}

	int capacity() {
		return buffer.length;
	}

	@Override
	public E peek() {
		if (isEmpty())
			throw new NoSuchElementException("queue is empty");
		return (E) buffer[head];
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<>() {
			private int index = head;

			@Override
			public boolean hasNext() {
				return index < tail;
			}

			@Override
			public E next() {
				if (index >= tail)
					throw new NoSuchElementException("check if iterator has any elements before advancing");
				return (E) buffer[index++];
			}

		};
	}

	@Override
	public String toString() {
		final var join = new StringJoiner(",", "[", "]");
		for (E e : this) {
			join.add(e.toString());
		}
		return join.toString();
	}

}
