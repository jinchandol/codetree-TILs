n = int(input())

def recur(num):
    if num == 1:
        return 1

    return recur(num-1) + num

# ans = recur(n)

# print(ans)
print(recur(n))