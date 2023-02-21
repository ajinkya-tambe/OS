#!/bin/bash

if [ $# -eq 0 ]
then
    echo "No arguments supplied"
    echo "If you don't know what the program does!"
    echo "pass your roll number as an argument to check result."
    exit 1
fi

if [ $1 -eq 1 ]
then
    echo "Pass"
elif [ $1 -eq 2 ]
then
    echo "Pass"
elif [ $1 -eq 3 ]
then
    echo "Fail"
else
    echo "Invalid roll number"
fi
