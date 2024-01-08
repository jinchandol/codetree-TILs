str1 = list(input())
str2 = list(input())

str1.sort()
str2.sort()

def is_equal():
    for s1, s2 in zip(str1, str2):
        if s1 != s2:
            return False
    
    return True

if len(str1) == len(str2):
    if is_equal:
        print("Yes")
    else:
        print("No")
else:
    print("No")