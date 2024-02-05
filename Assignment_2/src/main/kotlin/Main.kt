package org.example
/**
 * Node represents a value and a reference to the next and previous
 * element in the Stack
 * @param T the type of data to store in the node.
 * @property data the data stored
 * @property prev the previous value in the stack
 * @property next the next value in the stack
 */
class Node<T>(var data: T) {
    var prev: Node<T>? = null
    var next: Node<T>? = null
}

/**
 * DoublyLinkedList is a class expression for a doubleheaded linked list using
 * nodes
 * @param T the type of data to store in the stack.
 * @property front the first head of the stack
 * @property back the second head of the stack
 */
class DoublyLinkedList<T> {
    private var front: Node<T>? = null
    private var back: Node<T>? = null

    /**
     * Place an element of data, [data], at the first head in the linked list
     *
     * @param data Set of data to store
     */
    fun pushFront(data: T) { // Instantiate a node and place it in the front
        val newNode = Node(data)
        newNode.next = front
        front?.prev = newNode
        front = newNode

        if (back == null) { // If the list is empty, the new node is both the front and back
            back = newNode
        }
    }

    /**
     * Place an element of data, [data], at the second head in the linked list
     *
     * @param data Set of data to store
     */
    fun pushBack(data: T) { // Instantiate a node and place it in the back
        val newNode = Node(data)
        newNode.prev = back
        back?.next = newNode
        back = newNode

        if (front == null) { // If the list is empty, the new node is both the back and front
            front = newNode
        }
    }

    /**
     * Remove the element of data on first head in the linked list
     *
     * @return The data at the first head of the stack
     */
    fun popFront(): T? {
        val frontValue = front?.data
        front = front?.next // The front of the list is now the second one
        front?.prev = null // The front of the list will have nothing in front

        if (front == null) { // If there is nothing at the front, there is nothing behind
            back = null
        }

        return frontValue
    }

    /**
     * Remove the element of data on second head in the linked list
     *
     * @return The data at the second head of the stack
     */
    fun popBack(): T? {
        val backValue = back?.data
        back = back?.prev // The back of the list is now the preceding one
        back?.next = null // The back of the list will have nothing behind it

        if (back == null) { // If there is nothing at the back, there is nothing in front
            front = null
        }

        return backValue
    }

    /**
     * Check the element of data on first head in the linked list
     *
     * @return The data at the first head of the stack
     */
    fun peekFront(): T? {
        return front?.data
    }

    /**
     * Check the element of data on second head in the linked list
     *
     * @return The data at the second head of the stack
     */
    fun peekBack(): T? {
        return back?.data
    }

    /**
     * Check to see if the list is empty
     *
     * @return A boolean for whether the list is empty
     */
    fun isEmpty(): Boolean {
        return front == null
    }
}

/**
 * Stack is a class expression for a data stack
 * @param T the type of data to store in the stack.
 * @property head the top of the stack
 */
interface Stack<T> {

    var head: StackNode<T>?

    /**
     * StackNode represents a value and a reference to the next element in the Stack
     * @param T the type of data to store in the node.
     * @property data the data stored
     * @property next the next value in the Stack
     */
    class StackNode<T>(val data: T,
                                var next: StackNode<T>?)


    /**
     * Remove the top element of the stack and return it. If it is empty
     * return null
     *
     * @return the data stored at the top of the stack
     */
    fun pop(): T? {
        val returnValue = head?.data
        head = head?.next
        return returnValue
    }

    /**
     * Push an element of data, [data] onto the top of the stack
     *
     * @param data the data to put on the stack
     */
    fun push(data: T) {
        head = StackNode<T>(data, head)
    }

    /**
     * View the element of data on the top of the stack
     *
     * @return the data at the top of the stack
     */
    fun peek(): T? {
        return head?.data
    }


    /**
     * @return A boolean expression for whether the stack is empty
     */
    fun isEmpty(): Boolean {
        return head == null
    }
}
/**
 * Queue is a class expression for a data queue
 * @param T the type of data to store in the stack.
 * @property front the top of the queue
 * @property rear the end of the queue
 */
interface Queue<T> {

    var front: QueueNode<T>?
    var rear: QueueNode<T>?

    /**
     * QueueNode represents a value and a reference to the next element in the Queue
     * @param T the type of data to store in the node.
     * @property data the data stored
     * @property next the next value in the Queue
     */
    class QueueNode<T>(val data: T, var next: QueueNode<T>?)
    /**
     * Add an element at the back of the queue.
     *
     * @param data The data to add to the queue
     */
    fun enqueue(data: T) {
        val newNode = QueueNode<T>(data, null)
        if (rear == null) { // If the queue is empty, both front and back are the same
            front = newNode
            rear = newNode
        }
        else {
            // Update the next reference of the current rear to the new node
            rear?.next = newNode
            // Update rear to the new node
            rear = newNode
        }
    }
    /**
     * Remove the element at the front of the queue.
     * If the queue is empty, return null
     *
     * @return the value at the front of the queue
     */
    fun dequeue(): T? {
        val returnValue = front?.data
        front = front?.next // The front is now the next node
        if (front == null) { // If the queue is empty, both the front and back are empty
            rear = null
        }
        return returnValue
    }
    /**
     * Check the element at the head of the queue. If the queue has nothing
     * return null
     *
     * @return the value at the front of the queue
     */
    fun peek(): T? {
        return front?.data
    }
    /**
     * Check to see if the queue is empty
     *
     * @return A boolean for whether the queue is empty
     */
    fun isEmpty(): Boolean {
        return front == null
    }
}

/**
 * Check to see if ta set of parentheses are matched
 *
 * @param input A string of characters to splice
 *
 * @return A boolean for if a sequence of parentheses are balanced
 */
fun areParenthesesMatched(input: String): Boolean {
    val stack = object : Stack<Char> { // First instantiate a stack
        override var head: Stack.StackNode<Char>? = null
    }

    for (char in input) { // Iterate through all characters of the string
        when (char) {
                    // If the character is a left bracket, add it to the stack
                    // If the character is a right bracket remove the top element and see
                    // if the brackets are the same type
            '(', '{', '[' -> stack.push(char)
            ')', '}', ']' -> {
                val top = stack.pop()
                // Negate the boolean if the bracket is correct
                if ((char == ')' && top != '(') ||
                    (char == '}' && top != '{') ||
                    (char == ']' && top != '[')
                ) {
                    return false
                }
            }
        }
    }

    return stack.isEmpty() // The parentheses are matched only if the stack is empty
}

