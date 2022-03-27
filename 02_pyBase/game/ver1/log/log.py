
import logging
import shutil
import time
from config import Config


logging.basicConfig(level=logging.INFO,  # 控制台打印的日志级别
                    filename=Config.logMsgFile,
                    filemode='a',  ##模式，有w和a，w就是写模式，每次都会重新写日志，覆盖之前的日志
                    # a是追加模式，默认如果不写的话，就是追加模式
                    format='%(asctime)s - %(pathname)s[line:%(lineno)d] - %(levelname)s: %(message)s'
                    # 日志格式
                    )

def logImg(msg, *srcs):
    nowStr = time.strftime("%Y_%m_%d__%H_%M_%S", time.localtime())
    imgArr = []
    for num in range(len(srcs)):
        imgUrl = "{0}{1}_{2}.png".format(Config.logImgRoot, nowStr, num)
        imgArr.append(imgUrl)
        shutil.copyfile(srcs[num], imgUrl)
    logging.error(msg, *imgArr)


def info(msg, *args):
    logging.info(msg, *args)



if __name__ == '__main__':
    # shutil.copyfile()
    strftime = time.strftime("%Y_%m_%d__%H_%M_%S", time.localtime())
    print(strftime)

    pass
