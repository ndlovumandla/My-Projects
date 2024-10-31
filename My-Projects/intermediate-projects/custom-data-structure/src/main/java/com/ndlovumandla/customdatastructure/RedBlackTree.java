package com.ndlovumandla.customdatastructure;

public class RedBlackTree {
    private class Node {
        int data;
        Node left, right, parent;
        boolean color; // true for red, false for black

        Node(int data) {
            this.data = data;
            left = right = parent = null;
            color = true; // new node is red by default
        }
    }

    private Node root, TNULL;

    // Preorder
    private void preOrderHelper(Node node) {
        if (node != TNULL) {
            System.out.print(node.data + " ");
            preOrderHelper(node.left);
            preOrderHelper(node.right);
        }
    }

    private void balanceInsert(Node k) {
        Node u;
        while (k.parent.color) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left; // uncle
                if (u.color) {
                    // case 3.1
                    u.color = false;
                    k.parent.color = false;
                    k.parent.parent.color = true;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        // case 3.2.2
                        k = k.parent;
                        rotateRight(k);
                    }
                    // case 3.2.1
                    k.parent.color = false;
                    k.parent.parent.color = true;
                    rotateLeft(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right;

                if (u.color) {
                    // mirror case 3.1
                    u.color = false;
                    k.parent.color = false;
                    k.parent.parent.color = true;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        // mirror case 3.2.2
                        k = k.parent;
                        rotateLeft(k);
                    }
                    // mirror case 3.2.1
                    k.parent.color = false;
                    k.parent.parent.color = true;
                    rotateRight(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = false;
    }

    private void rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != TNULL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    private void rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    public RedBlackTree() {
        TNULL = new Node(0);
        TNULL.color = false; // black
        root = TNULL;
    }

    public void insert(int key) {
        Node node = new Node(key);
        node.parent = null;
        node.left = TNULL;
        node.right = TNULL;

        Node y = null;
        Node x = this.root;

        while (x != TNULL) {
            y = x;
            if (node.data < x.data) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.data < y.data) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null) {
            node.color = false;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        balanceInsert(node);
    }

    public boolean search(int key) {
        return search(root, key);
    }

    private boolean search(Node node, int key) {
        if (node == TNULL) {
            return false;
        }
        if (key == node.data) {
            return true;
        }
        return key < node.data ? search(node.left, key) : search(node.right, key);
    }

    public void delete(int key) {
        deleteNode(this.root, key);
    }

    private void deleteNode(Node node, int key) {
        Node z = TNULL;
        Node x, y;
        while (node != TNULL) {
            if (node.data == key) {
                z = node;
            }

            if (node.data <= key) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (z == TNULL) {
            System.out.println("Couldn't find key in the tree");
            return;
        }

        y = z;
        boolean yOriginalColor = y.color;
        if (z.left == TNULL) {
            x = z.right;
            rbTransplant(z, z.right);
        } else if (z.right == TNULL) {
            x = z.left;
            rbTransplant(z, z.left);
        } else {
            y = minValueNode(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                rbTransplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            rbTransplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        z = null;
        if (yOriginalColor == false) {
            fixDelete(x);
        }
    }

    private void rbTransplant(Node u, Node v) {
        if (u.parent == null) {
            this.root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    private void fixDelete(Node x) {
        Node s;
        while (x != root && x.color == false) {
            if (x == x.parent.left) {
                s = x.parent.right;
                if (s.color) {
                    s.color = false;
                    x.parent.color = true;
                    rotateLeft(x.parent);
                    s = x.parent.right;
                }

                if (s.left.color == false && s.right.color == false) {
                    s.color = true;
                    x = x.parent;
                } else {
                    if (s.right.color == false) {
                        s.left.color = false;
                        s.color = true;
                        rotateRight(s);
                        s = x.parent.right;
                    }

                    s.color = x.parent.color;
                    x.parent.color = false;
                    s.right.color = false;
                    rotateLeft(x.parent);
                    x = root;
                }
            } else {
                s = x.parent.left;

                if (s.color) {
                    s.color = false;
                    x.parent.color = true;
                    rotateRight(x.parent);
                    s = x.parent.left;
                }

                if (s.right.color == false && s.left.color == false) {
                    s.color = true;
                    x = x.parent;
                } else {
                    if (s.left.color == false) {
                        s.right.color = false;
                        s.color = true;
                        rotateLeft(s);
                        s = x.parent.left;
                    }

                    s.color = x.parent.color;
                    x.parent.color = false;
                    s.left.color = false;
                    rotateRight(x.parent);
                    x = root;
                }
            }
        }
        x.color = false;
    }

    private Node minValueNode(Node node) {
        while (node.left != TNULL) {
            node = node.left;
        }
        return node;
    }

    public boolean validate() {
        return validate(root);
    }

    private boolean validate(Node node) {
        if (node == TNULL) {
            return true;
        }

        if (node.left != TNULL && node.left.data > node.data) {
            return false;
        }
        if (node.right != TNULL && node.right.data < node.data) {
            return false;
        }
        
        return validate(node.left) && validate(node.right);
    }
}
