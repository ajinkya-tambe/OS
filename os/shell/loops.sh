echo "Enter which loop to use"
echo "1. For loop"
echo "2. While loop"
echo "3. Until loop"

read choice
case $choice in
    1) echo "Enter a number"
        read num
        for (( i=1; i<=num; i++ ))
            do
                echo $i
            done
    ;;
    2) echo "Enter a number"
        read num
        i=1
        while [ $i -le $num ]
            do
                echo $i
                i=`expr $i + 1`
            done
    ;;
    3) echo "Enter a number"
        read num
        i=1
        until [ $i -gt $num ]
            do
                echo $i
                i=`expr $i + 1`
            done
    ;;
esac