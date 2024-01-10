num_of_days = [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

m1, d1, m2, d2 = map(int, input().split())

ans = (num_of_days[m1] - d1 + 1) + sum(num_of_days[m1+1:m2]) + d2

print(ans)