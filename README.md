
# INFO 6205 PSA - Final project

## Implementation 
#### General Implementation Strategy

-   We leveraged the code found in the class [repository](https://github.com/rchillyard/INFO6205) and added methods to perform similar operations using the java.text.Collator and com.ibm.icu.text.Collator.
    
-   We design a FileUtil class that we use for reading and writing data to text files located in the RandomString and SortedString directories. The read method in the class can be passed a parameter specifying the number of strings to be returned in the array.
    
-   We implemented a simple Enum called Language that we used in the Driver class to benchmark the sorting algorithms.
    
-   The Driver class contains the following static methods
    

-   benchMarkAnySort() - This method takes a Consumer as a parameter named sortFunction which is the sorting method that needs to be benchmarked. It takes in another parameter named lang which specifies what language to use as input for the sorting method.
    
-   getDataToSort() - This method returns an array of strings which serves as the input to the sorting method being benchmarked.
## Implementation Strategy For MSD and LSD Radix Sorts

-   For MSD Sort and LSD Sort, we used a TreeMap to store the keys and passed the Collator to determine the ordering of the keys in the map. These implementations are used while sorting Chinese strings. Here we explain the concept implemented for MSD Radix Sort and the same one has been used for LSD Radix Sort.
    

-   Consider we initiated MSD sort on a given input of strings. If we freeze the running of the algorithm precisely at the start of a recursive call on a sub-array of the original array with the value of d set to x. The sub-array consists of strings that have the same first x  characters.
    
>   Count - The first step is to count the number of occurrences of different characters at position d.  There are 2 possibilities when we encounter a character-
    

-   Encounter a new character: We add it to the TreeMap and store the count as 1.
    
-   Encounter an examined character: We increment the count by 1 for the character in the TreeMap.
    

>The value of a key(character) in the tree map now represents the count of the strings having that character at position d.

-   Transform - The second step is to transform the counts to useful indices. We leverage the fact that keys(i.e characters in our case) are stored in an orderly way in our TreeMap. We iteratively compute the sum of counts of all keys that come before a key ‘k’ and store it as the value for the key. The values in the treep map now represent the starting index of the character in the auxiliary array.
    
-   Distribute - Now that we have the starting indices, distributing the strings in an auxiliary array works similar to how normal MSD works with a count array. We keep incrementing the value whenever the key is encountered while distributing.
The values in the tree map now represent the starting index of the succeeding character in the auxiliary array.

> The main differences between this implementation and the general implementation of MSD sort
    

-   The TreeMap will consist of only characters that are encountered whereas the general implementation would initiate a count array of the size of the alphabet.
    
-   This implementation allows for a passing of a comparator to obtain custom orderings.
#### Implementation Strategy For Husky Sort of Chinese Strings

-   For Husky Sort, we added a HuskyCoder named ChineseCoder.
    
-   Our central idea behind the coder is that for sorting Chinese strings we use a Collator and Collator has a method called toByteArray() which returns a byte array representation of the key. Based on the method description - if two CollationKeys could be legitimately compared, then one could compare the byte arrays for each of those keys to obtain the same result, we decided to convert the byteArrays to String and encode it.
    
-   We used the unicodeCoder to encode the string obtained from the byteArray.
    
-   To verify if this encoding improves the time taken to sort we ran a few tests with variable inputs where we benchmarked the first run of husky sort and the second run of Tim Sort and compared it by sorting the same input using only Tim Sort. 
-   The coding was not perfect as the strings passed to the unicodeCoder had length greater than 4. Moreover, the byteArrays had negative values and so the conversion to strings resulted in random characters such as ‘�’. We proceeded with this encoding to obtain the benchmarking which is captured in Table B.
    
-   As we were working on figuring out a better encoding we came across Professor Robin’s encoding mechanism. We used this encoding and the findings have been stated under Graphs & Findings.
## Repo structure
The structure of the repo is as follows:
> **Driver.java :** Contains the **main** method where we call the sort methods to perform the experiments
>  The sortEssentials package contains the code for the classes implemented. Namely : MSDStringSort , LSDStringSort etc..
