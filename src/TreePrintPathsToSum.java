import java.util.ArrayList;
import java.util.List;

public class TreePrintPathsToSum {
    private static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        TreeNode tree1 = makeTree1();
        printPaths(tree1, 10);
        TreeNode tree2 = makeTree2();
        printPaths(tree2, 2);
    }

    private static TreeNode makeTree1() {
        TreeNode tree1 = new TreeNode(5);
        tree1.left = new TreeNode(6);
        tree1.left.left = new TreeNode(-1);
        tree1.left.right = new TreeNode(-11);
        tree1.left.left.left = new TreeNode(6);
        tree1.left.right.left = new TreeNode(10);
        tree1.right = new TreeNode(8);
        tree1.right.right = new TreeNode(13);
        tree1.right.right.right = new TreeNode(-13);
        tree1.right.right.right.left = new TreeNode(-3);
        tree1.right.right.right.left.right = new TreeNode(4);
        return tree1;
    }

    private static TreeNode makeTree2() {
        TreeNode tree2 = new TreeNode(2);
        tree2.right = new TreeNode(-2);
        tree2.right.right = new TreeNode(2);
        tree2.right.right.right = new TreeNode(0);
        tree2.right.right.right.right = new TreeNode(2);
        tree2.right.right.right.right.right = new TreeNode(0);
        tree2.right.right.right.right.right.right = new TreeNode(-2);
        tree2.right.right.right.right.right.right.right = new TreeNode(-2);
        tree2.right.right.right.right.right.right.right.right = new TreeNode(0);
        tree2.right.right.right.right.right.right.right.right.right = new TreeNode(2);
        return tree2;
    }

    public static void printPaths(TreeNode root, int sum) {
        printPathsRecurse(root, sum, new ArrayList<>());
        System.out.println("-----------------------------------------------------------------");
    }

    private static void printPathsRecurse(TreeNode curr, int sum, List<TreeNode> chain) {
        if (curr == null) {
            return;
        }
        chain.add(curr);
        walkUp(sum, chain);
        if (curr.left != null) printPathsRecurse(curr.left, sum, chain);
        if (curr.right!= null) printPathsRecurse(curr.right, sum, chain);
        if (!chain.isEmpty()) chain.remove(chain.size() - 1);
    }

    private static void walkUp(int sum, List<TreeNode> chain) {
        int i = chain.size()-1;
        int currSum = 0;
        while (i >= 0) {
            currSum += chain.get(i).value;
            if (currSum == sum) printPath(chain, i, chain.size()-1);
            i--;
        }
    }

    private static void printPath(List<TreeNode> chain, int start, int end) {
        System.out.print(Integer.toString(chain.get(start).value));
        for (int i = start+1; i <= end; i++) {
            System.out.print(", " + chain.get(i).value);
        }
        System.out.println();
    }
}
