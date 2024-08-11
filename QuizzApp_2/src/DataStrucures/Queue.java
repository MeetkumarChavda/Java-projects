package DataStrucures;

class Queue {

    int rear;
    int front;
    int size;
    int[] queueArray;
    private String line = "\u001B[31m|\u001B[0m";

    Queue(int size) {
        queueArray = new int[size];
        this.size = size;
        rear = -1;
        front = -1;
    }

    void Enqueue(int data) {
        if (rear < size - 1) {
            if (front == -1) {
                front = 0;
            }
            queueArray[++rear] = data;// here top is incremneted first means first rear<--rear+1; get exicuted
            System.out.printf("Value %d added to the Queue \n", data);
        } else {
            System.out.println("OverFlow");
        }

    }

    void Dequeue() {

        if (front == -1) {
            System.out.println("UnderFlow !");
        } else {
            if (front == rear) {
                front = rear = -1;
            } else {
                try {
                    System.out.printf("Element %d is Deleted \n", queueArray[front]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                front++;

            }

        }

    }

    int getFront() {
        if (front == -1) {
            System.out.println("Empty ! front at -1");
            return -1;
        }
        return queueArray[front];
    }

    int getRear() {
        if (rear == -1) {
            System.out.println("Empty ! rear at -1");
            return -1;
        }
        return queueArray[rear];
    }

    String aro = "\u001b[32m-->\u001b[0m";

    void display() {
        if (front == -1) {
            System.out.println("Empty Queue !");
        } else {
            for (int i = front; i <= rear; i++) {
                System.out.print(line + " " + queueArray[i] + " " + line + aro);
            }
            System.out.println();
        }
    }
}

// For Testing
/*
 * public static void main(String[] args) {
 * Queue q = new Queue(11);
 * for (int i = 0; i < 10; i++) {
 * q.Enqueue(i);
 * }
 * q.display();
 * }
 */
