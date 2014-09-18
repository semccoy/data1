package data1;

import java.util.*;

public class FiniteSet {

    interface BST {

        public int cardinality();

        public boolean isEmptyHuh();

        public boolean member(int elt);

        public BST add(int elt);

        public BST remove(int elt);

        public BST union(BST u);

        public BST inter(BST u);

        public BST diff(BST u);

        public boolean equal(BST u);

        public boolean subset(BST u);
    }

    static class EmptySet implements BST {

        EmptySet() {
        }

        public int cardinality() {
            return 0;
        }

        public boolean isEmptyHuh() {
            return true;
        }

        public boolean member(int elt) {
            return false;
        }

        public BST add(int elt) {
            return new FullSet(elt, new EmptySet(), new EmptySet());
        }

        public BST remove(int elt) {
            return new EmptySet();
        }

        public BST union(BST u) {
            return u;
        }

        public BST inter(BST u) {
            return new EmptySet();
        }

        public BST diff(BST u) {
            return u;
        }

        public boolean equal(BST u) {
            return u.isEmptyHuh();
        }

        public boolean subset(BST u) {
            return true;
        }

    }

    static class FullSet implements BST {

        int node;
        BST left;
        BST right;

        FullSet(int node, BST left, BST right) {
            this.node = node;
            this.left = left;
            this.right = right;
        }

        public BST empty() {
            return new EmptySet();
        }

        public int cardinality() {
            return left.cardinality() + 1 + right.cardinality();
        }

        public boolean isEmptyHuh() {
            return false;
        }

        public boolean member(int elt) {
            if (this.node == elt) {
                return true;
            } else if (this.node > elt) {
                return left.member(elt);
            } else {
                return right.member(elt);
            }
        }

        public BST add(int elt) {
            if (this.node == elt) {
                return this;
            } else {
                if (this.node > elt) {
                    return new FullSet(node, left.add(elt), right);
                } else {
                    return new FullSet(node, left, right.add(elt));
                }
            }
        }

        public BST union(BST u) {
            return this.left.union(u.union(right).add(this.node));
        }

        public BST remove(int elt) {
            if (node == elt) {
                return right.union(left);
            } else if (node > elt) {
                return new FullSet(node, left, right.remove(elt));
            } else {
                return new FullSet(node, left.remove(elt), right);
            }
        }

        public BST inter(BST u) {
            //if in the set, put in inter
            if (u.member(node)) {
                return new FullSet(node, this.left.inter(u), this.right.inter(u));
            } //if not in the set, remove node from inter
            else {
                return this.remove(node).inter(u);
            }
        }

        public BST diff(BST u) {
            //returns things different between existing BST and u
            //so if is a member, we want to remove it
            if (u.member(node)) {
                return this.left.union(this.right).diff(u.remove(this.node));
            } //and if not, we don't need to remove it
            else {
                return this.left.union(this.right).diff(u);
            }
        }

        public boolean subset(BST u) {
            return (left.subset(u) && u.member(node) && right.subset(u));
        }

        public boolean equal(BST u) {
            return (this.subset(u) && u.subset(this));
        }
    }

    ///// c/o ben slaw
    Random r = new Random();

    public int rInt(int low, int high) {
        return r.nextInt((high - low) + 1) + low;
    }

    public BST rBST(BST theBST, int lengthBST) {
        if (lengthBST == 0) {
            return theBST;
        } else {
            int theInt = rInt(0, 100);
            if (!theBST.member(theInt)) {
                return rBST(theBST.add(theInt), lengthBST - 1);
            } else {
                return rBST(theBST, lengthBST);
            }
        }
    }

