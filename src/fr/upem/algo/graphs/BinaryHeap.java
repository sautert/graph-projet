package fr.upem.algo.graphs;

public class BinaryHeap {
	private final int[] heap;
	private final int maxSize;
	private int size;

	public BinaryHeap(int maxSize) {
		size = 0;
		this.maxSize = maxSize;
		this.heap = new int[this.maxSize + 1];
		heap[0] = Integer.MIN_VALUE;
	}

	private void swap(int posOne, int posTwo) 
	{
		int tmp;
		tmp = heap[posOne];
		heap[posOne] = heap[posTwo];
		heap[posTwo] = tmp;
	}

	private int parent(int node) {
		return (node / 2);
	}

	private int leftChild(int node) {
		return (node * 2);
	}

	private int rightChild(int node) {
		return (node * 2 + 1);
	}

	public void insert(int elem) {
		heap[++size] = elem;
		elem = size;
		while (heap[elem] < heap[parent(elem)]) {
			swap(elem, parent(elem));
			elem = parent(elem);
		}
	}

	private boolean leaf(int pos) {
		return (pos >= (size / 2) && pos <= size);
	}

	private void integrity(int pos) {
		if (!leaf(pos)) {
			if (heap[pos] > heap[leftChild(pos)] || heap[pos] > heap[rightChild(pos)]) {
				if (heap[leftChild(pos)] < heap[rightChild(pos)]) {
					swap(pos, leftChild(pos));
					integrity(leftChild(pos));
				} else {
					swap(pos, rightChild(pos));
					integrity(rightChild(pos));
				}
			}
		}
	}

	public int get() {
		int min = heap[1];
		heap[1] = heap[size--];
		integrity(1);
		return (min);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= size; i++) {
			sb.append(heap[i]).append(" ");
		}
		return sb.toString();
	}

	public static void main(String args[]) {
		var tab = new BinaryHeap(1024);
		tab.insert(10);
		tab.insert(8);
		tab.insert(10);
		tab.insert(30);
		tab.insert(20);
		tab.insert(400);
		tab.insert(150);
		tab.insert(160);
		tab.insert(12);
		tab.insert(18);
		System.out.println(tab.toString());
		tab.get();
		System.out.println(tab.toString());
		tab.get();
		System.out.println(tab.toString());
		tab.get();
		System.out.println(tab.toString());
		tab.get();
		System.out.println(tab.toString());
		tab.get();
		System.out.println(tab.toString());
		tab.get();
		System.out.println(tab.toString());
	}
}
