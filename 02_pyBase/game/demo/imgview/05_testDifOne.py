import cv2
import matplotlib.pyplot as plt


# 计算方差
def getss(list):
    # 计算平均值
    avg = sum(list) / len(list)
    # 定义方差变量ss，初值为0
    ss = 0
    # 计算方差
    for l in list:
        ss += (l - avg) * (l - avg) / len(list)
    # 返回方差
    return ss


# 获取每行像素平均值
def getdiff(imgURL, sideLen=0):
    """
    :param imgURL: 图片地址
    :param sideLen: 缩放长度
    :return:
    """
    img = cv2.imread(imgURL)
    # 定义边长
    # 缩放图像
    if sideLen:
        img = cv2.resize(img, (sideLen, sideLen), interpolation=cv2.INTER_CUBIC)
    else:
        sideLen = len(img)
    # 灰度处理
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    # avglist列表保存每行像素平均值
    avglist = []
    # 计算每行均值，保存到avglist列表
    for i in range(sideLen):
        avg = sum(gray[i]) / len(gray[i])
        avglist.append(avg)
    # 返回avglist平均值
    return avglist, sideLen


def drawImg(oldDiff, newDiff, xLen):
    xLen = range(xLen)
    plt.figure("avg")
    plt.plot(xLen, oldDiff, marker="*", label="oldURL")
    plt.plot(xLen, newDiff, marker="*", label="newURL")
    plt.title("avg")
    plt.legend()
    plt.show()
    cv2.waitKey(0)
    cv2.destroyAllWindows()

def compareImg(oldURL, newURL):
    """
    对像素求方差并比对

    :param oldURL: 图片1
    :param newURL: 图片2
    :return: 方差
    """
    oldDiff, sideLen = getdiff(oldURL)
    oldSS = getss(oldDiff)

    newDiff, _ = getdiff(newURL)
    newSS = getss(newDiff)

    result = abs(oldSS - newSS)
    return result

def compareImgAndDraw(oldURL, newURL):
    """
    对像素求方差并比对

    :param oldURL: 图片1
    :param newURL: 图片2
    :return: 方差
    """
    oldDiff, sideLen = getdiff(oldURL)
    oldSS = getss(oldDiff)
    newDiff, _ = getdiff(newURL)
    newSS = getss(newDiff)

    result = abs(oldSS - newSS)

    drawImg(oldDiff, newDiff, sideLen)
    return result


if __name__ == '__main__':
    saveURL = r"D:\testpyimg\test\haha"
    saveURL_E = r".png"
    oldURL = saveURL + "cmp1" + saveURL_E
    newURL = saveURL + "cmp2" + saveURL_E
    # 读取测试图片
    result = compareImgAndDraw(oldURL, newURL)
    print("两张照片的方差为：%s" % (result))