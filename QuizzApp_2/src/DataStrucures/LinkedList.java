package DataStrucures;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * A generic implementation of a singly linked list.
 *
 * @param <E> the type of elements in this list
 */
public class LinkedList<E> {
    private Node<E> first;
    private int size;

    /**
     * Inner class representing a node in the linked list.
     *
     * @param <E> the type of element held in this node
     */
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    // Constructor to initialize an empty linked list
    public LinkedList() {
        first = null;
        size = 0;
    }

    // Constructor that takes a collection and adds its elements to the linked list
    public LinkedList(Collection<E> collection) {
        this.first = null;
        if (collection != null && !collection.isEmpty()) {
            Iterator<E> iterator = collection.iterator();
            while (iterator.hasNext()) {
                add(iterator.next());
            }
        }
    }

    // Add an element to the end of the linked list
    public void add(E data) {
        Node<E> newNode = new Node<>(data);
        if (first == null) {
            first = newNode;
        } else {
            Node<E> current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // Add an element to the beginning of the linked list
    public void addFirst(E data) {
        Node<E> newNode = new Node<>(data);
        newNode.next = first;
        first = newNode;
        size++;
    }

    // Remove the first occurrence of an element from the linked list
    public void remove(E data) {
        if (first == null) {
            throw new NoSuchElementException("List is empty");
        }
        if (first.data.equals(data)) {
            first = first.next;
            size--;
            return;
        }
        Node<E> current = first;
        while (current.next != null) {
            if (current.next.data.equals(data)) {
                current.next = current.next.next;
                size--;
                return;
            }
            current = current.next;
        }
        throw new NoSuchElementException("Element not found in the list");
    }

    // Method to remove an element at a specified index
    public void removeAtIndex(int index) {
        if (index < 0 || first == null) {
            return; // Invalid index or empty list
        }
        if (index == 0) {
            first = first.next;
            return;
        }
        Node<E> current = first;
        int currentIndex = 0;
        while (current != null && currentIndex < index - 1) {
            current = current.next;
            currentIndex++;
        }
        if (current == null || current.next == null) {
            return; // Index out of bounds
        }
        current.next = current.next.next;
    }

    // Get the size of the linked list
    public int size() {
        return size;
    }

    // Get the value at a specific index
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<E> current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    // Display the linked list
    public void display() {
        Node<E> current = first;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
    /*
     * Approch - 1
     * Convert the linked list into an array.
     * Shuffle the array.
     * Reconstruct the linked list from the shuffled array.
     */

    /**
     * 
     * Approch- 2
     * Find the middle of the linked list.
     * Split the linked list into two halves.
     * Reverse the second half of the linked list.
     * Merge the two halves while randomly choosing elements from each half.
     * 
     */
    public void shuffle() {
        if (first == null || first.next == null) {
            return; // Nothing to shuffle for empty or single-element list
        }

        // Step 1: Find the middle of the linked list
        Node<E> slow = first;
        Node<E> fast = first;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Split the list into two halves
        Node<E> firstHalf = first;
        Node<E> secondHalf = slow.next;
        slow.next = null;

        // Step 3: Reverse the second half of the linked list
        secondHalf = reverse(secondHalf);

        // Step 4: Merge the two halves randomly
        Random random = new Random();
        Node<E> dummy = new Node<>(null);
        Node<E> current = dummy;

        while (firstHalf != null || secondHalf != null) {
            if (firstHalf != null && (secondHalf == null || random.nextBoolean())) {
                current.next = firstHalf;
                firstHalf = firstHalf.next;
            } else {
                current.next = secondHalf;
                secondHalf = secondHalf.next;
            }
            current = current.next;
        }

        // Update the head to the shuffled list
        first = dummy.next;
    }

    private Node<E> reverse(Node<E> node) {
        Node<E> prev = null;
        while (node != null) {
            Node<E> next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> current = first;
        while (current != null) {
            sb.append(current.data).append(" ");
            current = current.next;
        }
        return sb.toString().trim();
    }

}