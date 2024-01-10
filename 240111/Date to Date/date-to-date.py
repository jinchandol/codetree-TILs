num_of_days = [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

m1, d1, m2, d2 = map(int, input().split())

ans = sum(num_of_days[1:m2+1])+d2 - sum(num_of_days[1:m1+1]) - d1 

print(ans)