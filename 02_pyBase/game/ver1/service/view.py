import time

from win import cap
from img import diff
from img import find
from log import log

from config import Config


class View:
    def __init__(self, name="tmp", w=1200, h=1080):
        self.pathArr = [Config.viewRoot + name + "1.png", Config.viewRoot + name + "2.png"]
        self.w, self.h = w, h
        self.lastIdx = 1

    def saveCap(self):
        """
        保存当前截图
        :return: 返回文件路径
        """
        self.lastIdx ^= 1
        src = self.getLastSrc()
        cap.saveFile(src, 0, 0, self.w, self.h)
        return src

    def getLastSrc(self):
        return self.pathArr[self.lastIdx]

    def steady(self, sleepTime=1, score=0.95):
        """
        图片稳定
        :param sleepTime: 间隔时间
        :param score: >该分数才算稳定
        :return: 稳定后返回
        """
        log.info("steady_start")
        self.saveCap()
        time.sleep(sleepTime)
        self.saveCap()
        num = 1
        while self.isLess(score):
            num += 1
            time.sleep(sleepTime)
            self.saveCap()
        log.info("steady_over:%s", num)
        pass

    def isLess(self, score):
        get_score = diff.getScore(*self.pathArr)
        log.info("get_score:%s", get_score)
        return get_score < score

    def find(self, target, threshold=0.8):
        return find.find(self.getLastSrc(), target, threshold)

    def saveAndFind(self, target, threshold=0.8):
        self.saveCap()
        return find.find(self.getLastSrc(), target, threshold)

    def canFind(self, *srcs):
        self.saveCap()
        for src in srcs:
            findArr = self.find(self.getTargetSrc(src), 0.85)
            if findArr:
                return src
        return ""


    def getTargetSrc(self, src):
        return Config.targetRoot + src + r".png"

if __name__ == '__main__':
    view = View()
    view.steady()
    pass
