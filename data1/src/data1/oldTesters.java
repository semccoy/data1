//  BASIC TESTS - old, can ignore
//        BST MT = new EmptySet();
//        BST t4 = new FullSet(4, MT, MT);
//        BST t5 = new FullSet(5, MT, MT);
//        BST t6 = new FullSet(6, MT, MT);
//        BST t7 = new FullSet(7, MT, MT);
//        BST t8 = new FullSet(8, MT, MT);
//        BST t9 = new FullSet(9, MT, MT);
//        BST t10 = new FullSet(10, MT, MT);
//        BST t67 = new FullSet(7, t6, MT);
//        BST t78 = new FullSet(7, MT, t8);
//        BST t678 = new FullSet(7, t6, t8);
//        BST t5678 = new FullSet(7, new FullSet(6, t5, MT), t8);
//        BST t56789 = new FullSet(7, new FullSet(6, t5, MT), new FullSet(8, MT, t9));
//        BST tlots = new FullSet(7, new FullSet(5, t4, t6), new FullSet(9, t8, t10));
//
//
//
//        System.out.println("\n\n");
//        System.out.println("cardinality:");
//        System.out.println(MT.cardinality() + " should be " + 0);
//        System.out.println(t5.cardinality() + " should be " + 1);
//        System.out.println(t67.cardinality() + " should be " + 2);
//        System.out.println(tlots.cardinality() + " should be " + 7);
//
//        System.out.println("\n\n");
//        System.out.println("isEmptyHuh:");
//        System.out.println(MT.isEmptyHuh() + " should be " + true);
//        System.out.println(t5.isEmptyHuh() + " should be " + false);
//        System.out.println(t67.isEmptyHuh() + " should be " + false);
//        System.out.println(tlots.isEmptyHuh() + " should be " + false);
//
//        System.out.println("\n\n");
//        System.out.println("member:");
//        System.out.println(MT.member(5) + " should be " + false);
//        System.out.println(t5.member(5) + " should be " + true);
//        System.out.println(t5.member(6) + " should be " + false);
//        System.out.println(t67.member(6) + " should be " + true);
//        System.out.println(t67.member(7) + " should be " + true);
//        System.out.println(tlots.member(10) + " should be " + true);
//        System.out.println(tlots.member(11) + " should be " + false);
//
//        System.out.println("\n\n");
//        System.out.println("add:");
//        System.out.println(MT.add(0).cardinality() + " should be " + 1);
//        System.out.println(t5.add(5).cardinality() + " should be " + 1);
//        System.out.println(t5.add(6).cardinality() + " should be " + 2);
//        System.out.println(t67.add(5).cardinality() + " should be " + 3);
//        System.out.println(t678.add(5).cardinality() + " should be " + 4);
//        System.out.println(tlots.add(42).cardinality() + " should be " + 8);
//
//        System.out.println("\n\n");
//        System.out.println("remove:");
//        System.out.println(MT.remove(42).cardinality() + " should be " + 0);
//        System.out.println(t5.remove(5).cardinality() + " should be " + 0);
//        System.out.println(t67.remove(6).cardinality() + " should be " + 1);
//        System.out.println(t678.remove(7).cardinality() + " should be " + 2);
//        System.out.println(tlots.remove(8).cardinality() + " should be " + 6);
//
//        System.out.println("\n\n");
//        System.out.println("union:");
//        System.out.println(MT.union(MT).isEmptyHuh() + " should be " + true);
//        System.out.println(MT.union(t678).isEmptyHuh() + " should be " + false);
//        System.out.println(MT.union(t4).cardinality() + " should be " + 1);
//        System.out.println(t5.union(t4).cardinality() + " should be " + 2);
//        System.out.println(t6.union(t67).cardinality() + " should be " + 2);
//        System.out.println(t678.union(t678).cardinality() + " should be " + 2);
//        System.out.println(t5.union(t7).union(t9).cardinality() + " should be " + 3);
//        System.out.println(t5.union(t9).union(t7).cardinality() + " should be " + 3);
//        System.out.println(t7.union(t5).union(t9).cardinality() + " should be " + 3);
//        System.out.println(t7.union(t9).union(t5).cardinality() + " should be " + 3);
//        System.out.println(t9.union(t5).union(t7).cardinality() + " should be " + 3);
//        System.out.println(t9.union(t7).union(t5).cardinality() + " should be " + 3);
//
//        System.out.println("\n\n");
//        System.out.println("inter:");
//        System.out.println(MT.inter(MT).cardinality() + " should be " + 0);
//        System.out.println(MT.inter(t5).cardinality() + " should be " + 0);
//        System.out.println(t5.inter(t4).cardinality() + " should be " + 0);
//        System.out.println(t5.inter(t5).cardinality() + " should be " + 1);
//        System.out.println(t678.inter(t6).cardinality() + " should be " + 1);
//        System.out.println(t6.inter(MT).cardinality() + " should be " + 0);
//        System.out.println(tlots.inter(t678).cardinality() + " should be " + 3);
//        System.out.println("\n\n");
//
//        //not right, see above declaration
//        System.out.println("diff:");
//        System.out.println(MT.diff(MT).cardinality() + " should be " + 0);
//        System.out.println(MT.diff(t7).cardinality() + " should be " + 1);
//        System.out.println(t7.diff(MT).cardinality() + " should be " + 0);
//        System.out.println(t7.diff(t6).cardinality() + " should be " + 1);
//        System.out.println(t5.diff(t5).cardinality() + " should be " + 0);
//        System.out.println(t67.diff(t78).cardinality() + " should be " + 1);
//        System.out.println(t678.diff(t67).cardinality() + " should be " + 0);
//        System.out.println(t6.diff(t678).cardinality() + " should be " + 3);
//        System.out.println(t67.diff(t678).cardinality() + " should be " + 1);
//        System.out.println(tlots.diff(t8).cardinality() + " should be " + 0);
//        System.out.println(t8.diff(tlots).cardinality() + " should be " + 7);
//        System.out.println("\n\n");
//
//        System.out.println("equal:");
//        System.out.println(MT.equal(MT) + " should be " + true);
//        System.out.println(t5.equal(t4) + " should be " + false);
//        System.out.println(t6.equal(t6) + " should be " + true);
//        System.out.println(t78.equal(t67) + " should be " + false);
//        System.out.println(t678.equal(tlots) + " should be " + false);
//        System.out.println(t678.equal(t678) + " should be " + true);
//        System.out.println("\n\n");
//
//        System.out.println("subset:");
//        System.out.println(MT.subset(MT) + " should be " + true);
//        System.out.println(t7.subset(MT) + " should be " + false);
//        System.out.println(t7.subset(t7) + " should be " + true);
//        System.out.println(t67.subset(t6) + " should be " + false);
//        System.out.println(t678.subset(t6) + " should be " + false);
//        System.out.println(t6.subset(t678) + " should be " + true);
//        System.out.println(tlots.subset(t678) + " should be " + false);
//        System.out.println("\n\n");