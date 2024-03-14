## Project Summary

The purpose of this project was to explore and simulate the Quantum Fourier Transform (QFT) and Deutsch-Josza algorithm. The QFT is a fundamental operation in quantum computing that allows us to transform a quantum state from the time domain to the frequency domain. It plays a crucial role in many quantum algorithms, including the Deutsch-Josza algorithm.

The Deutsch-Josza algorithm is a quantum algorithm that solves the Deutsch problem, which is a simple problem of determining whether a given function is constant or balanced. It demonstrates the power of quantum computing by providing a quadratic speedup compared to classical algorithms.

In this project, we aimed to understand the underlying principles of these algorithms and implement them using a quantum simulator. By simulating the behavior of quantum circuits, we were able to observe the quantum phenomena and analyze the results.

### The Quantum Fourier Transform

The Quantum Fourier Transform (QFT) is a quantum equivalent of the classical Fourier Transform, which is a mathematical operation that decomposes a function into its constituent frequencies. In classical computing, the Fourier Transform, more specifically the Fast Fourier Transform, is used extensively in signal processing, data compression, and various other fields. The Quantum Fourier Transform plays a crucial role in many quantum algorithms to compose a given set of qubits under a specific linea basis to work under.

### Classical Fourier Transform:
Let's first understand the classical Fourier Transform briefly. Given a function $f(x)$, the continuous Fourier Transform (CFT) is defined as:

$F(k) = \int_{-\infty}^{\infty} f(x) e^{-2\pi i k x} \, dx$

Here, $ F(k) $ represents the frequency components of $f(x) $ at different frequencies $ k $. The inverse Fourier Transform takes $ F(k) $ and reconstructs $ f(x) $:

$ f(x) = \int_{-\infty}^{\infty} F(k) e^{2\pi i k x} \, dk $

The Discrete Fourier Transform (DFT) is used when dealing with discrete data. For a sequence $ x_0, x_1, ..., x_{N-1} $, the DFT is defined as:

$ X_k = \sum_{n=0}^{N-1} x_n \cdot e^{-2\pi i k n / N} $

### The Fast Fourier Transform Broken Down

The classical discrete Fourier transform can be broken down in the following algorithmic procedure.

#### Step 1: 
Divide the input sequence of length N into even and odd indexed sequeneces. We can then apply the DFT to these sequences

#### Step 2: Compute
Compute the DFT of each set of indices. This involves dividing the DFT computation into smaller sub-problems, reducing the overall computational complexity.

#### Step 3: Combine
Combine the results of the DFTs toegther

As we'll see with the Quantum Fourier Transform, the process is similar but not the same

### Quantum Fourier Transform (QFT):
Now, let's delve into the Quantum Fourier Transform. In quantum computing, we deal with quantum states represented by qubits. The QFT acts on a quantum state represented by an $ N $-qubit register.

Suppose we have an $ N $-qubit state represented as $ |x\rangle = |x_1x_2...x_N\rangle $, where $ x_i $ represents the value of the $ i $-th qubit. The QFT transforms this state into:

$ |y\rangle = \frac{1}{\sqrt{N}} \sum_{k=0}^{N-1} e^{2\pi i xk/N} |k\rangle $

Where $ |k\rangle $ represents the basis state corresponding to the integer $ k $.

### QFT Broken Down:
The QFT involves a series of quantum gates to perform the transformation. Here's how it can be constructed:

#### Step 1: Hadamard Transform
Apply Hadamard gates to each qubit in the register. This is analogous to dividing the inputs up and pr

$ H^{\otimes N} |x\rangle = \frac{1}{\sqrt{N}} \sum_{x=0}^{N-1} |x\rangle $

#### Step 2: Controlled Phase Shift
Apply controlled-phase gates. For each qubit $ j $, apply a gate that performs a phase shift of $ \frac{2\pi}{2^{j+1}} $ when the control qubit is in the $ |1\rangle $ state. This would be analogous to computing the DFT of the elements in broken down list in the FFT.

$ |x\rangle \rightarrow \frac{1}{\sqrt{N}} \sum_{x=0}^{N-1} e^{2\pi i \frac{xk}{2^{j+1}}} |x\rangle $

#### Step 3: Swap Qubits
Finally, reorder the qubits so that the state is in the correct order for measurement. When the state is collapsed, this is when we get our Fourier Coefficients

The Quantum Fourier Transform is a fundamental operation in quantum computing, analogous to the classical Fourier Transform. It efficiently computes the discrete Fourier Transform of quantum states, playing a vital role in many quantum algorithms by enabling the manipulation of quantum superpositions of states.

