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

        public static BST empty() {
            return new EmptySet();
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

        public static BST empty() {
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

    /* Random Stuff */
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static BST createBST(int size) {
        if (size == 0) {
            return FullSet.empty();
        } else {
            return createBST(size - 1).add(randInt(0, 50));
        }
    }

    /* Testing */
    //
    //all operations checked at least once (most twice or more)
    //
    //fyi, i didn't check empty() anywhere since i feel like you can
    //check isEmptyHuh() and get the same result...
    //
    //
    //
    //if cardinality is 0, u must be empty; if not, u must not be empty
    public static void isEmptyHuhCardCheck(BST u) {
        if (u.cardinality() == 0) {
            if (u.isEmptyHuh()) {
                System.out.println("Success - isEmptyHuhCardCheck");
            } else {
                System.out.println("Failure - isEmptyHuhCardCheck");
            }
        } else if (!u.isEmptyHuh()) {
            System.out.println("Success - isEmptyHuhCardCheck");
        } else {
            System.out.println("Failure - isEmptyHuhCardCheck");
        }
    }

    //adding things makes cardinality go up by however many things were added
    public static void cardAddCheck(int elt) {
        BST tree = new EmptySet();
        for (int i = 0; i < elt; i++) {
            tree = tree.add(i);
        }
        if (tree.cardinality() == elt) {
            System.out.println("Success - cardAddCheck");
        } else {
            System.out.println("Failure - cardAddCheck");
        }
    }

    //removing an elt from u results in a <= cardinality
    public static void removeCardCheck(BST u) {
        int elt = randInt(0, 100);
        BST smaller = u.remove(elt);
        if (smaller.cardinality() <= u.cardinality()) {
            System.out.println("Success - removeCardCheck");
        } else {
            System.out.println("Failure - removeCardCheck");
        }
    }

    //if some number is not in one set but in another that's just
    //the first set + the number, adding things to sets works properly!
    //note that x MUST be outside the range of possible values of u
    //else may fail
    public static void addMemberCheck(BST u) {
        int x = randInt(4200, 4300);
        BST uPlus = u.add(x);
        if (!u.member(x) && uPlus.member(x)) {
            System.out.println("Success - addMemberCheck");
        } else {
            System.out.println("Failure - addMemberCheck");
        }
    }

    //unions of (sub)sets are transitive
    public static void unionSubsetCheck(BST u, BST v, BST w) {
        if ((u.union(v)).subset(w)
                == (u.subset(w) && v.subset(w))) {
            System.out.println("Success - unionSubsetCheck");
        } else {
            System.out.println("Failure - unionSubsetCheck");
        }
    }

    //adding things to sets is transitive
    public static void unionMemberCheck(BST u, BST v) {
        int elt = randInt(0, 100);
        BST s1 = u.add(elt);
        BST s2 = v.add(elt);
        BST x1 = (s1.union(v));
        BST x2 = (s2.union(u));
        if (x1.member(elt) && x2.member(elt)) {
            System.out.println("Success - unionMemberCheck");
        } else {
            System.out.println("Failure - unionMemberCheck");
        }
    }

    //the intersection of 2 sets cannot be empty if 
    //both of those 2 sets contain the thing
    public static void memberInterCheck(BST u, BST v, int elt) {
        u = u.add(elt);
        v = v.add(elt);
        if (u.member(elt) && v.member(elt)) {
            if (!u.inter(v).isEmptyHuh()) {
                System.out.println("Success - memberInterCheck");
            } else {
                System.out.println("Failure - memberInterCheck");
            }
        } else {
            System.out.println("Can't touch this");
        }
    }

    //four things:
    //1 - if the difference between 2 sets is empty, they are equal
    //2 - if 2 sets are different, they are not equal
    //3 - and vice versa
    //4 -  " " 
    public static void diffEqualCheck(BST u, BST v) {
        if ((u.diff(v)).isEmptyHuh() && (v.diff(u)).isEmptyHuh()) {
            if (u.equal(v)) {
                System.out.println("Success - diffEqualCheck pt1");
            } else {
                System.out.println("Failure - diffEqualCheck pt1");
            }
        } else if (!u.equal(v)) {
            System.out.println("Success - diffEqualCheck pt1.2");
        } else {
            System.out.println("Failure - diffEqualCheck pt1.2");
        }
        //vice versa
        if (u.equal(v)) {
            if (u.diff(v).isEmptyHuh() && v.diff(u).isEmptyHuh()) {
                System.out.println("Success - diffEqualCheck pt2");
            } else {
                System.out.println("Failure - diffEqualCheck pt2");
            }
        } else if (!u.diff(v).isEmptyHuh()
                || !v.diff(u).isEmptyHuh()) {
            System.out.println("Success - diffEqualCheck pt2.2");
        } else {
            System.out.println("Failure - diffEqualCheck pt2.2");
        }
    }

    //add one thing that is to u and v that is either
    //already within or outside of both of them to being with, then if:
    //unioning them = u + v + elt,
    //removing elt from them = u + v,
    //interesting them = elt,
    //unioning elt and u + v = u + v + elt
    //and the union of the intersects of the original sets = u + v + elt,
    //then all the u + v + elt sets are equal
    public static void addUnionRemoveInterEqualCheck(BST u, BST v) {
        int elt = randInt(0, 200);
        BST u2 = u.add(elt);
        BST v2 = v.add(elt);
        BST unionSet = u2.union(v2); // u + v + elt
        BST removeSet = unionSet.remove(elt); // u + v
        BST interSet = u2.inter(v2); // elt
        BST connectSet = removeSet.union(interSet); // u + v + elt
        BST uiSet = interSet.union(u).union(v); // u + v + elt
        if (unionSet.equal(uiSet) && unionSet.equal(connectSet)) { // all equal
            System.out.println("Success - addUnionRemoveInterEqualCheck");
        } else {
            System.out.println("Failure - addUnionRemoveInterEqualCheck");
        }
    }

    public static void main(String[] args) {

        System.out.println("random int checker - " + randInt(0, 100) + " " + randInt(0, 100) + " " + randInt(0, 100));

        BST mt = new EmptySet();
        BST bst0 = createBST(1);
        BST bst6 = createBST(7);

        int adNauseum = 10; //repeats all tests this many times
        for (int i = 0; i < adNauseum; i++) {

            System.out.println("\nemptyCardCheck:");
            isEmptyHuhCardCheck(mt);
            isEmptyHuhCardCheck(bst0);
            isEmptyHuhCardCheck(bst6);
            isEmptyHuhCardCheck(createBST(randInt(0, 10)));
            isEmptyHuhCardCheck(createBST(randInt(0, 10)));
            isEmptyHuhCardCheck(createBST(randInt(0, 10)));

            System.out.println("\ncardAddCheck:");
            cardAddCheck(0);
            cardAddCheck(5);
            cardAddCheck(randInt(0, 100));
            cardAddCheck(randInt(0, 100));
            cardAddCheck(randInt(0, 100));

            System.out.println("\nremoveCardCheck:");
            removeCardCheck(createBST(randInt(0, 10)));
            removeCardCheck(createBST(randInt(0, 10)));
            removeCardCheck(createBST(randInt(0, 10)));

            System.out.println("\naddMemberCheck:");
            addMemberCheck(createBST(randInt(0, 10)));
            addMemberCheck(createBST(randInt(0, 10)));
            addMemberCheck(createBST(randInt(0, 10)));

            System.out.println("\nunionSubsetCheck:");
            unionSubsetCheck(createBST(randInt(0, 10)), createBST(randInt(0, 10)), createBST(randInt(0, 10)));
            unionSubsetCheck(createBST(randInt(0, 10)), createBST(randInt(0, 10)), createBST(randInt(0, 10)));
            unionSubsetCheck(createBST(randInt(0, 10)), createBST(randInt(0, 10)), createBST(randInt(0, 10)));

            System.out.println("\nunionMemberCheck:");
            unionMemberCheck(createBST(randInt(0, 10)), createBST(randInt(0, 10)));
            unionMemberCheck(createBST(randInt(0, 10)), createBST(randInt(0, 10)));
            unionMemberCheck(createBST(randInt(0, 10)), createBST(randInt(0, 10)));

            System.out.println("\nmemberInterCheck:");
            memberInterCheck(createBST(randInt(0, 10)), createBST(randInt(0, 10)), randInt(0, 100));
            memberInterCheck(createBST(randInt(0, 10)), createBST(randInt(0, 10)), randInt(0, 100));
            memberInterCheck(createBST(randInt(0, 10)), createBST(randInt(0, 10)), randInt(0, 100));

            System.out.println("\ndiffEqualCheck:");
            diffEqualCheck(createBST(randInt(0, 10)), createBST(randInt(0, 10)));
            diffEqualCheck(createBST(randInt(0, 10)), createBST(randInt(0, 10)));
            diffEqualCheck(createBST(randInt(0, 10)), createBST(randInt(0, 10)));

            System.out.println("\naddUnionRemoveInterEqualCheck:");
            addUnionRemoveInterEqualCheck(createBST(randInt(0, 10)), createBST(randInt(0, 10)));
            addUnionRemoveInterEqualCheck(createBST(randInt(0, 10)), createBST(randInt(0, 10)));
            addUnionRemoveInterEqualCheck(createBST(randInt(0, 10)), createBST(randInt(0, 10)));

        }
    }
}
