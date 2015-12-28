/**
 * 
 */
package textgen;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	MyLinkedList<Integer> list2;
	MyLinkedList<Integer> list3;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);

		list2 = new MyLinkedList<Integer>();
		list2.add(65);
		list2.add(21);
		list2.add(42);

		list3 = new MyLinkedList<Integer>();
		list3.add(66);
		list3.add(22);
		list3.add(43);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}

	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		assertEquals("Remove: empty list ", null, emptyList.remove(0));
		try {
			shortList.remove(3);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		assertEquals("Remove: check1 at the end of list", (Integer)42, list1.remove(2));
		assertEquals("Remove: check2 at the end of list", 2, list1.size());
		assertEquals("Remove: check3 at the end of list", (Integer)21, list1.get(1));

		int a = list1.remove(0);
		assertEquals("Remove: check1 at start of list is correct ", 65, a);
		assertEquals("Remove: check2 at start of list, element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check3 at start of list, size is correct ", 1, list1.size());

		list1.add(61);
		list1.add(41);
		assertEquals("Remove: check1 at mid of list is correct ", (Integer)61, list1.remove(1));
		assertEquals("Remove: check2 at mid of list, size is correct ", 2, list1.size());
		assertEquals("Remove: check3 at mid of list, element 1 is correct ", (Integer)41, list1.get(1));

	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{

		//assertEquals("Test null functionality", false, shortList.add(null));
		assertEquals("Test successful functionality", true, shortList.add("C"));
		assertEquals("Check end", "C", shortList.get(2));

	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("List 1 length test ", 3, list1.size());
		assertEquals("shortList length test ", 2, shortList.size());
		assertEquals("empty list length test ", 0, emptyList.size());
		assertEquals("longer list length test ", LONG_LIST_LENGTH, longerList.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		int sizeList2 = list2.size();
		try {
			list2.add(0,null);
			fail("Check out of bounds");
		}
		catch (NullPointerException e) {
		}
		assertEquals("Test null functionality", sizeList2, list2.size());

		try {
			list2.add(5,77);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}

		try {
			list2.add(-1,77);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}

		list2.add(3,80);
		assertEquals("Test successful functionality end of list", (Integer)80, list2.get(3));

		list2.add(0,88);
		assertEquals("Test successful functionality start of list", (Integer)88, list2.get(0));

		list2.add(1,73);
		assertEquals("Test successful functionality mid of list part1", (Integer)73, list2.get(1));
		assertEquals("Test successful functionality mid of list part2", (Integer)65, list2.get(2));
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		try {
			list3.set(6,86);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		int list3Size = list3.size();
		assertEquals("Test successful Set functionality 1", (Integer)22, list3.set(1,44));
		assertEquals("Test successful Set functionality 2", (Integer)44, list3.get(1));
		assertEquals("Test successful Set functionality 3", list3Size, list3.size());
	    
	}

}
