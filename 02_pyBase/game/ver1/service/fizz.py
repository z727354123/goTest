import time

import win.mouse as mouse
import service.action as action
import service.view as view

def capAndSend(act: action.Action):
    mouse.keyInput("print_screen")
    act.findClick(r"fizz\wechat")
    act.findClick(r"fizz\head")
    act.findClick(r"fizz\input")
    mouse.keyInputComb("ctrl", "v")
    time.sleep(0.05)
    mouse.keyInputComb("enter")
    mouse.click(10, 10)

if __name__ == '__main__':
    viewObj = view.View()
    act = action.Action(viewObj)
    capAndSend(act)


