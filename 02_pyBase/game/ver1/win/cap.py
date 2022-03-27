import os
import time
import win32gui, win32ui, win32con, win32api

import numpy as np
from PIL import Image
from win32.lib import win32con
MoniterDev = win32api.EnumDisplayMonitors(None, None)
maxW =  MoniterDev[0][2][2]
maxH = MoniterDev[0][2][3]

def notSave(wx, hy, w, h):
    '''
    windows 截屏
    :param filename: 保存文件名
    :param wx: x轴偏移量
    :param hy: y轴偏移量
    :param w: 宽度
    :param h: 高度
    :return: Image对象
    '''
    hwnd = 0  # 窗口的编号，0号表示当前活跃窗口
    # 根据窗口句柄获取窗口的设备上下文DC（Divice Context）
    hwndDC = win32gui.GetWindowDC(hwnd)
    # 根据窗口的DC获取mfcDC
    mfcDC = win32ui.CreateDCFromHandle(hwndDC)
    # mfcDC创建可兼容的DC
    saveDC = mfcDC.CreateCompatibleDC()
    # 创建bigmap准备保存图片
    saveBitMap = win32ui.CreateBitmap()
    # 获取监控器信息
    if not w:
        w = maxW
        h = maxH

    wx = 0 if not wx else wx
    hy = 0 if not hy else hy
    # print w,h　　　#图片大小
    # 为bitmap开辟空间
    saveBitMap.CreateCompatibleBitmap(mfcDC, w, h)
    # 高度saveDC，将截图保存到saveBitmap中
    saveDC.SelectObject(saveBitMap)
    # 截取从左上角（0，0）长宽为（w，h）的图片
    saveDC.BitBlt((0, 0), (w, h), mfcDC, (wx, hy), win32con.SRCCOPY)
    # 获取 array new
    signedIntsArray = saveBitMap.GetBitmapBits(True)
    img = np.fromstring(signedIntsArray, dtype='uint8')
    img.shape = (h, w, 4)
    im = Image.fromarray(img)
    # 保存图片 old
    # saveBitMap.SaveBitmapFile(saveDC, filename)
    return im


def saveFile(filename, wx = 0, hy= 0, w= 0, h= 0):
    '''
    windows 截屏
    :param filename: 保存文件名
    :param wx: x轴偏移量
    :param hy: y轴偏移量
    :param w: 宽度
    :param h: 高度
    :return: Image对象
    '''
    hwnd = 0  # 窗口的编号，0号表示当前活跃窗口
    # 根据窗口句柄获取窗口的设备上下文DC（Divice Context）
    hwndDC = win32gui.GetWindowDC(hwnd)
    # 根据窗口的DC获取mfcDC
    mfcDC = win32ui.CreateDCFromHandle(hwndDC)
    # mfcDC创建可兼容的DC
    saveDC = mfcDC.CreateCompatibleDC()
    # 创建bigmap准备保存图片
    saveBitMap = win32ui.CreateBitmap()
    # 获取监控器信息
    MoniterDev = win32api.EnumDisplayMonitors(None, None)
    maxW =  MoniterDev[0][2][2]
    maxH = MoniterDev[0][2][3]
    if not w:
        w = MoniterDev[0][2][2]
        h = MoniterDev[0][2][3]

    # print w,h　　　#图片大小
    # 为bitmap开辟空间
    saveBitMap.CreateCompatibleBitmap(mfcDC, w, h)
    # 高度saveDC，将截图保存到saveBitmap中
    saveDC.SelectObject(saveBitMap)
    # 截取从左上角（0，0）长宽为（w，h）的图片
    saveDC.BitBlt((0, 0), (w, h), mfcDC, (wx, hy), win32con.SRCCOPY)
    # 获取 array new
    # signedIntsArray = saveBitMap.GetBitmapBits(True)
    # img = np.fromstring(signedIntsArray, dtype='uint8')
    # img.shape = (h, w, 4)
    # im = Image.fromarray(img)
    # 保存图片 old
    saveBitMap.SaveBitmapFile(saveDC, filename)
    pass

if __name__ == '__main__':
    saveURL = r"D:\testpyimg\test\haha"
    saveURL_E = r".png"
    oldURL = saveURL + "cmp1" + saveURL_E
    newURL = saveURL + "cmp2" + saveURL_E
    # cap.saveFile(oldURL)
    # cap.saveFile(newURL)
