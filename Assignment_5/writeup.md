### Matrix Multiplication Analysis

Using the standard row-column multiplication and the Strassen multiplication, I benchmarked each of these multiplication algorithms by generating a list of randomly assorted arrays 10 times for each algorithm. Every algorithm was also tested with square matrices with a length of 32, 64, 128, and 256. All numbers are listed in milliseconds.

| Method | Min | Max | Average | Uncertainty |
| ------- | ----- | ----- | ----- | ----------- |
| Standard (32) | 1.1 | 0 | 4 | 1.3 |
| Strassen (32) | 13.8 | 8 | 27 | 5.51
| Standard (64) | 1.0 | 1 | 1 | 0 |
| Strassen (64) | 45.9 | 25 | 66 | 11.97
| Standard (128) | 5.7 | 4 | 7 | 0.78 |
| Strassen (128) | 190.1 | 176 | 238 | 18.93
| Standard (256) | 42.2 | 38 | 48 | 2.86 |
| Strassen (256) | 1335.0 | 1259 | 1413 | 45.19

Oddly enough,for the standard algorithm, it appeared to be superior in time complexity compared to the Strassen method. With a bit of research, the Strassen algorithm seems to be slightly superior in terms of worst case complexity with other implementations yielding a complexity of $O(n^{\log(7)})$ which is better than the cubic complexity that the standard method has which leads me to believe my implementation is somewhat faulty and could be improved.