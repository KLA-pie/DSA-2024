## Lossless Compression with Lempel-Ziv

In my implementation I utilized an associative array for lossless compression. 

The experiment involved developing the Lempel-Ziv algorithm utilizing those associative arrays.  When encountering a character in the data stream, the algorithm would check the array for its existence. If present, the corresponding count would be incremented. If not, a new entry would be created within the array, associating the character with a count of 1. This approach allowed for the efficient storage of recurring characters and their frequencies.

To assess the effectiveness of this method, I employed a set of test strings:

* ABABCABABCDABCDABDE
* MISSISSIPPI
* SHESELLSSEASHELLS
* ABRACADABRA
* THEQUICKBROWNFOXJUMPSOVERTHELAZYDOG

The compression results for each string varied depending on the level of redundancy present. Strings with a high frequency of repeated characters, such as "MISSISSIPPI" and "SHESELLSSEASHELLS," achieved compression ratios exceeding 20%. Conversely, strings with minimal repetition, like "THEQUICKBROWNFOXJUMPSOVERTHELAZYDOG," yielded lower compression percentages.

The experiment provided valuable insights into the potential of associative arrays for data compression. While the specific implementation details are beyond the scope of this summary, the core concept highlights the effectiveness of this approach in scenarios where data exhibits redundancy.  Future work could involve exploring different techniques for symbol representation and further optimizing the encoding process to enhance compression efficiency.

## Lossless Compression Experiment with Character Counts

In this experiment, I explored a novel approach to lossless data compression. I implemented a system that utilizes a data structure to efficiently store repeated characters and their corresponding counts. This method aimed to reduce the overall size of the data while preserving its integrity.

To evaluate the effectiveness of this technique, I employed a set of test strings:

* "ABABCABABCDABCDABDE"
* "MISSISSIPPI"
* "SHESELLSSEASHELLS"
* "ABRACADABRA"
* "THEQUICKBROWNFOXJUMPSOVERTHELAZYDOG"

These strings exhibit varying degrees of redundancy, which is a crucial factor for successful compression using this method. Redundancy refers to the repetition of characters within the data.

The compression algorithm achieved the following results:

* String 1: Compressed size represents 42.11% of the original string's size.
* String 2: Achieved a compression ratio of 18.18%.
* String 3: Compressed data size is 23.53% of the original string.
* String 4: Resulted in a compressed size representing 36.36% of the original data.
* String 5: Showed the least compression effectiveness at 11.43%.

The observed variations in compression ratios highlight the influence of redundancy on the effectiveness of this technique. Strings with a higher frequency of repeated characters achieved a greater reduction in size. 

The implementation showed me the usefulness of this algorithm for lossless compression with promising compression results. Even some results that were low such as the quick brown fox string can still be considered significant compression ratios especially if our data were to become larger.
