#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'lilysHomework' function below.
#
# The function is expected to return an INTEGER.
# The function accepts INTEGER_ARRAY arr as parameter.
#
def no_of_swaps(arr):
    indexmap = {}
    
    for i in range(len(arr)):
        indexmap[arr[i]] = i
        
    sorted_array = sorted(arr)
    result = 0
    
    for i in range(len(arr)):
        if (arr[i] != sorted_array[i]):
            result += 1
            swapped_index = indexmap[sorted_array[i]]
            indexmap[arr[i]] = swapped_index
            arr[i], arr[swapped_index] = arr[swapped_index], arr[i]
    return result

def lilysHomework(arr):
    # Write your code here

    
    count1 = no_of_swaps(arr[::])
    count2 = no_of_swaps(arr[::-1])
    return min(count1, count2)


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input().strip())

    arr = list(map(int, input().rstrip().split()))

    result = lilysHomework(arr)

    fptr.write(str(result) + '\n')

    fptr.close()
