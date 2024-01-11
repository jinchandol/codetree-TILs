num = int(input())

digit_list = []

def digit(n):
    while True:
        if n < 2:
            digit_list.append(n)
            break
        
        digit_list.append(n%2)
        n //= 2

digit(num)

for i in digit_list[::-1]:
    print(i, end = "")