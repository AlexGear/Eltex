package ru.eltex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeSet;

public class CollectionsTesting {
	public static void main(String[] args) {
		testAndPrint(new ArrayList<Integer>());
		testAndPrint(new LinkedList<Integer>());
		testAndPrint(new TreeSet<Integer>());
	}
	
	static void testAndPrint(Collection<Integer> collection) {
		prepare(collection);
		
		Integer value = 99999999;
		long addingTime = measureAddingTime(collection, value);
		long removingTime = measureRemovingTime(collection, value);
		
		String name = collection.getClass().getSimpleName();
		System.out.printf("%s:\n  Adding: %d ns\n  Removing: %d ns\n\n", name, addingTime, removingTime);
	}
	
	static void prepare(Collection<Integer> collection) {
		for(int i = 0; i < 1000000; i++) {
			collection.add(i);
		}
	}
	
	static long measureAddingTime(Collection<Integer> collection, Integer value) {
		long timeBefore = System.nanoTime();
		collection.add(value);
		long timeAfter = System.nanoTime();
		return timeAfter - timeBefore;
	}
	
	static long measureRemovingTime(Collection<Integer> collection, Integer value) {
		long timeBefore = System.nanoTime();
		collection.remove(value);
		long timeAfter = System.nanoTime();
		return timeAfter - timeBefore;
	}
}