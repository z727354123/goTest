import time

import win.mouse as mouse
import service.action as action
import service.view as view
import service.lushiState as state

# 窗口偏移量
changeX, changeY = 279, 137


class Btn:
    def __init__(self):
        # 弹出框关闭
        self.close = r"lushi\close"
        # TODO: 佣兵模式
        self.slaveMode = r"lushi\slaveMode"
        # 旅行点
        self.travelPoint = r"lushi\travelPoint"
        self.travelNormal = r"lushi\travelNormal"
        # 旅行点 选择
        self.travelStart = r"lushi\travelStart"
        # level10 选择
        self.level10 = r"lushi\level10"
        # 等级选择 开始
        self.levelStart = r"lushi\levelStart"
        # 队伍开始
        self.teamStart = r"lushi\teamStart"
        # 队伍锁定
        self.teamLock = r"lushi\teamLock"
        # 游戏开始
        self.gameStart = r"lushi\gameStart"
        self.gameStart2 = r"lushi\gameStart2"

        self.gameStartArr = [
            self.travelPoint,
            self.travelNormal,
            self.travelStart,
            self.level10,
            self.levelStart,
            self.teamStart,
            self.teamLock,
            self.gameStart
        ]
        # 已登场
        self.selectThreeTeam = r"lushi\selectThreeTeam"
        self.selectThreeTeamOver = r"lushi\selectThreeTeamOver"
        # 进攻
        self.absAttaFrom = (547 + changeX, 377 + changeY)
        self.absAttaTo = (661 + changeX, 243 + changeY)
        # 进攻结束
        self.attaOver = r"lushi\attaOver"
        # 进攻结束
        self.attaTmpOver = r"lushi\attaTmpOver"
        # 需要升级技能
        self.isNeedSkill = r"lushi\isNeedSkill"
        self.isNeedSkill2 = r"lushi\isNeedSkill2"

        # 等待重新选择
        self.waitNextLevel = r"lushi\waitNextLevel"

        # 选择技能
        self.selSkill = (592 + changeX, 311 + changeY)
        self.selSkillOver = (805 + changeX, 648 + changeY)
        # 选择敌人
        self.abssSelectLevel = [
            (540 + changeX, 192 + changeY),
            (330 + changeX, 400 + changeY),
            (380 + changeX, 400 + changeY),
            (430 + changeX, 400 + changeY),
            (480 + changeX, 400 + changeY),
            (530 + changeX, 400 + changeY),
            (580 + changeX, 400 + changeY),
            (630 + changeX, 400 + changeY),
            (680 + changeX, 400 + changeY),
            (730 + changeX, 400 + changeY),
            (780 + changeX, 400 + changeY),
        ]
        # 虚空按钮
        self.absNormalBtn = (30 + changeX, 50 + changeY)
        # 选中之后还是 gameStart

        # 奖励图片
        self.getCard = r"lushi\getCard"
        # 奖励图片结束
        self.getCardOver = r"lushi\getCardOver"


lushiBtn = Btn();


class Lushi:
    def __init__(self, act: action.Action):
        self.act = act
        self.view = act.mainView
        self.state = state.SelLevState(self)

    def clickWaitFor(self, *srcs):
        num = 1
        time.sleep(1)
        while num < 300:
            self.view.saveCap()
            url = self.view.canFind(*srcs)
            if url:
                return url
            time.sleep(1)
            num += 1
        return ""

    def run(self):
        # 开始游戏
        while self.state.playGame():
            pass
        # 发送截屏
        capAndSend(self.act)

    def normalClick(self):
        num = 0
        time.sleep(1)
        while num < 5:
            mouse.click(*lushiBtn.absNormalBtn)
            time.sleep(0.5)
            mouse.click(*lushiBtn.absNormalBtn)
            time.sleep(0.5)
            num += 1

    def attaAnimi(self):
        num = 1
        while not self.view.canFind(lushiBtn.attaOver, lushiBtn.selectThreeTeam) and num < 13:
            mouse.hmoveToAbs(*lushiBtn.absAttaFrom, *lushiBtn.absAttaTo)
            num+=1
            time.sleep(1)
        self.view.steady()
        if self.view.canFind(lushiBtn.isNeedSkill, lushiBtn.isNeedSkill2):
            return True
        if self.act.findClick(lushiBtn.selectThreeTeam, 0.9):
            time.sleep(3)
            self.view.steady()
            return True
        if self.act.findClick(lushiBtn.attaTmpOver, 0.9):
            time.sleep(3)
            self.view.steady()
            return True
        if self.act.findClick(lushiBtn.attaOver, 0.9):
            time.sleep(3)
            self.view.steady()
            return True
        time.sleep(1)
        return False


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
    viewObj = view.View("add", mouse.maxW, mouse.maxH)
    act = action.Action(viewObj)
    lushi = Lushi(act)
    time.sleep(2)
    # capAndSend(act)
    lushi.run()
