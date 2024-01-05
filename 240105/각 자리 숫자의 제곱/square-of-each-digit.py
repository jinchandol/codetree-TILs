n = int(input())

def recur(num):
    if num < 10:
        return num**2
    
    return recur(num//10) + (num%10)**2

print(recur(n))