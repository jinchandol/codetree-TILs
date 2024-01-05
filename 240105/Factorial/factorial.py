n = int(input())

def fac(num):
    if num == 1:
        return 1
    
    return fac(num-1)*num

ans = fac(5)

print(ans)