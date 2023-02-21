echo "Enter VOLP username"
read email
echo "Enter VOLP password"
read -s password

if test $email = "tilak@vit.edu"
    then
        echo "Valid Email"
        if test $password = "1234abcd"
            then
                echo "Login Successful"
                echo "Enter roll number to view result"
                read roll
                case $roll in
                    1) echo "Pass" 
                    ;;
                    2) echo "Pass" 
                    ;;
                    3) echo "Fail" 
                    ;;
                esac
        fi
    else
        echo "Invalid Credentials"
fi