    /////
    public static void main(String[] args) {

        /// BASIC TESTS
        BST MT = new EmptySet();
        BST t4 = new FullSet(4, MT, MT);
        BST t5 = new FullSet(5, MT, MT);
        BST t6 = new FullSet(6, MT, MT);
        BST t7 = new FullSet(7, MT, MT);
        BST t8 = new FullSet(8, MT, MT);
        BST t9 = new FullSet(9, MT, MT);
        BST t10 = new FullSet(10, MT, MT);
        BST t67 = new FullSet(7, t6, MT);
        BST t78 = new FullSet(7, MT, t8);
        BST t678 = new FullSet(7, t6, t8);
        BST t5678 = new FullSet(7, new FullSet(6, t5, MT), t8);
        BST t56789 = new FullSet(7, new FullSet(6, t5, MT), new FullSet(8, MT, t9));
        BST tlots = new FullSet(7, new FullSet(5, t4, t6), new FullSet(9, t8, t10));

        System.out.println("\n\n");
        System.out.println("cardinality:");
        System.out.println(MT.cardinality() + " should be " + 0);
        System.out.println(t5.cardinality() + " should be " + 1);
        System.out.println(t67.cardinality() + " should be " + 2);
        System.out.println(tlots.cardinality() + " should be " + 7);

        System.out.println("\n\n");
        System.out.println("isEmptyHuh:");
        System.out.println(MT.isEmptyHuh() + " should be " + true);
        System.out.println(t5.isEmptyHuh() + " should be " + false);
        System.out.println(t67.isEmptyHuh() + " should be " + false);
        System.out.println(tlots.isEmptyHuh() + " should be " + false);

        System.out.println("\n\n");
        System.out.println("member:");
        System.out.println(MT.member(5) + " should be " + false);
        System.out.println(t5.member(5) + " should be " + true);
        System.out.println(t5.member(6) + " should be " + false);
        System.out.println(t67.member(6) + " should be " + true);
        System.out.println(t67.member(7) + " should be " + true);
        System.out.println(tlots.member(10) + " should be " + true);
        System.out.println(tlots.member(11) + " should be " + false);

        System.out.println("\n\n");
        System.out.println("add:");
        System.out.println(MT.add(0).cardinality() + " should be " + 1);
        System.out.println(t5.add(5).cardinality() + " should be " + 1);
        System.out.println(t5.add(6).cardinality() + " should be " + 2);
        System.out.println(t67.add(5).cardinality() + " should be " + 3);
        System.out.println(t678.add(5).cardinality() + " should be " + 4);
        System.out.println(tlots.add(42).cardinality() + " should be " + 8);

        System.out.println("\n\n");
        System.out.println("remove:");
        System.out.println(MT.remove(42).cardinality() + " should be " + 0);
        System.out.println(t5.remove(5).cardinality() + " should be " + 0);
        System.out.println(t67.remove(6).cardinality() + " should be " + 1);
        System.out.println(t678.remove(7).cardinality() + " should be " + 2);
        System.out.println(tlots.remove(8).cardinality() + " should be " + 6);

        System.out.println("\n\n");
        System.out.println("union:");
        System.out.println(MT.union(MT).isEmptyHuh() + " should be " + true);
        System.out.println(MT.union(t678).isEmptyHuh() + " should be " + false);
        System.out.println(MT.union(t4).cardinality() + " should be " + 1);
        System.out.println(t5.union(t4).cardinality() + " should be " + 2);
        System.out.println(t6.union(t67).cardinality() + " should be " + 2);
        System.out.println(t678.union(t678).cardinality() + " should be " + 2);
        System.out.println(t5.union(t7).union(t9).cardinality() + " should be " + 3);
        System.out.println(t5.union(t9).union(t7).cardinality() + " should be " + 3);
        System.out.println(t7.union(t5).union(t9).cardinality() + " should be " + 3);
        System.out.println(t7.union(t9).union(t5).cardinality() + " should be " + 3);
        System.out.println(t9.union(t5).union(t7).cardinality() + " should be " + 3);
        System.out.println(t9.union(t7).union(t5).cardinality() + " should be " + 3);

        System.out.println("\n\n");
        System.out.println("inter:");
        System.out.println(MT.inter(MT).cardinality() + " should be " + 0);
        System.out.println(MT.inter(t5).cardinality() + " should be " + 0);
        System.out.println(t5.inter(t4).cardinality() + " should be " + 0);
        System.out.println(t5.inter(t5).cardinality() + " should be " + 1);
        System.out.println(t678.inter(t6).cardinality() + " should be " + 1);
        System.out.println(t6.inter(MT).cardinality() + " should be " + 0);
        System.out.println(tlots.inter(t678).cardinality() + " should be " + 3);
        System.out.println("\n\n");

        //not right, see above declaration
        System.out.println("diff:");
        System.out.println(MT.diff(MT).cardinality() + " should be " + 0);
        System.out.println(MT.diff(t7).cardinality() + " should be " + 1);
        System.out.println(t7.diff(MT).cardinality() + " should be " + 0);
        System.out.println(t7.diff(t6).cardinality() + " should be " + 1);
        System.out.println(t5.diff(t5).cardinality() + " should be " + 0);
        System.out.println(t67.diff(t78).cardinality() + " should be " + 1);
        System.out.println(t678.diff(t67).cardinality() + " should be " + 0);
        System.out.println(t6.diff(t678).cardinality() + " should be " + 3);
        System.out.println(t67.diff(t678).cardinality() + " should be " + 1);
        System.out.println(tlots.diff(t8).cardinality() + " should be " + 0);
        System.out.println(t8.diff(tlots).cardinality() + " should be " + 7);
        System.out.println("\n\n");

        System.out.println("equal:");
        System.out.println(MT.equal(MT) + " should be " + true);
        System.out.println(t5.equal(t4) + " should be " + false);
        System.out.println(t6.equal(t6) + " should be " + true);
        System.out.println(t78.equal(t67) + " should be " + false);
        System.out.println(t678.equal(tlots) + " should be " + false);
        System.out.println(t678.equal(t678) + " should be " + true);
        System.out.println("\n\n");

        System.out.println("subset:");
        System.out.println(MT.subset(MT) + " should be " + true);
        System.out.println(t7.subset(MT) + " should be " + false);
        System.out.println(t7.subset(t7) + " should be " + true);
        System.out.println(t67.subset(t6) + " should be " + false);
        System.out.println(t678.subset(t6) + " should be " + false);
        System.out.println(t6.subset(t678) + " should be " + true);
        System.out.println(tlots.subset(t678) + " should be " + false);
        System.out.println("\n\n");

    }

}
