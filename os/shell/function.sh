#!/bin/bash

function palindrome()
{
    num=$1
    rev=0
    while [ $num -gt 0 ]
    do
        rem=`expr $num % 10`
        rev=`expr $rev \* 10 + $rem`
        num=`expr $num / 10`
    done
    if [ $rev -eq $1 ]
    then
        echo "Palindrome"
    else
        echo "Not Palindrome"
    fi
}

function fibo()
{
    num=$1
    a=0
    b=1
    echo "Fibonacci series is"
    echo $a
    echo $b
    for (( i=2; i<$num; i++ ))
    do
        c=`expr $a + $b`
        echo $c
        a=$b
        b=$c
    done
}

function factorial()
{
    num=$1
    fact=1
    for (( i=1; i<=$num; i++ ))
    do
        fact=`expr $fact \* $i`
    done
    echo "Factorial of $num is $fact"
}

echo "What do you want to do?"
echo "1. Check if a number is palindrome or not"
echo "2. Print fibonacci series"
echo "3. Print factorial of a number"
read choice
case $choice in
    1) echo "Enter a number"
        read num
        palindrome $num
    ;;
    2) echo "Enter a number"
        read num
        fibo $num
    ;;
    3) echo "Enter a number"
        read num
        factorial $num
    ;;
esac