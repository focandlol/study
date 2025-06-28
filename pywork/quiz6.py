def std_weight(height, gender):
    if gender == "남자":
        weight = round((height / 100) * (height / 100) * 22,2)
        print(f"키 {height}cm 남자의 표준 체중은 {weight}입니다.")
    else:
        weight = round((height / 100) * (height / 100) * 21,2)
        print(f"키 {height}cm 여자의 표준 체중은 {weight}입니다.")

std_weight(175, "남자")