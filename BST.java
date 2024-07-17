import java.util.Scanner;

class Node {
    Node left;
    Node right;
    int data;

    Node(int data) {
        this.data = data;
        left = right = null;
    }
}

public class BST {
    static Node insert(Node root, int val) {
        if (root == null) {
            return new Node(val);
        }
        if (val < root.data) {
            root.left = insert(root.left, val);
        } else if (val > root.data) {
            root.right = insert(root.right, val);
        }
        return root;
    }

    static Node min(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    static Node max(Node root) {
        if (root == null) {
            return null;
        }
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    static Node deleteFromBST(Node root, int val) {
        if (root == null) {
            return root;
        }

        if (val < root.data) {
            root.left = deleteFromBST(root.left, val);
        } else if (val > root.data) {
            root.right = deleteFromBST(root.right, val);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                Node successor = min(root.right);
                root.data = successor.data;
                root.right = deleteFromBST(root.right, successor.data);
            }
        }
        return root;
    }

    static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    static void preorder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    static void postorder(Node root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    static Node search(Node root, int val) {
        if (root == null || root.data == val) {
            return root;
        }
        if (val < root.data) {
            return search(root.left, val);
        }
        return search(root.right, val);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Number of nodes: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Node root = null;
        for (int i = 0; i < n; i++) {
            root = insert(root, arr[i]);
        }

        System.out.println("Delete node: ");
        int dell = sc.nextInt();
        root = deleteFromBST(root, dell);

        System.out.println("Min node is: " + min(root).data);
        System.out.println("Max node is: " + max(root).data);

        System.out.print("Inorder traversal: ");
        inorder(root);
        System.out.println();
        
        System.out.print("Postorder traversal: ");
        postorder(root);
        System.out.println();
        
        System.out.print("Preorder traversal: ");
        preorder(root);
        System.out.println();

        System.out.println("Enter value to search: ");
        int data = sc.nextInt();
        if (search(root, data) != null) {
            System.out.println("Value found.");
        } else {
            System.out.println("Value not found.");
        }

        sc.close();
    }
}