### Deutsch-Jozsa Algorithm:
Now we will detail the Deutsch-Jozsa algorithm. Given a black-box function, the Deutsch-Jozsa problem asks whether the function is constant (outputs the same value for all inputs) or balanced (outputs 0 for half the inputs and 1 for the other half). In the classical case, determining the nature of the function requires $2^{n-1}+1$ queries to the function in the worst case. The Deutsch-Jozsa algorithm, however, can solve this problem with a single query, making it exponentially faster than classical algorithms.

### Deutsch-Jozsa Algorithm Breakdown

#### Step 1: Initialization: 
Start with a quantum registers: an $ n $-qubit input register initialized to $ |0\rangle^{\otimes n} $.

#### Step 2: Hadamard Transform
Apply Hadamard gates to all qubits. This puts the input register into a superposition of all possible input states.

$ |0\rangle^{\otimes n} \xrightarrow{H^{\otimes n}} \frac{1}{\sqrt{2^n}} \sum_{x=0}^{2^n-1} |x\rangle $

#### Step 3: Oracle Query
Apply the oracle (black-box function) $ U_f $ which performs the transformation $ |x\rangle |y\rangle \rightarrow |x\rangle |y \oplus f(x)\rangle $, where $ \oplus $ denotes bitwise addition modulo 2. In the Deutsch-Jozsa problem, this oracle is provided as a quantum gate that performs the function $ f(x) $.

#### Step 4: Hadamard Gates Again
Apply Hadamard gates to the input register.

#### Step 5: Measurement
Measure all qubits in the input register. If all measurements result in either $ |0\rangle $ or $ |1\rangle $, the function is constant; if any measurement results in half of the collapsed state, the function is balanced.

### Quantum Fourier Transform (QFT) in DJ Algorithm:
The Quantum Fourier Transform is utilized in the Deutsch-Jozsa algorithm during the final step of Hadamard gates. After applying the oracle, another Hadamard transformation is applied to the input qubits. This effectively implements the Quantum Fourier Transform. However, the QFT itself is not explicitly called out in the Deutsch-Jozsa algorithm, unlike in other algorithms such as Shor's algorithm.

## Implementation
The provided Kotlin code simulates the Quantum Fourier Transform (QFT) and Deutsch-Josza (DJ) algorithm for a 2-qubit system. It achieves this by creating a custom class `PseudoRandomNumberGenerator` (PRNG) and leveraging it within the core algorithm functions.

In essence, the PRNG acts as a tool to introduce randomness into the calculations, mimicking the probabilistic nature of quantum mechanics and enabling the simulation of these quantum algorithms on a classical computer. While a true quantum computer would leverage the superposition and entanglement properties of qubits, this classical simulation provides valuable insights into the behavior of these algorithms for small-scale systems.

For the QFT, the implementation was a bit more complicatd to implement compared to the PRNG class.

In my code, I built a function called `quantumFourierTransform` to tackle the QFT for a 2-qubit system. It takes a list representing the initial state of the qubits and a PRNG instance for generating random angles.

The first step involves figuring out how many qubits we're dealing with based on the size of the input list. Since a 2-qubit system has 4 basis states (00, 01, 10, 11), the input list should have a length of 4. Then, we create another list to store the amplitudes for each basis state after the QFT.

The core part involves looping through each element in the output state. For each element, we need to consider every element in the input state. Here's where things get a bit more mathematical. We calculate a specific rotation angle for each combination of input and output qubits.

However, directly simulating these rotations on a classical computer isn't possible. That's where the PRNG comes in. We use it to generate random numbers, which are then used to create random phase shifts that are added to the rotation angles. This effectively incorporates the randomness inherent in quantum mechanics into the calculations.

Finally, We accumulate the effects of these rotations on the input state, considering both the cosine and sine of the adjusted angles. After iterating through all possible combinations, we normalize the results to get the final amplitudes representing the transformed state after the QFT. By performing this process for each element in the output state, we essentially simulate the QFT for a 2-qubit system classically. 

We've also implemented the Deutsch-Josza (DJ) algorithm for a 2-qubit system using a function named `deutschJoszaAlgorithm`. It has three arguments: the oracle function we want to analyze (either constant or balanced), the number of qubits (which is 2 in this case), and, once again, PRNG.

The first step is to create the initial state of the qubits. Since we're working with 2 qubits, this translates to a list of four elements, each representing an equal mix of being 0 or 1 (1 divided by the size of the list). This initial state signifies that both qubits are in a superposition, ready to explore both possibilities of the oracle function simultaneously.

