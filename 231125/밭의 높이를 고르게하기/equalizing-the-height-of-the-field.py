import sys

n, h, t = map(int, input().split())

height = list(map(lambda x : abs(int(x) - h), input().split()))

cost = sys.maxsize

for i in range(n-t+1):
    min_c = sum(height[i:i+t])
    cost = min(cost, min_c)

print(cost)