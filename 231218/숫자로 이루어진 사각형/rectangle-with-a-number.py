n = int(input())

cnt = 1

grid = [
    [0 for _ in range(n)]
    for _ in range(n)
]

for row in range(n):
    for col in range(n):
        grid[row][col] = cnt
        cnt += 1
        
        if cnt == 10:
            cnt -= 9

for i in range(n):
    for j in range(n):
        print(grid[i][j], end=' ')
    print()