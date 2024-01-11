a, b = map(int, input().split())

n = list(map(int, list(input())))
length = len(n)

value = 0

for i in range(length):
    value = value*a + n[i]

ans = []

while True:
    if value < b:
        ans.append(value)
        break
    
    ans.append(value%b)
    value //= b

for j in ans[::-1]:
    print(j, end="")