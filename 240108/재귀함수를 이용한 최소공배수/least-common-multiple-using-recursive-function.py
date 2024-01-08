n = int(input())

arr = list(map(int, input().split()))

def gcd(a, b):
    return b if a % b == 0 else gcd(b, a % b)

def lcm(a, b):
    return int(a * b / gcd(a, b))


def recur(a):
    if a == 0:
        return arr[0]

    return lcm(recur(a - 1), arr[a])


print(recur(n-1))