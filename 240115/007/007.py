class Student:
    def __init__(self, secret_code, meeting_point, time):
        self.s = secret_code
        self.m = meeting_point
        self.t = time

info = tuple(input().split())

student = Student(info[0], info[1], info[2])

print("secret code :", student.s)
print("meeting point :", student.m)
print("time :", student.t)