We implemented a series of nested loops to perform Hadamard transforms on each qubit individually. These Hadamard transforms are crucial because they put the qubits into a superposition state, allowing them to explore both possibilities of the oracle function at the same time.

After applying the Hadamard transforms, it's time to interact with the oracle function. We loop through each element in the current state and apply the user-defined oracle function to it. Remember, this oracle function is like a black box – it can be either constant (always returning 0 or 1) or balanced (returning 0 or 1 with equal probability). This step introduces the key difference between constant and balanced functions into the qubit state.

Another round of Hadamard transforms follows, further manipulating the qubit state based on the information obtained from the oracle. This prepares the state for the QFT.

We call the `quantumFourierTransform` function we discussed earlier. This function, with the help of the PRNG for introducing randomness, simulates the QFT on the current state. The QFT essentially amplifies certain basis states depending on the properties of the oracle function.

Finally, we need to determine if the oracle function is constant or balanced. Since a constant function will affect all basis states in a similar way, the output from the QFT should have most elements concentrated around positive or negative values (due to cancellation effects). We check the distribution of values in the QFT output. If a significant majority (more than 3/4) lean towards positive or negative, it suggests a constant oracle function. Otherwise, the function is likely balanced.

By analyzing the outcome of the QFT, we can successfully distinguish between constant and balanced oracle functions using the Deutsch-Josza algorithm.

## Unit Testing

Developing unit tests for the Quantum Fourier Transform (QFT) and Deutsch-Josza (DJ) algorithms in my code presented a unique challenge. The inherent randomness introduced by the PseudoRandomNumberGenerator (PRNG) made it difficult to predict the exact outputs of these algorithms.

In traditional unit testing scenarios, we expect a deterministic relationship between inputs and outputs. However, the PRNG injects a layer of controlled randomness into the calculations. This randomness mimics the probabilistic nature of quantum mechanics but makes it challenging to predetermine the precise outcome for unit testing purposes.

Some unit tests/experiments I did were simple such as modeling the QFT on a single qubit while others were more advanced such as modeling different types of oracle cases in the DJ algorithm.

Consequently, the unit tests for the QFT and DJ algorithms rely on assertions with relatively high delta values. These delta values represent an acceptable margin of error for the comparison between the calculated output and the expected output. While a certain level of deviation is expected due to the randomness, minimizing the delta values remains an ongoing effort. Even the relatively high delta values do not work for some PRNG seeds due to the noise generated by the given seed which means that some tests will give us the wrong output for some specific oracle in our DJ implementation.

This experience has sparked my interest in exploring more robust testing methodologies for quantum-inspired algorithms. Ideally, we would like to develop unit tests that are both reliable and informative, even in the presence of randomness. Here are some potential avenues for further exploration:

We could potentially explore techniques to mock the PRNG during unit testing. Mocking would allow us to inject predictable random values, enabling a more deterministic testing environment for the core functionalities of the QFT and DJ algorithms.

Shifting the focus from individual test cases to statistical analysis could be another approach. By running the algorithms multiple times with different random seeds and statistically analyzing the distribution of the outputs, we could build confidence in the overall correctness of the implementation.

Investigating formal verification techniques, which involve mathematically proving the correctness of code, could be a long-term goal. While challenging, formal verification could offer the highest level of assurance for these quantum-inspired algorithms.

Unit testing quantum-inspired algorithms with inherent randomness requires careful consideration.  While the current approach with delta values provides a baseline for functionality, further exploration into mocking, statistical testing, and formal verification techniques holds promise for developing more robust and informative testing strategies in the future.

## Future Applications

Even though unit testing these quantum-inspired algorithms presents challenges due to the randomness of the PRNG, there is still significant value in investigating and understanding the Deutsch-Josza and Quantum Fourier Transform algorithms.

The mock code serves as a valuable educational tool and a stepping stone towards more complex applications. Despite limitations in perfectly simulating a quantum computer, the core principles behind these algorithms remain relevant.

The DJ algorithm, for instance, demonstrates the potential for efficiently differentiating between certain types of functions, a concept that could be applied in various domains like code optimization, large-scale cryptography, or data analysis. Similarly, the QFT lays the groundwork for understanding quantum signal processing and other advanced applications that may emerge in the future of quantum computing.

Therefore, while the current implementation might not achieve perfect fidelity due to the simulated nature and the limitations of the PRNG, it serves as a crucial step in exploring the potential of these algorithms and paves the way for future advancements in the field of quantum-inspired computing.

