class ThailandPackage:
    def detail(self):
        print("태국")

if __name__ == "__main__":
    print("thailand 모듈 직접 실행")
else:
    print("thailand 외부에서 모듈 호출")
    print(__package__)