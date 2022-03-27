import time

import win.mouse as mouse
import service.lushi as lushi
import log.log as log
from config import Config

lushiBtn = lushi.lushiBtn
FightCount = Config.fightCount

class State:
    def __init__(self, lushiparam: lushi.Lushi):
        self.lushi = lushiparam
        self.act = lushiparam.act
        self.view = lushiparam.view

    def playGame(self):
        return False

    def setState(self, state):
        self.lushi.state = state
        return False


# 初始状态
class InitState(State):

    def playGame(self):
        """
        执行游戏
        :return: True 正常运行
        """
        self.view.steady()
        startLen = len(lushiBtn.gameStartArr) - 1
        gameStart = lushiBtn.gameStartArr[-1]
        for idx in range(startLen):
            item = lushiBtn.gameStartArr[idx]
            self.act.findClick(item)
            nextImg = lushiBtn.gameStartArr[idx + 1]
            print("nextImg", nextImg)
            realImg = self.lushi.clickWaitFor(nextImg, gameStart)
            print("realImg", realImg)
            if realImg:
                if realImg == gameStart:
                    break
                continue
            else:
                self.errorLog(nextImg)
                self.setState(ErrorState(self.lushi))
                return True
        # 最后一个按钮
        time.sleep(2)
        self.act.findClick(gameStart)
        # 准备到ok
        time.sleep(10)
        self.view.steady()
        if self.lushi.clickWaitFor(lushiBtn.selectThreeTeam):
            self.setState(FightState(self.lushi))
            return True
        self.setState(ErrorState(self.lushi))
        return True

    def errorLog(self, src):
        log.logImg("initState_error_nextImg={0}".format(src), src)
        pass



# 战斗状态
class FightState(State):

    def playGame(self):
        # 选人
        if self.lushi.clickWaitFor(lushiBtn.selectThreeTeam, lushiBtn.attaOver, lushiBtn.attaTmpOver):
            self.act.findClick(lushiBtn.selectThreeTeam)
        # 结束
        # self.lushi.clickWaitFor(lushiBtn.selectThreeTeamOver)
        # self.act.findClick(lushiBtn.selectThreeTeamOver)
        time.sleep(5)
        # 进攻
        if not self.lushi.attaAnimi():
            self.setState(ErrorState(self.lushi))
            return True
        self.view.steady()
        num = 1
        findImg = self.view.canFind(lushiBtn.waitNextLevel, lushiBtn.isNeedSkill, lushiBtn.isNeedSkill2)
        while not findImg and num < FightCount:
            if not self.lushi.attaAnimi():
                self.setState(ErrorState(self.lushi))
                return True
            self.view.steady()
            findImg = self.view.canFind(lushiBtn.waitNextLevel, lushiBtn.isNeedSkill, lushiBtn.isNeedSkill2)
            num +=1
        # 进攻 次数过多
        if num > FightCount:
            log.info("FightState_error_>FightCount")
            self.setState(ErrorState(self.lushi))
            return True

        if findImg:
            self.setState(SelLevState(self.lushi))
            return True
        self.setState(ErrorState(self.lushi))
        return True


# 选关状态
class SelLevState(State):

    def playGame(self):
        # 升级技能先
        if self.view.canFind(lushiBtn.isNeedSkill, lushiBtn.isNeedSkill2):
            mouse.click(*lushiBtn.selSkill)
            time.sleep(0.5)
            mouse.click(*lushiBtn.selSkillOver)
            time.sleep(0.5)
            self.view.steady()
        # 选关
        clickImg = self.view.canFind(lushiBtn.gameStart, lushiBtn.gameStart2)
        if not clickImg:
            for absVal in lushiBtn.abssSelectLevel:
                mouse.click(*absVal)
                time.sleep(0.5)
                clickImg = self.view.canFind(lushiBtn.gameStart, lushiBtn.gameStart2)
                if clickImg:
                    break
        if clickImg:
            self.act.findClick(clickImg)
            if clickImg == lushiBtn.gameStart2:
                self.playGame()
                return
            self.act.findClick(clickImg)
            time.sleep(20)
            self.view.steady()
            self.lushi.clickWaitFor(lushiBtn.selectThreeTeam)
        # 看看是否有选人状态
        if self.view.canFind(lushiBtn.selectThreeTeam):
            self.setState(FightState(self.lushi))
            return True
        #
        if self.view.canFind(lushiBtn.getCard, lushiBtn.getCardOver):
            self.setState(LevClearState(self.lushi))
            return True
        self.setState(ErrorState(self.lushi))
        return True

# 关卡清空状态
class LevClearState(State):

    def playGame(self):
        while self.view.canFind(lushiBtn.getCard):
            self.act.findClick(lushiBtn.getCard)
            time.sleep(1)

        if self.view.canFind(lushiBtn.getCardOver):
            self.act.findClick(lushiBtn.getCard)
            time.sleep(1)
            self.view.steady()

        if self.view.canFind(lushiBtn.travelPoint):
            self.setState(InitState(self.lushi))
            return True
        self.setState(ErrorState(self.lushi))
        return True


# 异常状态
class ErrorState(State):
    def __init__(self, *args):
        super(ErrorState, self).__init__(*args)
        log.logImg()

    def playGame(self):
        return False

    def errorLog(self):
        log.logImg("errorState_log", self.view.getLastSrc())

