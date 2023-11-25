n = int(input())
grid = [
    [0 for _ in range(200)]
    for _ in range(200)
]

for _ in range(n):
    x1, y1, x2, y2 = map(lambda x : int(x) + 100, input().split())

    for i in range(x1, x2):
        for j in range(y1, y2):
            grid[i][j] = 1

ans = 0

for elem1 in range(200):
    for elem2 in range(200):
        ans += grid[elem1][elem2]


print(ans)