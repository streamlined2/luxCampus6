package org.training.campus.queue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class ArrayQueueTest {

	@Test
	void testArrayQueue() {
		ArrayQueue<String> q = new ArrayQueue<>();
		assertEquals(0, q.size());
		assertEquals(ArrayQueue.INITIAL_CAPACITY, q.capacity());
	}

	@Test
	void testArrayCapacitySuccess() {
		final int testCapacity = 1000;
		ArrayQueue<String> q = new ArrayQueue<>(testCapacity);
		assertEquals(0, q.size());
		assertEquals(testCapacity, q.capacity());
	}

	@Test
	void testArrayCapacityFail() {
		final int testCapacity = -10;
		assertThrows(IllegalArgumentException.class, () -> new ArrayQueue<String>(testCapacity),
				"negative capacity isn't accepted");
	}

	@Test
	void testIsEmpty() {
		ArrayQueue<String> q = new ArrayQueue<>();
		assertTrue(q.isEmpty());
		q.enqueue("A");
		assertFalse(q.isEmpty());
	}

	@Test
	void testEnqueueGrow() {
		ArrayQueue<String> q = new ArrayQueue<>(1);
		q.enqueue("A");
		assertEquals(1, q.size());
		assertEquals(1, q.capacity());
		q.enqueue("B");
		assertEquals(2, q.size());
		assertEquals(4, q.capacity());
		q.enqueue("C");
		assertEquals(3, q.size());
		assertEquals(4, q.capacity());
		q.enqueue("D");
		assertEquals(4, q.size());
		assertEquals(4, q.capacity());
		q.enqueue("E");
		assertEquals(5, q.size());
		assertEquals(10, q.capacity());
	}

	@Test
	void testEnqueueGrowAndConsume() {
		ArrayQueue<String> q = new ArrayQueue<>(2);
		q.enqueue("A");
		assertEquals(1, q.size());
		assertEquals(2, q.capacity());
		q.enqueue("B");
		assertEquals(2, q.size());
		assertEquals(2, q.capacity());
		assertEquals("A", q.dequeue());
		assertEquals(1, q.size());
		assertEquals(2, q.capacity());
		q.enqueue("C");
		assertEquals("B", q.peek());
		assertEquals(2, q.size());
		assertEquals(2, q.capacity());
	}

	@Test
	void testContains() {
		ArrayQueue<String> q = new ArrayQueue<>();
		assertFalse(q.contains("A"));
		q.enqueue("A");
		assertTrue(q.contains("A"));
		assertFalse(q.contains("B"));
		q.enqueue("B");
		assertTrue(q.contains("A"));
		assertTrue(q.contains("B"));
	}

	@Test
	void testClear() {
		ArrayQueue<String> q = new ArrayQueue<>();
		assertTrue(q.isEmpty());
		q.enqueue("A");
		assertFalse(q.isEmpty());
		q.clear();
		assertTrue(q.isEmpty());
	}

	@Test
	void testPeekFail() {
		ArrayQueue<String> q = new ArrayQueue<>();
		assertThrows(NoSuchElementException.class, () -> q.peek());
	}

	@Test
	void testPeekSucces() {
		ArrayQueue<String> q = new ArrayQueue<>();
		q.enqueue("A");
		assertEquals(1, q.size());
		assertEquals("A", q.peek());
		assertEquals(1, q.size());
	}

	@Test
	void testToString() {
		ArrayQueue<String> q = new ArrayQueue<>();
		q.enqueue("A");
		q.enqueue("B");
		q.enqueue("C");
		q.enqueue("D");
		q.enqueue("E");
		assertEquals("[A,B,C,D,E]", q.toString());
	}

	@Test
	void testIteratorNextFail() {
		ArrayQueue<String> q = new ArrayQueue<>();
		var i = q.iterator();
		assertThrows(NoSuchElementException.class, () -> i.next());
	}

	@Test
	void testDequeueFail() {
		ArrayQueue<String> q = new ArrayQueue<>();
		assertThrows(NoSuchElementException.class, () -> q.dequeue());
	}

	@Test
	void testDequeueSuccess() {
		ArrayQueue<String> q = new ArrayQueue<>();
		q.enqueue("A");
		assertEquals(1, q.size());
		assertEquals("A", q.dequeue());
		assertTrue(q.isEmpty());
	}

}
