import aircv as ac
import cv2 as cv



def draw_circle(img, pos, circle_radius, color, line_width):
    cv.circle(img, (int(pos[0]), int(pos[1])), circle_radius, color, line_width)


def showImg(img):
    cv.namedWindow("image")
    cv.imshow('image', img)
    cv.waitKey(10000)  # 显示 10000 ms 即 10s 后消失
    cv.destroyAllWindows()


if __name__ == "__main__":
    imsrc = ac.imread(r'D:\testpyimg\test\hahaold.png')
    imobj = ac.imread(r'D:\testpyimg\test\hahanew.png')

    # find the match position
    pos = ac.find_all_template(imsrc, imobj, 0.5)
    for item in pos:
        result = item['result']
        print(result)
        circle_radius = 50
        color = (0, 255, 0)
        width = 0
        draw_circle(imsrc, result, circle_radius, color, width)
    showImg(imsrc)
    print("-------------------华丽分割线----------------------")