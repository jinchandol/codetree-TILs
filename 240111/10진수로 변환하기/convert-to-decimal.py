digit_list = list(map(int, input()))

num = 0

for i in range(len(digit_list)):
    num = num*2 + digit_list[i]

print(num)