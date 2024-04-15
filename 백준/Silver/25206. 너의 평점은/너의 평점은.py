grades = {"A+": 4.5, "A0":	4.0,
"B+":	3.5,
"B0":	3.0,
"C+":	2.5,
"C0":	2.0,
"D+":	1.5,
"D0":   1.0,
"F":	0.0
        }

total = 0.0
cnt = 0.0
for i in range(20):
    name, credit, grade = input().split()
    
    if grade != "P":
        total += float(credit) * grades[grade]
        cnt += float(credit)

print(total/cnt)    

    