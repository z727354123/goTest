import time

from config import Config
from log import log
from win.mouse import hmoveToAbs
from win.mouse import click
import win.mouse as mouse
import service.view as view


class Action:
    def __init__(self, mainView: view.View):
        self.mainView = mainView

    def findMove(self, src, dest, threshold=0.8) -> bool:
        self.mainView.saveCap()
        srcUrl = self.getTargetSrc(src)
        destUrl = self.getTargetSrc(dest)
        srcArr = self.mainView.find(srcUrl, threshold)
        destArr = self.mainView.find(destUrl, threshold)

        if srcArr and destArr:
            hmoveToAbs(*srcArr[0], *destArr[0])
            return True
        log.logImg(
            "findMove_error_src={0}`dest={1}`srcArr={2}`destArr={3}`threshold={4}`img=%s".format(
                src, dest, srcArr, destArr, threshold), self.mainView.getLastSrc())
        return False

    def getTargetSrc(self, src):
        return Config.targetRoot + src + r".png"

    def findClick(self, src, threshold=0.8):
        self.mainView.saveCap()
        srcArr = self.mainView.find(self.getTargetSrc(src), threshold)
        if srcArr:
            click(*srcArr[0])
            mouse.move(0, 0)
            time.sleep(2)
            return True

        log.logImg(
            "findClick_error_src={0}`srcArr={1}`threshold={2}`img=%s".format(
                src, srcArr, threshold), self.mainView.getLastSrc())
        return False
