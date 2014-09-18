package data1;

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
            return u.isEmptyHuh();
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
            if (left.member(elt) || node == elt || right.member(elt)) {
                return true;
            } else {
                return false;
            }

        }

    }
    
    ////

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
        return u.isEmptyHuh();
    }

}

public static void main(String[] args) {
        System.out.println("test post please ignore");

    }

}


/*
 Your finite sets should support the following operations:

 procedure
 (empty) → finite-set
 Returns a fresh empty set.


 procedure
 (cardinality t) → integer
 t : finite-set
 Returns the number of elements in t.


 procedure
 (isEmptyHuh t) → boolean
 t : finite-set
 Determines if t is empty.


 procedure
 (member t elt) → boolean
 t : finite-set
 elt : integer
 Determines if elt is in t.


 procedure
 (add t elt) → finite-set
 t : finite-set
 elt : integer
 Returns a set containing elt and everything in t.


 procedure
 (remove t elt) → finite-set
 t : finite-set
 elt : integer
 Returns a set containing everything in t except elt.
 procedure
 (union t u) → finite-set
 t : finite-set
 u : finite-set
 Returns a set containing everything in t and u.
 procedure
 (inter t u) → finite-set
 t : finite-set
 u : finite-set
 Returns a set containing everything that is in both t and u.
 procedure
 (diff t u) → finite-set
 t : finite-set
 u : finite-set
 Returns a set containing everything in u except those that are in t.
 procedure
 (equal t u) → boolean
 t : finite-set
 u : finite-set
 Determines if t and u contain the same elements.
 procedure
 (subset t u) → boolean
 t : finite-set
 u : finite-set
 */
