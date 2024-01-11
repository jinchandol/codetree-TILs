n = list(map(int, list(input())))

def digit_to_decimal(num1):
    value = 0

    for i in num1:
        value = value*2 + i
    
    return value

decimal = digit_to_decimal(n)

decimal_time = decimal * 17

digit = []

def decimal_to_digit(num2):
    while True:
        if num2 < 2:
            digit.append(num2)
            break
        
        digit.append(num2%2)
        num2 //=2

decimal_to_digit(decimal_time)

for j in digit[::-1]:
    print(j, end="")