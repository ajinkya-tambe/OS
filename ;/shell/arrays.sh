#!/bin/bash

arr=(1 2 3 4 5 6 7 8 9 10)
echo "Array is ${arr[@]}"

echo "Choose an array operation to perform"
echo "1. Print array length"
echo "2. Insert an element"
echo "3. Delete an element"

read choice
case $choice in
    1) echo "Array length is ${#arr[@]}"
    ;;
    2) echo "Enter the element to insert"
        read element
        echo "Enter the position to insert"
        read position
        arr=(${arr[@]:0:$position} $element ${arr[@]:$position})
        echo "Array is ${arr[@]}"
    ;;
    3) eecho "Enter the position to delete"
        read position
        unset arr[$position]
        echo "Array is ${arr[@]}"
    ;;
esac
