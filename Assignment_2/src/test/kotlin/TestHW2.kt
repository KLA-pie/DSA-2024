import org.example.DoublyLinkedList
import org.junit.jupiter.api.Test
import org.example.Queue
import org.example.Stack
import org.example.areParenthesesMatched
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.*

class DoublyLinkedListTest {

    private lateinit var doublyLinkedList: DoublyLinkedList<String>

    @BeforeEach
    fun setUp() {
        doublyLinkedList = DoublyLinkedList()
    }

    @Test
    fun testPushFront() {
        doublyLinkedList.pushFront("test_push")
        assertEquals("test_push", doublyLinkedList.peekFront())
        assertEquals("test_push", doublyLinkedList.peekBack())
    }

    @Test
    fun testPushBack() {
        doublyLinkedList.pushBack("test_push")
        assertEquals("test_push", doublyLinkedList.peekFront())
        assertEquals("test_push", doublyLinkedList.peekBack())
    }

    @Test
    fun testPopFront() {
        doublyLinkedList.pushFront("test_push")
        doublyLinkedList.pushFront("second_push")
        assertEquals("second_push", doublyLinkedList.popFront())
        assertEquals("test_push", doublyLinkedList.popFront())
        assertNull(doublyLinkedList.popFront())
    }

    @Test
    fun testPopBack() {
        doublyLinkedList.pushBack("test_push")
        doublyLinkedList.pushBack("second_push")
        assertEquals("second_push", doublyLinkedList.popBack())
        assertEquals("test_push", doublyLinkedList.popBack())
        assertNull(doublyLinkedList.popBack())
    }

    @Test
    fun testPeekFront() {
        assertNull(doublyLinkedList.peekFront())
        doublyLinkedList.pushFront("test_push")
        assertEquals("test_push", doublyLinkedList.peekFront())
    }

    @Test
    fun testPeekBack() {
        assertNull(doublyLinkedList.peekBack())
        doublyLinkedList.pushBack("test_push")
        assertEquals("test_push", doublyLinkedList.peekBack())
    }

    @Test
    fun testIsEmpty() {
        assertTrue(doublyLinkedList.isEmpty())
        doublyLinkedList.pushFront("test_push")
        assertFalse(doublyLinkedList.isEmpty())
        doublyLinkedList.popFront()
        assertTrue(doublyLinkedList.isEmpty())
    }
}

class StackTest {

    private lateinit var stack: Stack<String>

    @BeforeEach
    fun setUp() {
        stack = object : Stack<String> {
            override var head: Stack.StackNode<String>? = null
        }
    }

    @Test
    fun testPushPop() {
        stack.push("test_push")
        stack.push("second_push")
        assertEquals("second_push", stack.pop())
        assertEquals("test_push", stack.pop())
        assertNull(stack.pop())
    }

    @Test
    fun testPeek() {
        stack.push("test_push")
        assertEquals("test_push", stack.peek())
    }

    @Test
    fun testIsEmpty() {
        assertTrue(stack.isEmpty())
        stack.push("test_push")
        assertFalse(stack.isEmpty())
        stack.pop()
        assertTrue(stack.isEmpty())
    }
}

class QueueTest {

    private lateinit var queue: Queue<String>

    @BeforeEach
    fun setUp() {
        queue = object : Queue<String> {
            override var front: Queue.QueueNode<String>? = null
            override var rear: Queue.QueueNode<String>? = null
        }
    }

    @Test
    fun testEnqueueDequeue() {
        queue.enqueue("test_push")
        queue.enqueue("second_push")
        assertEquals("test_push", queue.dequeue())
        assertEquals("second_push", queue.dequeue())
        assertNull(queue.dequeue())
    }

    @Test
    fun testPeek() {
        assertNull(queue.peek())
        queue.enqueue("test_push")
        assertEquals("test_push", queue.peek())
    }

    @Test
    fun testIsEmpty() {
        assertTrue(queue.isEmpty())
        queue.enqueue("test_push")
        assertFalse(queue.isEmpty())
        queue.dequeue()
        assertTrue(queue.isEmpty())
    }
}


class ParenthesisTest(){
    @Test
    fun testCorrectExpression(){
        assertTrue(areParenthesesMatched("(Mihir)"),)
    }
    @Test
    fun testIncorrectExpression(){
        assertFalse(areParenthesesMatched("(Mihir("))
    }
    @Test
    fun testUnbalancedBracket(){
        assertFalse(areParenthesesMatched("([]((({})))"))
    }
}
