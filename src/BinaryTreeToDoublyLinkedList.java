public class BinaryTreeToDoublyLinkedList {
    private static class Node {
        int value;
        Node previous; //AKA left
        Node next; //AKA right

        public Node(int value) { this.value = value; }
    }

    private static class DoublyLinkedList {
        Node head;
        Node tail;
    }

    public static Node prev = null;
    public static void main(String[] args) {
        Node root = new Node(10);
        root.previous = new Node(5);
        root.next = new Node(17);
        root.previous.previous = new Node(1);
        root.previous.next = new Node(8);
        root.next.previous = new Node(13);

        DoublyLinkedList dll = treeToDLL(root);
        printDLL(dll);
    }

    private static void printDLL(DoublyLinkedList dll) {
        if (dll.head == null) {
            System.out.println("null");
            return;
        }
        System.out.print("null <-- ");
        System.out.print(dll.head.value);
        Node curr = dll.head.next;
        while (curr != null) {
            System.out.print(" <--> " + curr.value);
            curr = curr.next;
        }
        System.out.println(" --> null");
    }

    private static DoublyLinkedList treeToDLL(Node root) {
        if (root == null) return null;
        DoublyLinkedList dll = new DoublyLinkedList();
        treeToDLL(root, dll);
        return dll;
    }

    private static void treeToDLL(Node treeCurr, DoublyLinkedList list) {
        if (treeCurr == null) return;
        list.tail = treeCurr;
        treeToDLL(treeCurr.previous, list);
        if (prev == null) list.head = treeCurr;
        else {
            treeCurr.previous = prev;
            prev.next = treeCurr;
        }
        prev = treeCurr;
        treeToDLL(treeCurr.next, list);
    }
}
