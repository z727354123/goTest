import service.action as action
import service.view as view
import service.fizz as fizz
import os
import time





if __name__ == '__main__':
    dir = r"C:\Users\fizzkai\Desktop\\"
    while True :
        listdir = os.listdir(dir)
        for item in listdir:
            if item.find("Hearthstone") >= 0:
                print(item)
                os.remove(dir + item)
        time.sleep(5)
