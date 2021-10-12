package com.ssm.tree.avlTree;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/10/12 18:35
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        //  int [] arr = {4,3,6,5,7,8 };
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        avlTree.infixOrder();
        System.out.println(avlTree.getRoot().height());
        System.out.println(avlTree.getRoot().leftHeight());
        System.out.println(avlTree.getRoot().rightHeight());

    }
}

class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    //查找要删除的结点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父结点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * @param node 传入的结点(当做二叉排序树的根结点)
     * @return 返回的 以node 为根结点的二叉排序树的最小结点值
     * 删除node 为根结点的二叉排序树的最小结点
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环查找左子结点,就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        //删除最小结点
        delNode(target.value);
        return target.value;
    }

    //删除结点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            Node parent = searchParent(value);
            //要删除的结点是叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                if (parent.left != null && parent.left.value == value) {  //是左子结点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
                //要删除的结点有两颗子树
            } else if (targetNode.left != null && targetNode.right != null) {
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else { //只有一颗子树的结点
                if (targetNode.left != null) {
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }

                }
            }
        }

    }

    //添加结点
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空！不能遍历");
        }
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    //返回以该结点为根结点的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转方法
    private void leftRotate() {
        //创建新结点,以当前结点的值
        Node newNode = new Node(value);
        //把新的结点的左子树设置成当前结点的左子树
        newNode.left = left;
        //把新的结点的右子树设置成当前结点的右子树的左子树
        newNode.right = right.left;
        //把当前结点的值替换成右子结点的值
        value = right.value;
        //把当前结点的右子树设置成当前结点右子树的右子树
        right = right.right;
        //把当前结点的左子树(结点)设置成新的结点
        left = newNode;
    }

    //右旋转方法
    private void rightRotate() {
        Node newNode = new Node(value);
        //新结点的右子树设置成当前结点的右子树
        newNode.right = right;
        //新结点的左子树设置成当前结点的左子树的右子树
        newNode.left = left.right;
        //当前结点的值换为左子结点的值
        value = left.value;
        //当前结点的左子树设置成左子树的左子树
        left = left.left;
        ///当前结点的右子树设置为新结点
        right = newNode;

    }

    /**
     * 查找要删除的节点
     *
     * @param value 希望删除的结点的值
     * @return 如果找到返回该结点，否则返回null
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除节点的父结点
     *
     * @param value 要找到结点的值
     * @return 返回要删除节点的父结点，如果没有返回nul
     */
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }


    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //添加结点
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
        // 1.当符合右旋转的条件时
        // 2.如果它的左子树的的右子树高度大于它的左子树的高度
        // 3.先对当前这个结点的左节点进行左旋转
        // 4.再对当前结点进行右旋转的操作即可
        if (rightHeight() - leftHeight() > 1) { //左旋转
            //如果它的右子树的左子树的高度大于它的右子树的右子树的高度
            if (right != null && right.leftHeight() > right.rightHeight()) {
                //先对右子结点进行右转移
                right.rightRotate();
                //然后再对当前结点进行左转移
                leftRotate();
            } else {
                leftRotate();

            }
            return;
        }
        if (leftHeight() - rightHeight() > 1) { //右旋转
            if (left != null && left.rightHeight() > left.leftHeight()) {
                //先对当前这个结点的左节点进行左旋转
                left.leftRotate();
                rightRotate();
            } else {
                rightRotate();
            }

        }
    }

    //编写中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

}