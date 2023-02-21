#!/bin/bash

# write a program to perform the following operations on two strings
# 1. Length of a string
# 2. Concatenation of two strings
# 3. Change case to lowercase
# 4. Change case to uppercase
# 5. Slice a string

echo "Enter string 1"
read str1
echo "Enter string 2"
read str2

echo "Choose an operation to perform"
echo "1. Length of a string"
echo "2. Concatenation of two strings"
echo "3. Change case to lowercase"
echo "4. Change case to uppercase"
echo "5. Slice a string"

read choice

case $choice in
    1) echo "Length of string 1 is ${#str1}"
        echo "Length of string 2 is ${#str2}"
    ;;
    2) echo "Concatenation of two strings is $str1$str2"
    ;;
    3) echo "Lowercase of string 1 is ${str1,,}"
        echo "Lowercase of string 2 is ${str2,,}"
    ;;
    4) echo "Uppercase of string 1 is ${str1^^}"
        echo "Uppercase of string 2 is ${str2^^}"
    ;;
    5) echo "Enter the position to slice"
        read position
        echo "Enter the length to slice"
        read length
        echo "Sliced string is ${str1:position:length}"
    ;;
esac