class user:
    def __init__(self, ID = "codetree", Level = 10):
        self.ID = ID
        self.Level = Level

Id, level = tuple(input().split())

user1 = user()

print(f"user {user1.ID} lv {user1.Level}")

user2 = user(Id, level)

print(f"user {user2.ID} lv {user2.Level}")